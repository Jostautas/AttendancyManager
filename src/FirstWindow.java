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
    private JButton PDFAllstudB;
    private JButton exportToPDFButton1;
    private JButton showOneB;
    private JButton showOneWDateB;
    private JButton exportToPDFButton2;
    private JButton exportToPDFButton3;
    private JTextField nameTF;
    private JButton addStudB;
    private JButton saveCSV_B;
    private JTextField dateTF;
    private JButton trueB;
    private JButton falseB;
    private JButton addDateB;
    private JButton saveXLS_B;

    ArrayList<LocalDate> DatesT = new ArrayList<LocalDate>();
    ArrayList<String> NamesT = new ArrayList<String>();
    ArrayList<ArrayList<Boolean>> AttendT = new ArrayList<ArrayList<Boolean>>();
    String openedFileName;

    public FirstWindow(String title) throws ParseException {
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
                if (extension.equals("csv")) {
                    openedFileName = fileName;

                    impl = new CSVImplement();
                    impl.readTable(fileName);
                    CheckConfirmL.setText("Opened Successfully");

                    DatesT = impl.getDatesT();
                    NamesT = impl.getNamesT();
                    AttendT = impl.getAttendT();
                } else if (extension.equals("xls")) {
                    openedFileName = fileName;

                    impl = new XLSImplement();
                    impl.readTable(fileName);
                    CheckConfirmL.setText("Opened Successfully");

                    DatesT = impl.getDatesT();
                    NamesT = impl.getNamesT();
                    AttendT = impl.getAttendT();
                } else {
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
                work.showAllStudents(false);
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
        showOneB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileActions work = new workWithData();
                work.setDatesT(DatesT);
                work.setNamesT(NamesT);
                work.setAttendT(AttendT);
                work.showOneStudent(nameTF.getText());
            }
        });
        showOneWDateB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileActions work = new workWithData();
                work.setDatesT(DatesT);
                work.setNamesT(NamesT);
                work.setAttendT(AttendT);

                work.setStartDate(work.readDate(startDateTF.getText()));
                work.setEndDate(work.readDate(endDateTF.getText()));

                work.showOneStudWithinDates(nameTF.getText());
            }
        });
        saveCSV_B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileActions impl = new CSVImplement();

                impl.setDatesT(DatesT);
                impl.setNamesT(NamesT);
                impl.setAttendT(AttendT);
                impl.save(openedFileName);
                System.out.println("changes saved");
            }
        });
        addStudB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileActions work = new workWithData();
                work.setDatesT(DatesT);
                work.setNamesT(NamesT);
                work.setAttendT(AttendT);

                work.addStudent(nameTF.getText());

                DatesT = work.getDatesT();
                NamesT = work.getNamesT();
                AttendT = work.getAttendT();
            }
        });
        trueB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileActions work = new workWithData();
                work.setDatesT(DatesT);
                work.setNamesT(NamesT);
                work.setAttendT(AttendT);

                work.setDate(work.readDate(dateTF.getText()));
                work.changeAttend(true, nameTF.getText());

                AttendT = work.getAttendT();
            }
        });
        falseB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileActions work = new workWithData();
                work.setDatesT(DatesT);
                work.setNamesT(NamesT);
                work.setAttendT(AttendT);

                work.setDate(work.readDate(dateTF.getText()));
                work.changeAttend(false, nameTF.getText());

                AttendT = work.getAttendT();
            }
        });
        addDateB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileActions work = new workWithData();
                work.setDatesT(DatesT);
                work.setNamesT(NamesT);
                work.setAttendT(AttendT);

                work.addDate(dateTF.getText());

                work.getDatesT();
                work.getNamesT();
                work.getAttendT();
            }
        });
        saveXLS_B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileActions impl = new XLSImplement();

                impl.setDatesT(DatesT);
                impl.setNamesT(NamesT);
                impl.setAttendT(AttendT);
                impl.save(openedFileName);
                System.out.println("changes saved");
            }
        });
        PDFAllstudB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileActions work = new workWithData();
                work.setDatesT(DatesT);
                work.setNamesT(NamesT);
                work.setAttendT(AttendT);
                work.showAllStudents(true);
            }
        });
    }

    public static void main(String[] args) throws ParseException {
        JFrame frame1 = new FirstWindow("Attendancy system");
        frame1.setVisible(true);
    }
}
