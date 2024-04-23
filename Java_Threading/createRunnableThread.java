// Define a class that implements the Runnable interface
class RunnableDemo implements Runnable {
    // This method runs when the thread starts
    public void run() {
        System.out.println("Thread is running...");
    }

    public static void main(String[] args) {
        // Create an instance of RunnableDemo
        RunnableDemo runnable = new RunnableDemo();
        // Create a Thread object and pass the Runnable object
        Thread thread = new Thread(runnable);
        // Start the thread
        thread.start();
    }
}
