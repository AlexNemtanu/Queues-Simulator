package Strategy;

import Server.Server;
import Task.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private final List<Server> servers;// List of servers available for scheduling tasks.
    private Strategy strategy;// Strategy used for task scheduling.

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.servers = new ArrayList<>();// Initializing the list of servers.
        // Creating the specified number of servers and starting a thread for each of them.
        for (int i = 0; i < maxNoServers; i++) {
            this.servers.add(new Server(maxTasksPerServer));
            Thread thread = new Thread(servers.get(i));
            thread.start();
        }
    }
    // Method for changing the scheduling strategy.
    public void changeStrategy(SelectionPolicy policy) {
        // If the selected policy is Shortest Queue, create a new ConcreteStrategyQueue object.
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            this.strategy = new ConcreteStrategyQueue();
        }
        // If the selected policy is Shortest Time, create a new ConcreteStrategyTime object.
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            this.strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchTask(Task t) throws InterruptedException {
        // Use the selected strategy to add the task to one of the servers.
        strategy.addTask(this.servers, t);
    }

    public ArrayList<Server> getServers() {
        return (ArrayList<Server>) this.servers;
    }
    // Method for checking if there are any tasks left to be executed.
    public boolean check() {
        int size = 0;
        // Counting the total number of tasks across all servers.
        for (Server server : servers)
            size += server.getNumberOfTasks();
        // If the total number of tasks is 0, there are no tasks left to be executed.
        return size == 0;
    }
}
