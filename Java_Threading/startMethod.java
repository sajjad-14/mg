// The start() method is used to begin the execution of the thread
class StartDemo extends Thread {
    public void run() {
        System.out.println("Thread is running...");
    }

    public static void main(String[] args) {
        StartDemo thread = new StartDemo();
        // Calling start() method to begin execution of the thread
        thread.start();
    }
}
