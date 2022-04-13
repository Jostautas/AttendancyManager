import java.time.LocalDate;
import java.util.ArrayList;

public abstract class AbstractImplement implements FileActions {
    ArrayList<LocalDate> DatesT = new ArrayList<LocalDate>();
    ArrayList<String> NamesT = new ArrayList<String>();
    ArrayList<ArrayList<Boolean>> AttendT = new ArrayList<ArrayList<Boolean>>();

    int nameCounter = 0;

    @Override
    public void showAllStudents() {
        System.out.println("aa");
        System.out.print("       ");
        for(int i = 0; i < DatesT.size(); i++){
            System.out.print(DatesT.get(i)+", ");
        }
        for(int i = 0; i < nameCounter; i++){
            System.out.print(NamesT.get(i) + ", ");
            System.out.println(AttendT.get(i));
            /*for(int j = 0; j < DatesT.size(); j++){
                System.out.print(AttendT.get(j).indexOf(i) + ", ");
            }*/
            System.out.println("");
        }
    }
}
