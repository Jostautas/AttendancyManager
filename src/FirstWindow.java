import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;


public class FirstWindow extends JFrame{
    private JLabel enterFileL;
    private JTextField fileNameTF;
    private JButton checkFileB;
    private JPanel mainPanel;
    private JTextField startDateTF;
    private JTextField endDateTF;
    private JButton showAllDateB;
    private JButton ShowAllB;
    private JLabel CheckConfirmL;
    private JButton exportToPDFButton;
    private JButton exportToPDFButton1;
    private JButton showButton;
    private JButton showButton1;
    private JButton exportToPDFButton2;
    private JButton exportToPDFButton3;
    private JTextField nameSurnameTextField;
    private JButton addButton;
    private JTextField textField1;
    private JButton saveButton;
    private JTextField nameSurnameTextField1;
    private JTextField dateTextField;
    private JButton trueButton;
    private JButton falseButton;

    ArrayList<LocalDate> DatesT = new ArrayList<LocalDate>();
    ArrayList<String> NamesT = new ArrayList<String>();
    ArrayList<ArrayList<Boolean>> AttendT = new ArrayList<ArrayList<Boolean>>();

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
                    CheckConfirmL.setText("Opened Successfully");

                    DatesT = impl.getDatesT();
                    NamesT = impl.getNamesT();
                    AttendT = impl.getAttendT();
                }


                else if(extension.equals("xls")){
                    System.out.println("XLS");
                }
                else{
                    System.out.println("ERROR - incorrect extension");
                    CheckConfirmL.setText("ERROR");
                }

            }
        });


        ShowAllB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileActions work = new workWithData();
                work.setDatesT(DatesT);
                work.setNamesT(NamesT);
                work.setAttendT(AttendT);
                work.showAllStudents();
            }
        });
        showAllDateB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileActions work = new workWithData();
                work.setDatesT(DatesT);
                work.setNamesT(NamesT);
                work.setAttendT(AttendT);

                work.setStartDate(work.readDate(startDateTF.getText()));
                work.setEndDate(work.readDate(endDateTF.getText()));

                work.showAllStudentsWDate();
            }
        });
    }

    public static void main(String[] args) throws ParseException {
        JFrame frame1 = new FirstWindow("Attendancy system");
        frame1.setVisible(true);
        System.out.println("aaa");
    }
}
