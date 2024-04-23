import java.util.PriorityQueue;
import java.util.Queue;

class Task implements Runnable, Comparable<Task> {
    private String name;
    private int priority;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public void run() {
        System.out.println("Executing task: " + name + ", Priority: " + priority + ", Thread: " + Thread.currentThread().getName());
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(other.priority, this.priority); // Higher priority tasks first
    }
}

class TaskScheduler {
    private Queue<Task> taskQueue;

    public TaskScheduler() {
        taskQueue = new PriorityQueue<>();
    }

    public void scheduleTask(Task task) {
        taskQueue.add(task);
    }

    public void start() {
        while (!taskQueue.isEmpty()) {
            Task task = taskQueue.poll();
            Thread thread = new Thread(task);
            thread.setName(task.getName());
            thread.setPriority(task.getPriority());
            thread.start();
        }
    }
}

public class TaskSchedulerExample {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        // Adding tasks with different priorities
        scheduler.scheduleTask(new Task("Task 1", Thread.MAX_PRIORITY));
        scheduler.scheduleTask(new Task("Task 2", Thread.NORM_PRIORITY));
        scheduler.scheduleTask(new Task("Task 3", Thread.MIN_PRIORITY));

        // Start the scheduler
        scheduler.start();
    }
}
