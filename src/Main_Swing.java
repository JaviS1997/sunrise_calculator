import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main_Swing extends JFrame {
    JFrame frame;
    JLabel label1;
    JLabel latLabel;
    JLabel longLabel;
    JLabel coord;
    JTextField latTxtfield;
    JTextField longTxtfield;
    JButton calculate;


    public static void main(String[] args) {
        Main_Swing sw = new Main_Swing();
    }

    public Main_Swing()
    {
        setTitle("Sunrise Calculator");
        setSize(400, 120);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("Sunrise Calculator");
        latLabel = new JLabel("Latitude:");
        longLabel = new JLabel("Longitude:");
        coord = new JLabel("The sun rises at ");

        latTxtfield = new JTextField(5);
        longTxtfield = new JTextField(5);
        calculate = new JButton("Calculate");

        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();


        panel.add(label1);
        panel2.add(latLabel);
        panel2.add(latTxtfield);
        panel2.add(longLabel);
        panel2.add(longTxtfield);
        panel3.add(coord);
        panel3.add(calculate);
        add(panel,BorderLayout.NORTH);
        add(panel2,BorderLayout.CENTER);
        add(panel3,BorderLayout.SOUTH);
        add(panel3,BorderLayout.SOUTH);


        setVisible(true);
    }

    class CustomActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

        }
    }

}