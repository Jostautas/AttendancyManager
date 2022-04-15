import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class workWithData extends AbstractImplement{
    LocalDate startDate, endDate, date;

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
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public void showAllStudentsWDate(){
        int columnCounter = 0;
        for(int i = 0; i < DatesT.size(); i++){
            if(DatesT.get(i).isAfter(startDate) && DatesT.get(i).isBefore(endDate)){
                columnCounter++;
            }
        }

        String[] collumnNames = new String[columnCounter+1];
        collumnNames[0] = "Names";
        for(int i = 0, j = 1; i < DatesT.size(); i++){
            if(DatesT.get(i).isAfter(startDate) && DatesT.get(i).isBefore(endDate)) {
                collumnNames[j] = DatesT.get(i).toString();
                j++;
            }
        }

        String[][] data = new String[NamesT.size()][columnCounter+1];
        for(int i = 0; i < NamesT.size(); i++){
            data[i][0] = NamesT.get(i);
            List<Boolean> temp = new ArrayList<Boolean>();
            temp = AttendT.get(i);
            for(int j = 0, y = 1; j < DatesT.size(); j++){
                if(DatesT.get(j).isAfter(startDate) && DatesT.get(j).isBefore(endDate)) {
                    data[i][y] = temp.get(j).toString();
                    y++;
                }
            }
        }

        new SecondWindow(data, collumnNames);

    }

    @Override
    public void showOneStudWithinDates(String name){
        int columnCounter = 0;
        for(int i = 0; i < DatesT.size(); i++){
            if(DatesT.get(i).isAfter(startDate) && DatesT.get(i).isBefore(endDate)){
                columnCounter++;
            }
        }

        String[] collumnNames = new String[columnCounter+1];
        collumnNames[0] = "Names";
        for(int i = 0, j = 1; i < DatesT.size(); i++){
            if(DatesT.get(i).isAfter(startDate) && DatesT.get(i).isBefore(endDate)) {
                collumnNames[j] = DatesT.get(i).toString();
                j++;
            }
        }

        String[][] data = new String[NamesT.size()][DatesT.size()+1];
        for(int i = 0; i < NamesT.size(); i++){
            if(NamesT.get(i).equals(name)) {
                data[0][0] = NamesT.get(i);
                List<Boolean> temp = new ArrayList<Boolean>();
                temp = AttendT.get(i);
                for (int j = 0, y = 1; j < DatesT.size(); j++) {
                    if(DatesT.get(j).isAfter(startDate) && DatesT.get(j).isBefore(endDate)) {
                        data[0][y] = temp.get(j).toString();
                        y++;
                    }
                }
                new SecondWindow(data, collumnNames);
                return;
            }
        }
        System.out.println("ERROR - no such student");
    }

    @Override
    public void changeAttend(Boolean newVal, String name) {
        // check if date exists:
        int dateIndex = -1;
        for(int i = 0; i < DatesT.size(); i++){
            if(date.isEqual(DatesT.get(i))){
                dateIndex = i;
                break;
            }
        }
        if(dateIndex == -1){
            System.out.println("ERROR - no such date");
            return;
        }

        // check if student exists:
        int studIndex = -1;
        for(int i = 0; i < NamesT.size(); i++){
            if(name.equals(NamesT.get(i))){
                studIndex = i;
                break;
            }
        }
        if(studIndex == -1){
            System.out.println("ERROR - no such student");
            return;
        }

        // if all ok, set bool value in table:
        ArrayList<Boolean> temp = AttendT.get(studIndex);
        temp.set(dateIndex, newVal);
    }



    @Override
    public void save(String fileName) {
    }
    @Override
    public void readTable(String fileName) {
    }
}
