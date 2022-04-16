import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVImplement extends AbstractImplement{
    @Override
    public void readTable(String fileName) {
        CSVReader reader = null;

        try{//parsing a CSV file into CSVReader class constructor
            reader = new CSVReader(new FileReader(fileName));
            String[] nextLine;//reads one line at a time

            // reads date:
            if((nextLine = reader.readNext()) != null){
                for(int i = 1; i < nextLine.length; i++){
                    String str = nextLine[i];
                    LocalDate date = LocalDate.parse(str);
                    DatesT.add(date);
                }
            }

            // reads name and attendance:
            while ((nextLine = reader.readNext()) != null) {
                NamesT.add(nextLine[0]);

                int i = 1;
                AttendT.add(new ArrayList<Boolean>());
                do{
                    Boolean b = Boolean.parseBoolean(nextLine[i]);
                    AttendT.get(NamesT.size()-1).add(b);
                    i++;
                }while(i <= DatesT.size());
            }
            reader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void save(String fileName) {
        try{
            CSVWriter writer = new CSVWriter(new FileWriter(fileName));

            // overwriting data file:

            String[] line = new String[DatesT.size()+1];
            // write first line:
            line[0] = "Name";
            for(int i = 1; i <= (DatesT.size()); i++){
                line[i] = DatesT.get(i-1).toString();
            }
            writer.writeNext(line);

            // write the rest lines:
            for(int i = 0; i < NamesT.size(); i++){
                line[0] = NamesT.get(i);

                List<Boolean> temp = new ArrayList<Boolean>();
                temp = AttendT.get(i);
                for(int j = 1; j <= DatesT.size(); j++){
                    line[j] = temp.get(j-1).toString();
                }
                writer.writeNext(line);
            }

            writer.close();
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
    public void showAllStudentsWDate(Boolean choice) {
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
    public void showOneStudWithinDates(String name, Boolean choice) {
    }
}
