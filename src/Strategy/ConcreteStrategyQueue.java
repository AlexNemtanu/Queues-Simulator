package Strategy;

import Server.Server;
import Task.Task;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ConcreteStrategyQueue implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task task) {
        // Finds the server with the minimum number of tasks
        Server selectedServer = Collections.min(servers, Comparator.comparingInt(Server::getNumberOfTasks));
        // Adds the task to the selected server
        selectedServer.addTask(task);
    }
}