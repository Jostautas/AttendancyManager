import java.time.LocalDate;
import java.util.ArrayList;

public interface FileActions {
    void addStudent(String newName);
    void addDate(String str);
    void showAllStudents();
    void showOneStudent(String name);
    void showOneStudWithinDates(String name);
    void changeAttend(Boolean newVal);
    void save(String fileName);
    void readTable(String fileName);

    LocalDate readDate(String str);
    void showAllStudentsWDate();
    ArrayList<LocalDate> getDatesT();
    ArrayList<String> getNamesT();
    ArrayList<ArrayList<Boolean>> getAttendT();

    void setDatesT(ArrayList<LocalDate> DatesT);
    void setNamesT(ArrayList<String> NamesT);
    void setAttendT(ArrayList<ArrayList<Boolean>> AttendT);
    void setStartDate(LocalDate startDate);
    void setEndDate(LocalDate endDate);

}
