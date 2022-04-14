import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractImplement implements FileActions {
    ArrayList<LocalDate> DatesT = new ArrayList<LocalDate>();
    ArrayList<String> NamesT = new ArrayList<String>();
    ArrayList<ArrayList<Boolean>> AttendT = new ArrayList<ArrayList<Boolean>>();

    @Override
    public void showAllStudents() {
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

        new SecondWindow(data, collumnNames);

        /*
        System.out.print("       ");
        for(int i = 0; i < DatesT.size(); i++){
            System.out.print(DatesT.get(i)+", ");
        }
        for(int i = 0; i < nameCounter; i++){
            System.out.print(NamesT.get(i) + ", ");
            List<Boolean> temp = new ArrayList<Boolean>();
            temp = AttendT.get(i);
            for(int j = 0; j < DatesT.size(); j++){
                System.out.print(temp.get(j) + ", ");
            }
            System.out.println("");
        }*/
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
    public void showOneStudent(String name) {
        for(int i = 0; i < DatesT.size(); i++){
            System.out.print(DatesT.get(i)+", ");
        }
        System.out.println("");
        for(int i = 0; i < NamesT.size(); i++){
            if(NamesT.get(i).equals(name)){
                System.out.print(NamesT.get(i) + ", ");
                List<Boolean> temp = new ArrayList<Boolean>();
                temp = AttendT.get(i);
                for (int j = 0; j < DatesT.size(); j++) {
                    System.out.print(temp.get(j) + ", ");
                }
                System.out.println("");
                return;
            }
        }
        System.out.println("STUDENT NOT FOUND");
    }

    @Override
    public void showOneStudWithinDates(String startD, String endD){

    }

    @Override
    public void changeAttend(Boolean newVal) {

    }

    @Override
    public LocalDate readDate(String str){
        LocalDate date = LocalDate.parse(str);
        return date;
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
