package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class Simulation extends JFrame {

    private final JLabel label;
    private final JLabel label1;
    private final JLabel time1;
    private final JLabel time2;
    private final JLabel time3;
    private final ArrayList<JLabel> queuesLabels = new ArrayList<>();


    public Simulation(int noOfQueues) {

        setBounds(100, 100, 450, 700);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(248, 255, 91 ));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel title = new JLabel("Simulation");
        title.setFont(new Font("Roboto Bono", Font.PLAIN, 20));
        title.setBounds(179, 10, 150, 23);
        contentPane.add(title);

        time1 = new JLabel("");
        time1.setFont(new Font("Roboto Bono", Font.PLAIN, 15));
        time1.setBounds(10, 570, 200, 20);
        contentPane.add(time1);

        time2 = new JLabel("");
        time2.setFont(new Font("Roboto Bono", Font.PLAIN, 15));
        time2.setBounds(10, 600, 200, 20);
        contentPane.add(time2);

        time3 = new JLabel("");
        time3.setFont(new Font("Roboto Bono", Font.PLAIN, 15));
        time3.setBounds(10, 630, 200, 20);
        contentPane.add(time3);

        JLabel wClients = new JLabel("Waiting Clients:");
        wClients.setFont(new Font("Roboto Bono", Font.PLAIN, 15));
        wClients.setBounds(10, 75, 122, 20);
        contentPane.add(wClients);

        label = new JLabel("");
        label.setFont(new Font("Roboto Bono", Font.PLAIN, 12));
        label.setBounds(142, 75, 284, 20);
        contentPane.add(label);

        JLabel simTime = new JLabel("Simulation Time:");
        simTime.setFont(new Font("Roboto Bono", Font.PLAIN, 15));
        simTime.setBounds(10, 45, 150, 20);
        contentPane.add(simTime);

        label1 = new JLabel("");
        label1.setFont(new Font("Roboto Bono", Font.PLAIN, 15));
        label1.setBounds(123, 45, 45, 20);
        contentPane.add(label1);
        int y = 105;
        for (int i = 0; i < noOfQueues; i++) {
            JLabel j = new JLabel("Queue" + (i + 1) + ": ");
            j.setFont(new Font("Roboto Bono", Font.PLAIN, 15));
            j.setBounds(10, y, 65, 20);
            contentPane.add(j);
            queuesLabels.add(new JLabel(""));
            queuesLabels.get(i).setFont(new Font("Roboto Bono", Font.PLAIN, 15));
            queuesLabels.get(i).setBounds(80, y, 350, 20);
            contentPane.add(queuesLabels.get(i));
            y += 30;
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void setWaintingClients(String a) {
        this.label.setText(a);
    }

    public void setSimTime(String a) {
        this.label1.setText(a);
    }

    public void setQueues(String a, int i) {
        this.queuesLabels.get(i).setText(a);
    }

    public void setAvgWaitTime(String a) {
        this.time1.setText(a);
    }

    public void setAvgProcTime(String a) {
        this.time2.setText(a);
    }

    public void setPeakHour(String a) {
        this.time3.setText(a);
    }


}
