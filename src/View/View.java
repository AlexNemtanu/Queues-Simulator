package View;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private final JTextField noClients;
    private final JTextField noQueues;
    private final JTextField simTime;
    private final JTextField minArrivalTime;
    private final JTextField maxArrivalTime;
    private final JTextField minServeTime;
    private final JTextField maxServeTime;
    private final JButton simStart;

    public View() {



        JFrame frame = new JFrame("Queues Simulator");
        frame.getContentPane().setBackground(new Color(248, 255, 91  ));
        frame.setBounds(75, 75, 275, 420);
        frame.getContentPane().setLayout(null);

        JLabel title = new JLabel("Queues Simulator:");
        title.setFont(new Font("Roboto Bono", Font.PLAIN, 20));
        title.setBounds(52, 10, 182, 27);
        title.setForeground(Color.BLACK);
        frame.getContentPane().add(title);

        JLabel clients = new JLabel("Number of Clients:");
        clients.setFont(new Font("RobotoBono", Font.PLAIN, 15));
        clients.setBounds(20, 74, 202, 25);
        clients.setForeground(Color.BLACK);
        frame.getContentPane().add(clients);

        this.noClients = new JTextField();
        this.noClients.setBounds(173, 78, 71, 19);
        this.noClients.setBackground(new Color(97, 253, 72 ));
        frame.getContentPane().add(this.noClients);
        this.noClients.setColumns(10);

        JLabel queues = new JLabel("Number of Queues:");
        queues.setFont(new Font("Roboto Bono", Font.PLAIN, 15));
        queues.setBounds(20, 114, 202, 25);
        queues.setForeground(Color.BLACK);
        frame.getContentPane().add(queues);

        this.noQueues = new JTextField();
        this.noQueues.setColumns(10);
        this.noQueues.setBounds(173, 118, 71, 19);
        this.noQueues.setBackground(new Color(97, 253, 72 ));
        frame.getContentPane().add(this.noQueues);

        JLabel simulationTime = new JLabel("Simulation Time:");
        simulationTime.setFont(new Font("Roboto Bono", Font.PLAIN, 15));
        simulationTime.setBounds(20, 149, 117, 25);
        simulationTime.setForeground(Color.BLACK);
        frame.getContentPane().add(simulationTime);

        simTime = new JTextField();
        simTime.setColumns(10);
        simTime.setBounds(173, 153, 71, 19);
        simTime.setBackground(new Color(97, 253, 72 ));
        frame.getContentPane().add(simTime);

        JLabel minArrTime = new JLabel("Min arrival time:");
        minArrTime.setFont(new Font("Roboto Bono", Font.PLAIN, 15));
        minArrTime.setBounds(20, 185, 143, 25);
        minArrTime.setForeground(Color.BLACK);
        frame.getContentPane().add(minArrTime);

        minArrivalTime = new JTextField();
        minArrivalTime.setColumns(10);
        minArrivalTime.setBounds(173, 189, 71, 19);
        minArrivalTime.setBackground(new Color(97, 253, 72 ));
        frame.getContentPane().add(minArrivalTime);

        JLabel maxArrTime = new JLabel("Max arrival time:");
        maxArrTime.setFont(new Font("Roboto Bono", Font.PLAIN, 15));
        maxArrTime.setBounds(20, 221, 155, 25);
        maxArrTime.setForeground(Color.BLACK);
        frame.getContentPane().add(maxArrTime);

        maxArrivalTime = new JTextField();
        maxArrivalTime.setColumns(10);
        maxArrivalTime.setBounds(173, 225, 71, 19);
        maxArrivalTime.setBackground(new Color(97, 253, 72 ));
        frame.getContentPane().add(maxArrivalTime);

        JLabel minServing = new JLabel("Min serving time:");
        minServing.setFont(new Font("Roboto Bono", Font.PLAIN, 15));
        minServing.setBounds(20, 256, 155, 25);
        minServing.setForeground(Color.BLACK);
        frame.getContentPane().add(minServing);

        minServeTime = new JTextField();
        minServeTime.setColumns(10);
        minServeTime.setBounds(173, 260, 71, 19);
        minServeTime.setBackground(new Color(97, 253, 72 ));
        frame.getContentPane().add(minServeTime);

        JLabel maxServing = new JLabel("Max serving time:");
        maxServing.setFont(new Font("Roboto Bono", Font.PLAIN, 15));
        maxServing.setBounds(20, 291, 155, 25);
        maxServing.setForeground(Color.BLACK);
        frame.getContentPane().add(maxServing);

        maxServeTime = new JTextField();
        maxServeTime.setColumns(10);
        maxServeTime.setBounds(173, 295, 71, 19);
        maxServeTime.setBackground(new Color(97, 253, 72 ));
        frame.getContentPane().add(maxServeTime);

        simStart = new JButton("Simulation start");
        simStart.setBounds(52, 346, 154, 27);
        simStart.setBackground(new Color(97, 253, 72 ));
        frame.getContentPane().add(simStart);

        simStart.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
        setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }

    public JButton getSimStart() {
        return this.simStart;
    }

    public String getNoClients() {
        return this.noClients.getText();
    }

    public String getNoQueues() {
        return this.noQueues.getText();
    }

    public String getSimTime() {
        return this.simTime.getText();
    }

    public String getMinArrivalTime() {
        return this.minArrivalTime.getText();
    }

    public String getMaxArrivalTime() {
        return this.maxArrivalTime.getText();
    }

    public String getMinServeTime() {
        return this.minServeTime.getText();
    }

    public String getMaxServeTime() {
        return this.maxServeTime.getText();
    }


}
