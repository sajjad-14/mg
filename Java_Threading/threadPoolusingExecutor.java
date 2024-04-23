import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable {
    private int taskNumber;

    public Task(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is executing task " + taskNumber);
    }
}

public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);  // Creating a pool of 4 threads

        for (int i = 1; i <= 10; i++) {
            Runnable task = new Task(i);
            executor.execute(task);  // Adding tasks to the thread pool
        }

        executor.shutdown();  // Shuts down the executor service
        while (!executor.isTerminated()) {
            // Waiting for all tasks to finish
        }
        System.out.println("Finished all tasks");
    }
}
