// Demonstrate the use of join() method to wait for a thread to finish
class JoinDemo extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                // Sleep for 500 milliseconds
                Thread.sleep(500);
                System.out.println(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Create two instances of JoinDemo
        JoinDemo t1 = new JoinDemo();
        JoinDemo t2 = new JoinDemo();
        
        // Start the first thread
        t1.start();
        
        try {
            // Main thread waits for t1 to finish
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Start the second thread after the first thread finishes
        t2.start();
    }
}
