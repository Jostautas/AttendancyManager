import java.time.LocalDate;

public interface FileActions {
    void addStudent(String name);
    void addDate(LocalDate date);
    void showAllStudents();
    void showOneStudent();
    void save();
}
