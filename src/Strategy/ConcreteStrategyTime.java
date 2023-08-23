package Strategy;

import Server.Server;
import Task.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task task) {
        int minWaitingTime = Integer.MAX_VALUE;
        Server chosenServer = null;

        // find the server with the smallest waiting time
        for (Server server : servers) {
            int serverWaitingTime = server.getWaitingPeriod();
            if (serverWaitingTime < minWaitingTime) {
                minWaitingTime = serverWaitingTime;
                chosenServer = server;
            }
        }

        // add the task to the chosen server and update its wait time
        task.setWaitTime(minWaitingTime + task.getProcessingTime());
        assert chosenServer != null;
        chosenServer.addTask(task);
    }
}
