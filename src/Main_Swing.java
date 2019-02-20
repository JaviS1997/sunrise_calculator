import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

public class Main_Swing extends JFrame {

    private JPanel panel1;
    private JButton bCalculate;
    private JLabel tfOutput;
    private JDateChooser date;
    private JSpinner spLatitude;
    private JSpinner spLongitude;
    private JSpinner spMinutesLatitude;
    private JSpinner spMinutesLongitude;
    private JSpinner spSecondsLatitude;
    private JSpinner spSecondsLongitude;


    public static void main(String[] args) {
        Main_Swing sw = new Main_Swing();
    }


    public Main_Swing() {
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.setPreferredSize(new Dimension(600, 400));

        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel2);

        final JLabel label1 = new JLabel();
        label1.setText("Sunrise Calculator");
        panel2.add(label1);

        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel3.setPreferredSize(new Dimension(600, 200));
        panel1.add(panel3);

        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel4.setPreferredSize(new Dimension(300, 47));
        panel3.add(panel4);


        final JLabel label2 = new JLabel();
        label2.setHorizontalAlignment(2);
        label2.setText("Latitude:");
        panel4.add(label2);


        SpinnerNumberModel modelLatitude = new SpinnerNumberModel(0, -90, 90, 1);
        spLatitude = new JSpinner(modelLatitude);
        panel4.add(spLatitude);
        SpinnerNumberModel modelMinutesLatitude = new SpinnerNumberModel(0, 0, 59, 1);
        spMinutesLatitude= new JSpinner(modelMinutesLatitude);
        panel4.add(spMinutesLatitude);
        SpinnerNumberModel modelSecondsLatitude = new SpinnerNumberModel(0, 0, 59, 1);
        spSecondsLatitude = new JSpinner(modelSecondsLatitude);
        panel4.add(spSecondsLatitude);

        final JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel5.setPreferredSize(new Dimension(300, 47));
        panel3.add(panel5);
        final JLabel label3 = new JLabel();
        label3.setHorizontalAlignment(2);
        label3.setText("Longitude:");
        panel5.add(label3);


        SpinnerNumberModel modelLongitude = new SpinnerNumberModel(0, -180, 180, 1);
        spLongitude = new JSpinner(modelLongitude);
        panel5.add(spLongitude);
        SpinnerNumberModel modelMinuteLongitude = new SpinnerNumberModel(0, 0, 59, 1);
        spMinutesLongitude = new JSpinner(modelMinuteLongitude);
        panel5.add(spMinutesLongitude);
        SpinnerNumberModel modelSecondsLongitude = new SpinnerNumberModel(0, 0, 59, 1);
        spSecondsLongitude = new JSpinner(modelSecondsLongitude);
        panel5.add(spSecondsLongitude);

        final JPanel datepanel = new JPanel();
        datepanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        datepanel.setPreferredSize(new Dimension(300, 47));
        panel3.add(datepanel);

        date = new JDateChooser();
        datepanel.add(date);

        final JPanel panel6 = new JPanel();
        panel6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel3.add(panel6);
        tfOutput = new JLabel();
        tfOutput.setPreferredSize(new Dimension(300, 47));
        tfOutput.setText("The sun rises at: ");
        panel6.add(tfOutput);

        bCalculate = new JButton();
        bCalculate.setText("Calculate");
        panel1.add(bCalculate);
        add(panel1);


        bCalculate.addActionListener(new CustomActionListener());



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sunrise Calculator");
        setSize(700, 400);
        setVisible(true);
    }



    class CustomActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String string_date = sdf.format(date.getDate());

            double Lat = (Integer) spLatitude.getValue();
            double MinutesLat = (Integer) spMinutesLatitude.getValue();
            double SecondsLat = (Integer) spSecondsLatitude.getValue();
            if(Lat>=0)
                Lat = Lat + (MinutesLat/60) + (SecondsLat/3600);
            else
                Lat = Lat - (MinutesLat/60) - (SecondsLat/3600);

            double Long = (Integer) spLongitude.getValue();
            double MinutesLong = (Integer) spMinutesLongitude.getValue();
            double SecondsLong = (Integer) spSecondsLongitude.getValue();
            if(Long>=0)
                Long = Long + (MinutesLong/60) + (SecondsLong/3600);
            else
                Long = Long - (MinutesLong/60) - (SecondsLong/3600);

            System.out.println("Converted Latitude : " + Lat + " \nConverted Longitude : " + Long);
            Connection connection;

            //Long = Long + ((Double) spMinuteLongitude.getValue() / 60) + ((Double) spSecondsLongitude.getValue() / 60);
            connection = new Connection(Double.toString(Lat), Double.toString(Long), string_date);

            String jsonData = connection.request();
            Sunrise sunrise = Parser.parse(jsonData);
            tfOutput.setText("The Sun Rises at " + sunrise.results.sunrise);
        }
    }

}