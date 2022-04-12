import javax.swing.*;

public class SecondWindow extends JFrame{
    private JTable table;
    private JPanel secondPanel;

    public SecondWindow(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(secondPanel);
        this.pack();

    }
}
