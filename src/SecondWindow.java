import javax.swing.*;

public class SecondWindow extends JFrame{
    private JTable table;
    private JFrame frame;
    //private JPanel secondPanel;

    public SecondWindow(String[][] data, String[] collumnNames){
        frame = new JFrame();
        frame.setTitle("Student table");

        table = new JTable(data, collumnNames);
        table.setBounds(30, 40, 200, 300);

        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);
        frame.setSize(1500, 800);
        frame.setVisible(true);

    }
}
