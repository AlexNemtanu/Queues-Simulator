package Simulation;

import Strategy.Scheduler;
import Strategy.SelectionPolicy;
import Task.Task;
import View.Simulation;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Server.Server.getSumWait;

public class SimulationManager implements Runnable {
    private List<Task> generatedClients;
    private final int numberOfClients;
    private final int timeLimit;
    private final int maxProcessingTime;
    private final int minProcessingTime;
    private final int maxArrivalTime;
    private final int minArrivalTime;
    private final Writer myWriter;
    private float avgProcess;
    private int max;
    private float peakHour;
    private final Scheduler scheduler;
    private final Simulation frame;

    public SimulationManager(int simTime, int maxProcTime, int minProcTime, int maxArrivalTime, int minArrivalTime, int numberOfQueues, int clients) throws IOException {
        this.numberOfClients = clients;
        this.maxProcessingTime = maxProcTime;
        this.minProcessingTime = minProcTime;
        this.timeLimit = simTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.myWriter = new FileWriter("events.txt");
        int maxClientsPerServer = 1000;
        this.scheduler = new Scheduler(numberOfQueues, maxClientsPerServer);
        SelectionPolicy selection = SelectionPolicy.SHORTEST_TIME;
        this.scheduler.changeStrategy(selection);
        this.max = 0;
        frame = new Simulation(numberOfQueues);
        frame.setVisible(true);
        this.generateNRandomTasks();
    }

    public void generateNRandomTasks() {
        Random r = new Random();
        this.generatedClients = new ArrayList<>();  // Create an ArrayList to store the generated clients.
        // Loop through N times and add a new Task to the ArrayList with a random arrival time and processing time.
        for (int i = 0; i < numberOfClients; i++)
            generatedClients.add(new Task(r.nextInt(this.maxArrivalTime - this.minArrivalTime) + this.minArrivalTime,
                    r.nextInt(this.maxProcessingTime - this.minProcessingTime) + this.minProcessingTime));

        // Sort the ArrayList of clients by arrival time.
        for (int i = 0; i < numberOfClients - 1; i++) {
            for (int j = i + 1; j < numberOfClients; j++)
                if (generatedClients.get(i).getArrivalTime() > generatedClients.get(j).getArrivalTime()) {
                    Task aux = generatedClients.get(i);
                    generatedClients.set(i, generatedClients.get(j));
                    generatedClients.set(j, aux);
                }
        }
    // Calculate the average processing time for all generated clients
        for (int i = 0; i < numberOfClients; i++)
            avgProcess += generatedClients.get(i).getProcessingTime();

        avgProcess = avgProcess / numberOfClients;
    }


    @Override
    public void run() {
        int currentTime = 0;
        StringBuilder printQueues;
        StringBuilder waitingClients;
        while (currentTime <= timeLimit) {
            try {
                myWriter.write("Simulation Time: " + currentTime + "\n");// Write the current simulation time to the output file
            } catch (IOException e) {
                e.printStackTrace();
            }
            int sum = 0;// Initialize a sum variable for the waiting period of all servers
            // Iterate through all generated clients
            for (int i = 0; i < generatedClients.size(); i++) {
                // If a client has arrived at the current time, dispatch it to a server and remove it from the generatedClients list
                if (generatedClients.get(i).getArrivalTime() == currentTime) {
                    try {
                        scheduler.dispatchTask(generatedClients.get(i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    generatedClients.remove(i);
                    i--;
                }
            }
            // Iterate through all servers and calculate the total waiting period
            for(int i = 0; i < scheduler.getServers().size(); i++) {
                sum += scheduler.getServers().get(i).getWaitingPeriod();
            }
            // If the total waiting period is greater than the current maximum, update the maximum and peakHour
            if(max < sum)
            {
                max = sum;
                peakHour = currentTime;
            }
            // Update the simulation frame with the current simulation time and waiting clients
            frame.setSimTime(String.valueOf(currentTime));
            waitingClients = new StringBuilder();
            for(Task generatedTask : generatedClients)
            {
                waitingClients.append(generatedTask.toString());
            }
            frame.setWaintingClients(waitingClients.toString());

            try{
                myWriter.write("Waiting Clients: "+ waitingClients + "\n" + "\n");
            }catch(IOException e)
            {
                e.printStackTrace();
            }
            // Iterate through all servers and update the queue for each server in the simulation frame
            for(int i =0; i<scheduler.getServers().size(); i++)
            {
                printQueues = new StringBuilder();
                for(int j = 0; j<scheduler.getServers().get(i).getNumberOfTasks(); j++)
                {
                    printQueues.append(scheduler.getServers().get(i).getTasks()[j].toString());
                }
                frame.setQueues(printQueues.toString(), i);
                try{
                    myWriter.write("Queue " + (i+1) + ": "+ printQueues + "\n");
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
            // Iterate through all servers and decrement the processing time for the first task in each queue
            for(int i =0; i<scheduler.getServers().size(); i++)
            {
                if (scheduler.getServers().get(i).getNumberOfTasks() !=0){
                    if(scheduler.getServers().get(i).getTasks()[0].getProcessingTime() != 0) {
                        scheduler.getServers().get(i).getTasks()[0].decrementProcessingTime();
                    }
                }
            }
            try{
                myWriter.write("\n");
            }catch(IOException e)
            {
                e.printStackTrace();
            }
            currentTime++;
            // If there are no more generated clients and all servers are empty, end the simulation
            if(generatedClients.isEmpty() && scheduler.check())
            {
                break;
            }

            try{
                Thread.sleep(1000);
            }catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        for(int i=0; i<scheduler.getServers().size(); i++)
        {
            if(scheduler.getServers().get(i).getNumberOfTasks()!=0)
            {
                scheduler.getServers().get(i).clear();
            }
        }
        printQueues = new StringBuilder("Simulation finished");

        for(int i = 0; i<scheduler.getServers().size(); i++)
        {
            frame.setQueues(printQueues.toString(), i);
            try{
                myWriter.write("Queue " + i + ": " + printQueues + "\n");
            }catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        try{
            myWriter.write("Simulation finished");
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        try{
            myWriter.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        frame.setAvgWaitTime("Average waiting time: " + (getSumWait() / numberOfClients));
        frame.setAvgProcTime("Average processing time: "+ avgProcess);
        frame.setPeakHour("Peak Hour: " + peakHour);
        Thread.currentThread().interrupt();
    }
}
