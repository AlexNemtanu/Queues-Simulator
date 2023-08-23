package Controller;

import Model.Model;
import Simulation.SimulationManager;
import View.View;

import javax.swing.*;
import java.io.IOException;

public class Controller {

    private final View view;
    private final Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        // Add an action listener for the "Start Simulation" button
        view.getSimStart().addActionListener(e -> simStartButtonPressed());
    }

    private void simStartButtonPressed() {
        // Initialize and set a variable for validating input data
        int valid = 0;
        valid += model.validator(view.getNoClients());
        valid += model.validator(view.getNoQueues());
        valid += model.validator(view.getSimTime());
        valid += model.validator(view.getMinServeTime());
        valid += model.validator(view.getMaxServeTime());
        valid += model.validator(view.getMinArrivalTime());
        valid += model.validator(view.getMaxArrivalTime());
            // If input data is not valid, show an error dialog
        if (valid != 0) {
            showErrorDialog();
            // Otherwise, create a SimulationManager object and start the simulation thread
        } else {
            SimulationManager gen = createSimulationManager();
            view.setVisible(false);
            startSimulationThread(gen);
        }
    }

    private void showErrorDialog() {
        JOptionPane.showMessageDialog(null,
                "Date invalide!",
                "Eroare!",
                JOptionPane.ERROR_MESSAGE);
    }

    private SimulationManager createSimulationManager() {
        SimulationManager gen = null;
        try {
            gen = new SimulationManager(
                    Integer.parseInt(view.getSimTime()),
                    Integer.parseInt(view.getMaxServeTime()),
                    Integer.parseInt(view.getMinServeTime()),
                    Integer.parseInt(view.getMaxArrivalTime()),
                    Integer.parseInt(view.getMinArrivalTime()),
                    Integer.parseInt(view.getNoQueues()),
                    Integer.parseInt(view.getNoClients()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return gen;
    }

    private void startSimulationThread(SimulationManager gen) {
        // Create a new thread for the simulation manager and start it
        Thread t = new Thread(gen);
        t.start();
    }
}
