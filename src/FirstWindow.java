import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;


public class FirstWindow extends JFrame{
    private JLabel enterFileL;
    private JTextField fileNameTF;
    private JButton checkFileB;
    private JPanel mainPanel;

    // Data from table:
    ArrayList<LocalDate> DatesT = new ArrayList<LocalDate>();
    ArrayList<String> NamesT = new ArrayList<String>();
    ArrayList<Boolean> AttendT = new ArrayList<Boolean>();
    int nameCounter = 0;

    public FirstWindow(String title) throws ParseException{
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        checkFileB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String extension = "";
                String fileName = fileNameTF.getText();
                int index = fileName.lastIndexOf('.');
                if (index > 0) {
                    extension = fileName.substring(index + 1);
                }



                if(extension.equals("csv")){
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
                            nameCounter++;

                            int i = 1;
                            do{
                                Boolean b = Boolean.parseBoolean(nextLine[i]);
                                AttendT.add(b);
                                i++;
                            }while(i < DatesT.size());
                            for(String token : nextLine) {
                                //System.out.print(token+", ");

                            }
                            //System.out.println("");
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }



                else if(extension.equals("xls")){
                    System.out.println("XLS");
                }
                else{
                    System.out.println("ERROR - incorrect extension");
                }

                System.out.println("aa");
                System.out.print("       ");
                for(int i = 0; i < DatesT.size(); i++){
                    System.out.print(DatesT.get(i)+", ");
                }
                for(int i = 0; i < nameCounter; i++){
                    System.out.print(NamesT.get(i) + ", ");
                    for(int j = 0; j < DatesT.size(); j++){
                        System.out.print(AttendT.get(j) + ", ");
                    }
                    System.out.println("");
                }
                //JFrame frame2 = new SecondWindow("Attendancy system");
                //frame2.setVisible(true);
            }
        });


    }

    public static void main(String[] args) throws ParseException {
        JFrame frame1 = new FirstWindow("Attendancy system");
        frame1.setVisible(true);
        System.out.println("aaa");
    }
}
