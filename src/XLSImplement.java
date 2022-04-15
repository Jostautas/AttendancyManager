import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

public class XLSImplement extends AbstractImplement{

        @Override
        public void readTable(String fileName) {
            try {
                FileInputStream fis = new FileInputStream(fileName);
                //creating workbook instance that refers to .xls file
                HSSFWorkbook wb = new HSSFWorkbook(fis);
                //creating a Sheet object to retrieve the object
                HSSFSheet sheet = wb.getSheetAt(0);

                //read date row (first row):
                Row row = sheet.getRow(0);
                for(int i = 1; i < row.getLastCellNum(); i++){
                    Cell cell = row.getCell(i);
                    LocalDate date = LocalDate.parse(cell.toString());
                    DatesT.add(date);
                }

                //read following rows:
                for(int i = 1; i <= sheet.getLastRowNum(); i++){
                    row = sheet.getRow(i);
                    AttendT.add(new ArrayList<Boolean>());

                    //read names:
                    Cell cell = row.getCell(0);
                    NamesT.add(cell.getStringCellValue());

                    //read attendance:
                    for(int j = 1; j < row.getLastCellNum(); j++){
                        cell = row.getCell(j);
                        Boolean b = Boolean.parseBoolean(cell.getStringCellValue());
                        AttendT.get(i-1).add(b);
                    }
                }

                fis.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void save(String fileName) {

            //create a new workbook and worksheet and overwrite the old file
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("0");

            //crate first row:
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Names");
            for(int i = 1; i <= DatesT.size(); i++){
                cell = row.createCell(i);
                cell.setCellValue(DatesT.get(i-1).toString());
                System.out.print(DatesT.get(i-1).toString() + ", ");
            }

            //create following rows:
            for(int i = 1; i <= NamesT.size(); i++){
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(NamesT.get(i-1));

                List<Boolean> temp = AttendT.get(i-1);
                for(int j = 1; j <= DatesT.size(); j++){
                    cell = row.createCell(j);
                    cell.setCellValue(temp.get(j-1).toString());
                    System.out.print(temp.get(j-1).toString() + ", ");
                }
                System.out.println();
            }

            //write to a file:
            try{
                FileOutputStream out = new FileOutputStream(new File(fileName));
                wb.write(out);
                out.close();
                System.out.println("ALL OK");

            } catch(java.io.IOException ex){
                ex.printStackTrace();
            }
        }


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
        public void showAllStudentsWDate() {
        }
        @Override
        public void setStartDate(LocalDate startDate) {
        }
        @Override
        public void setEndDate(LocalDate endDate) {
        }
        @Override
        public void setDate(LocalDate date) {
        }
        @Override
        public void changeAttend(Boolean newVal, String name) {
        }
        @Override
        public void showOneStudWithinDates(String name) {
        }

}
