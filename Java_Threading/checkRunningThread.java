// Check if a thread is still running using isAlive() method
class AliveDemo extends Thread {
    public void run() {
        System.out.println("Starting thread...");
        try {
            Thread.sleep(500); // Sleep for 500 milliseconds
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e);
        }
        System.out.println("Thread ending...");
    }

    public static void main(String[] args) {
        AliveDemo thread = new AliveDemo();
        thread.start();
        // Check if the thread is alive
        if (thread.isAlive()) {
            System.out.println("Thread is still running");
        } else {
            System.out.println("Thread has finished running");
        }
    }
}
