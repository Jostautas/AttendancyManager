import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import be.quodlibet.boxable.*;
import be.quodlibet.boxable.line.LineStyle;


public abstract class AbstractImplement implements FileActions {
    ArrayList<LocalDate> DatesT = new ArrayList<LocalDate>();
    ArrayList<String> NamesT = new ArrayList<String>();
    ArrayList<ArrayList<Boolean>> AttendT = new ArrayList<ArrayList<Boolean>>();

    @Override
    public void showAllStudents(Boolean choice) {
        if(choice == false) {   // print to a popup window
            String[] collumnNames = new String[DatesT.size() + 1];
            collumnNames[0] = "Names";
            for (int i = 0; i < DatesT.size(); i++) {
                collumnNames[i + 1] = DatesT.get(i).toString();
            }

            String[][] data = new String[NamesT.size()][DatesT.size() + 1];
            for (int i = 0; i < NamesT.size(); i++) {
                data[i][0] = NamesT.get(i);
                List<Boolean> temp = new ArrayList<Boolean>();
                temp = AttendT.get(i);
                for (int j = 0; j < DatesT.size(); j++) {
                    data[i][j + 1] = temp.get(j).toString();
                }
            }


            new SecondWindow(data, collumnNames);
        }

        // print to .pdf file
        else{
            PDDocument pdf= new PDDocument();
            PDPage page = new PDPage();
            pdf.addPage(page);
            //path where the PDF file will be store
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
                // the first parameter is the cell width
                Cell<PDPage> cell = row.createCell(12, "Name");
                cell.setFontSize(7);
                for(int i = 0; i < DatesT.size(); i++){
                    cell = row.createCell((80/DatesT.size()), DatesT.get(i).toString());
                    cell.setFontSize(7);
                }
                table.addHeaderRow(row);

                for(int i = 0; i < NamesT.size(); i++){
                    row = table.createRow(20);
                    cell = row.createCell(12, NamesT.get(i));
                    cell.setFontSize(7);
                    List<Boolean> temp = new ArrayList<Boolean>();
                    temp = AttendT.get(i);
                    for(int j = 0; j < DatesT.size(); j++){
                        cell = row.createCell((80/DatesT.size()), temp.get(j).toString());
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
    public void addDate(String str) {
        LocalDate date = LocalDate.parse(str);
        DatesT.add(date);

        //inicialize all student attendances new day to false:
        List<Boolean> b = new ArrayList<Boolean>();
        for(int i = 0; i < NamesT.size(); i++){
            b = AttendT.get(i);
            b.add(false);
        }
    }

    @Override
    public void addStudent(String newName) {
        NamesT.add(newName);

        //inicialize Attendance values to false:
        AttendT.add(new ArrayList<Boolean>());
        for(int i = 0; i < DatesT.size(); i++){
            AttendT.get(NamesT.size()-1).add(false);
        }
    }

    @Override
    public LocalDate readDate(String str){
        LocalDate date = LocalDate.parse(str);
        return date;
    }

    @Override
    public void showOneStudent(String name, Boolean choice) {
        String[]collumnNames = new String[DatesT.size()+1];
        collumnNames[0] = "Names";
        for(int i = 0; i < DatesT.size(); i++){
            collumnNames[i+1] = DatesT.get(i).toString();
        }

        String[][] data = new String[1][DatesT.size()+1];
        for(int i = 0; i < NamesT.size(); i++){
            if(NamesT.get(i).equals(name)) {
                data[0][0] = NamesT.get(i);
                List<Boolean> temp = new ArrayList<Boolean>();
                temp = AttendT.get(i);
                for (int j = 0; j < DatesT.size(); j++) {
                    data[0][j+1] = temp.get(j).toString();
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
                for(int i = 1; i < DatesT.size()+1; i++){
                    // the first parameter is the cell width
                    cell = row.createCell((80/DatesT.size()), collumnNames[i]);
                    cell.setFontSize(7);
                }
                table.addHeaderRow(row);


                row = table.createRow(20);
                cell = row.createCell(12, data[0][0]);
                cell.setFontSize(7);
                for(int j = 1; j < DatesT.size()+1; j++){
                    cell = row.createCell((80/DatesT.size()), data[0][j]);
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
    public ArrayList<LocalDate> getDatesT(){
        return DatesT;
    }
    @Override
    public ArrayList<String> getNamesT() {
        return NamesT;
    }
    @Override
    public ArrayList<ArrayList<Boolean>> getAttendT(){
        return AttendT;
    }


}
