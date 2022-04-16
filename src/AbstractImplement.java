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
        String[] collumnNames = new String[DatesT.size()+1];
        collumnNames[0] = "Names";
        for(int i = 0; i < DatesT.size(); i++){
            collumnNames[i+1] = DatesT.get(i).toString();
        }

        String[][] data = new String[NamesT.size()][DatesT.size()+1];
        for(int i = 0; i < NamesT.size(); i++){
            data[i][0] = NamesT.get(i);
            List<Boolean> temp = new ArrayList<Boolean>();
            temp = AttendT.get(i);
            for(int j = 0; j < DatesT.size(); j++){
                data[i][j+1] = temp.get(j).toString();
            }
        }

        if(choice == false){
            new SecondWindow(data, collumnNames);
        }
        else{
            PDDocument pdf= new PDDocument();
            PDPage page = new PDPage();
            pdf.addPage(page);
            //path where the PDF file will be store
            try{
                //pdf.save("output.pdf");

                PDPageContentStream cs = new PDPageContentStream(pdf, page);
                //cs.beginText();
                //cs.setFont(PDType1Font.HELVETICA, 5);
                // Text color in PDF
                //cs.setNonStrokingColor(Color.BLUE);
                // set offset from where content starts in PDF
                //cs.newLineAtOffset(20, 750);
                //cs.showText("Hello! This PDF is created using PDFBox");


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
                Row<PDPage> headerRow = table.createRow(20);
                // the first parameter is the cell width
                Cell<PDPage> cell = headerRow.createCell(10, "Name");
                cell.setFontSize(7);
                for(int i = 0; i < DatesT.size(); i++){
                    cell = headerRow.createCell((80/DatesT.size()), DatesT.get(i).toString());
                    cell.setFontSize(7);
                }
                table.addHeaderRow(headerRow);

                table.draw();



                //cs.newLine();
                //cs.endText();
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
    public void showOneStudent(String name) {
        String[] collumnNames = new String[DatesT.size()+1];
        collumnNames[0] = "Names";
        for(int i = 0; i < DatesT.size(); i++){
            collumnNames[i+1] = DatesT.get(i).toString();
        }

        String[][] data = new String[NamesT.size()][DatesT.size()+1];
        for(int i = 0; i < NamesT.size(); i++){
            if(NamesT.get(i).equals(name)) {
                data[0][0] = NamesT.get(i);
                List<Boolean> temp = new ArrayList<Boolean>();
                temp = AttendT.get(i);
                for (int j = 0; j < DatesT.size(); j++) {
                    data[0][j+1] = temp.get(j).toString();
                }
                new SecondWindow(data, collumnNames);
                return;
            }
        }
        System.out.println("ERROR - no such student");

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
