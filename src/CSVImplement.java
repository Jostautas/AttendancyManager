import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CSVImplement extends AbstractImplement{
    @Override
    public void readTable(String fileName) {
        CSVReader reader = null;

        try{//parsing a CSV file into CSVReader class constructor
            reader = new CSVReader(new FileReader(fileName));
            String [] nextLine;//reads one line at a time

            // reads date:
            if((nextLine = reader.readNext()) != null){
                for(int i = 1; i < nextLine.length; i++){
                    String token = nextLine[i];
                    LocalDate date = LocalDate.parse(token);
                    DatesT.add(date);
                }
                System.out.println("");
            }

            // reads name and attendance:
            while ((nextLine = reader.readNext()) != null) {
                NamesT.add(nextLine[0]);

                int i = 1;
                //AttendT.add(new ArrayList<Boolean>());
                //ArrayList<Boolean> strB = new ArrayList<Boolean>();
                AttendT.add(new ArrayList<Boolean>());
                do{
                    Boolean b = Boolean.parseBoolean(nextLine[i]);
                    AttendT.get(nameCounter).add(b);
                    i++;
                }while(i < DatesT.size());
                //AttendT.add(strB);
                nameCounter++;

            }
            reader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
}

    CSVWriter writer;
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
            for(int i = 1; i <= nameCounter; i++){
                line[0] = NamesT.get(i-1);
                for(int j = 1; j <= DatesT.size(); j++){
                    line[j] = "boolean";
                }
                writer.writeNext(line);
            }

            writer.close();
        } catch(java.io.IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void addStudent(String name) {

    }

    @Override
    public void addDate(LocalDate date) {

    }

    @Override
    public void showOneStudent() {

    }
}
