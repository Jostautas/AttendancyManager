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

    /*// Data from table:
    ArrayList<LocalDate> DatesT = new ArrayList<LocalDate>();
    ArrayList<String> NamesT = new ArrayList<String>();
    ArrayList<Boolean> AttendT = new ArrayList<Boolean>();
    int nameCounter = 0;*/

    public FirstWindow(String title) throws ParseException{
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        checkFileB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // check extension:
                String extension = "";
                String fileName = fileNameTF.getText();
                int index = fileName.lastIndexOf('.');
                if (index > 0) {
                    extension = fileName.substring(index + 1);
                }

                FileActions impl;
                if(extension.equals("csv")) {
                    impl = new CSVImplement();
                    impl.readTable(fileName);
                    impl.showAllStudents();
                    //impl.save(fileName);
                }


                else if(extension.equals("xls")){
                    System.out.println("XLS");
                }
                else{
                    System.out.println("ERROR - incorrect extension");
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
