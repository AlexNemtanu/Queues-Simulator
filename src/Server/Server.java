package Server;

import Task.Task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private final BlockingQueue<Task> tasks;
    private final AtomicInteger waitingPeriod;
    private static float sumWait = 0;
    // Constructor for Server class, initializes BlockingQueue with specified size and waitingPeriod to 0
    public Server(int maxTasksPerServer) {
        tasks = new ArrayBlockingQueue<>(maxTasksPerServer);
        waitingPeriod = new AtomicInteger();
    }
    // Method to add a new Task to the BlockingQueue
    public void addTask(Task newTask) {
        tasks.offer(newTask);
        waitingPeriod.addAndGet(newTask.getProcessingTime());
    }

    @Override
    public void run() {
        // Loop infinitely until interrupted
        while (true) {
            // Check if there are tasks in the BlockingQueue
            if (!tasks.isEmpty()) {
                int x = tasks.peek().getProcessingTime();// Get the processing time of the first task in the queue
                if (tasks.peek() != null) {// Check if the first task is not null, and if so, add its wait time to the sumWait variable
                    sumWait += tasks.peek().getWaitTime();
                }
                try {
                    Thread.sleep((x * 1000L)); // Sleep the thread for the duration of the first task's processing time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tasks.poll();// Remove the first task from the BlockingQueue and subtract its processing time from the waitingPeriod
                waitingPeriod.addAndGet((-x));
            }
        }
    }
    // Method to convert the BlockingQueue of Task objects to an array and return it
    public Task[] getTasks() {
        Task[] passArray = new Task[tasks.size()];
        tasks.toArray(passArray);
        return passArray;
    }

    public int getWaitingPeriod() {
        return waitingPeriod.get();
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public static float getSumWait() {
        return sumWait;
    }

    public void clear()
    {
        tasks.clear();
    }

}
