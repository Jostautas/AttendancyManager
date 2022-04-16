import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class workWithData extends AbstractImplement{
    LocalDate startDate, endDate, date;

    @Override
    public void setDatesT(ArrayList<LocalDate> DatesT) {
        this.DatesT = DatesT;
    }

    @Override
    public void setNamesT(ArrayList<String> NamesT) {
        this.NamesT = NamesT;
    }

    @Override
    public void setAttendT(ArrayList<ArrayList<Boolean>> AttendT) {
        this.AttendT = AttendT;
    }

    @Override
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public void showAllStudentsWDate(Boolean choice){
        int columnCounter = 0;
        String[] collumnNames;
        for(int i = 0; i < DatesT.size(); i++){
            if(DatesT.get(i).isAfter(startDate) && DatesT.get(i).isBefore(endDate)){
                columnCounter++;
            }
        }
        // first row:
        collumnNames = new String[columnCounter + 1];
        collumnNames[0] = "Names";
        for (int i = 0, j = 1; i < DatesT.size(); i++) {
            if (DatesT.get(i).isAfter(startDate) && DatesT.get(i).isBefore(endDate)) {
                collumnNames[j] = DatesT.get(i).toString();
                j++;
            }
        }

        // names and Attendance table:
        String[][] data = new String[NamesT.size()][columnCounter + 1];
        for (int i = 0; i < NamesT.size(); i++) {
            data[i][0] = NamesT.get(i);
            List<Boolean> temp = new ArrayList<Boolean>();
            temp = AttendT.get(i);
            for (int j = 0, y = 1; j < DatesT.size(); j++) {
                if (DatesT.get(j).isAfter(startDate) && DatesT.get(j).isBefore(endDate)) {
                    data[i][y] = temp.get(j).toString();
                    y++;
                }
            }
        }
        if(choice == false)
            new SecondWindow(data, collumnNames);
        else{
            PDDocument pdf= new PDDocument();
            PDPage page = new PDPage();
            pdf.addPage(page);
            //path where the PDF file will be stored
            try{
                PDPageContentStream cs = new PDPageContentStream(pdf, page);

                //Dummy Table
                float margin = 20;
                // starting y position is whole page height subtracted by top and bottom margin
                float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
                // we want table across whole page width (subtracted by left and right margin ofcourse)
                float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

                boolean drawContent = true;
                float yStart = yStartNewPage;
                float bottomMargin = 20;
                // y position is your coordinate of top left corner of the table
                float yPosition = 700;

                BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, pdf, page, true, drawContent);

                // the parameter is the row height
                Row<PDPage> row = table.createRow(20);
                Cell cell;
                cell = row.createCell(12, collumnNames[0]);
                cell.setFontSize(7);
                for(int i = 1; i < columnCounter+1; i++){
                    // the first parameter is the cell width
                    cell = row.createCell((80/columnCounter), collumnNames[i]);
                    cell.setFontSize(7);
                }
                table.addHeaderRow(row);

                for(int i = 0; i < NamesT.size(); i++){
                    row = table.createRow(20);
                    cell = row.createCell(12, data[i][0]);
                    cell.setFontSize(7);
                    for(int j = 1; j < columnCounter+1; j++){
                        cell = row.createCell((80/columnCounter), data[i][j]);
                        cell.setFontSize(7);
                    }
                }

                table.draw();
                cs.close();
                pdf.save("output.pdf");
                pdf.close();
                System.out.println("PDF created");

            } catch(Exception ex){
                ex.printStackTrace();
                System.out.println("ERROR - could not create pdf");
            }
        }

    }

    @Override
    public void showOneStudWithinDates(String name, Boolean choice) {
        int columnCounter = 0;
        for (int i = 0; i < DatesT.size(); i++) {
            if (DatesT.get(i).isAfter(startDate) && DatesT.get(i).isBefore(endDate)) {
                columnCounter++;
            }
        }


        String[] collumnNames = new String[columnCounter + 1];
        collumnNames[0] = "Names";
        for (int i = 0, j = 1; i < DatesT.size(); i++) {
            if (DatesT.get(i).isAfter(startDate) && DatesT.get(i).isBefore(endDate)) {
                collumnNames[j] = DatesT.get(i).toString();
                j++;
            }
        }

        String[][] data = new String[NamesT.size()][DatesT.size() + 1];
        for (int i = 0; i < NamesT.size(); i++) {
            if (NamesT.get(i).equals(name)) {
                data[0][0] = NamesT.get(i);
                List<Boolean> temp = new ArrayList<Boolean>();
                temp = AttendT.get(i);
                for (int j = 0, y = 1; j < DatesT.size(); j++) {
                    if (DatesT.get(j).isAfter(startDate) && DatesT.get(j).isBefore(endDate)) {
                        data[0][y] = temp.get(j).toString();
                        y++;
                    }
                }

                if(choice == false){
                    new SecondWindow(data, collumnNames);
                    return;
                }
                else{
                    break;
                }
            }
        }
        if(choice == false){
            System.out.println("ERROR - no such student");
        }

        else{
            PDDocument pdf= new PDDocument();
            PDPage page = new PDPage();
            pdf.addPage(page);
            //path where the PDF file will be stored
            try{
                PDPageContentStream cs = new PDPageContentStream(pdf, page);

                //Dummy Table
                float margin = 20;
                // starting y position is whole page height subtracted by top and bottom margin
                float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
                // we want table across whole page width (subtracted by left and right margin ofcourse)
                float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

                boolean drawContent = true;
                float yStart = yStartNewPage;
                float bottomMargin = 20;
                // y position is your coordinate of top left corner of the table
                float yPosition = 700;

                BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, pdf, page, true, drawContent);

                // the parameter is the row height
                Row<PDPage> row = table.createRow(20);
                Cell cell;
                cell = row.createCell(12, collumnNames[0]);
                cell.setFontSize(7);
                for(int i = 1; i < columnCounter+1; i++){
                    // the first parameter is the cell width
                    cell = row.createCell((80/columnCounter), collumnNames[i]);
                    cell.setFontSize(7);
                }
                table.addHeaderRow(row);

                row = table.createRow(20);
                cell = row.createCell(12, data[0][0]);
                cell.setFontSize(7);
                for(int j = 1; j < columnCounter+1; j++){
                    cell = row.createCell((80/columnCounter), data[0][j]);
                    cell.setFontSize(7);
                }

                table.draw();
                cs.close();
                pdf.save("output.pdf");
                pdf.close();
                System.out.println("PDF created");

            } catch(Exception ex){
                ex.printStackTrace();
                System.out.println("ERROR - could not create pdf");
            }
        }
    }

    @Override
    public void changeAttend(Boolean newVal, String name) {
        // check if date exists:
        int dateIndex = -1;
        for(int i = 0; i < DatesT.size(); i++){
            if(date.isEqual(DatesT.get(i))){
                dateIndex = i;
                break;
            }
        }
        if(dateIndex == -1){
            System.out.println("ERROR - no such date");
            return;
        }

        // check if student exists:
        int studIndex = -1;
        for(int i = 0; i < NamesT.size(); i++){
            if(name.equals(NamesT.get(i))){
                studIndex = i;
                break;
            }
        }
        if(studIndex == -1){
            System.out.println("ERROR - no such student");
            return;
        }

        // if all ok, set bool value in table:
        ArrayList<Boolean> temp = AttendT.get(studIndex);
        temp.set(dateIndex, newVal);
    }



    @Override
    public void save(String fileName) {
    }
    @Override
    public void readTable(String fileName) {
    }
}
