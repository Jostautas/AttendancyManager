import java.time.LocalDate;
import java.util.ArrayList;

public interface FileActions {
    void addStudent(String newName);
    void addDate(String str);
    void showAllStudents(Boolean choice); // choice = false - shows table in new window. true - prints to pdf
    void showOneStudent(String name, Boolean choice);
    void showOneStudWithinDates(String name, Boolean choice);
    void changeAttend(Boolean newVal, String name);
    void save(String fileName);
    void readTable(String fileName);
    LocalDate readDate(String str);
    void showAllStudentsWDate(Boolean choice);
    ArrayList<LocalDate> getDatesT();
    ArrayList<String> getNamesT();
    ArrayList<ArrayList<Boolean>> getAttendT();

    void setDatesT(ArrayList<LocalDate> DatesT);
    void setNamesT(ArrayList<String> NamesT);
    void setAttendT(ArrayList<ArrayList<Boolean>> AttendT);
    void setStartDate(LocalDate startDate);
    void setEndDate(LocalDate endDate);
    void setDate(LocalDate date);

}
