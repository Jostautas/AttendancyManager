import java.time.LocalDate;
import java.util.ArrayList;

public class WriteToPDF {
    ArrayList<LocalDate> DatesT = new ArrayList<LocalDate>();
    ArrayList<String> NamesT = new ArrayList<String>();
    ArrayList<ArrayList<Boolean>> AttendT = new ArrayList<ArrayList<Boolean>>();

    public WriteToPDF(ArrayList<LocalDate> DatesT, ArrayList<String> NamesT, ArrayList<ArrayList<Boolean>> AttendT){
        setDatesT(DatesT);
        setNamesT(NamesT);
        setAttendT(AttendT);

    }

    public void setDatesT(ArrayList<LocalDate> DatesT) {
        this.DatesT = DatesT;
    }
    public void setNamesT(ArrayList<String> NamesT) {
        this.NamesT = NamesT;
    }
    public void setAttendT(ArrayList<ArrayList<Boolean>> AttendT) {
        this.AttendT = AttendT;
    }
}
