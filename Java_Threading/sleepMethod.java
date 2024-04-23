// Demonstrate how to use sleep() method in a thread
class SleepDemo extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                // Sleep for 1000 milliseconds (1 second)
                Thread.sleep(1000);
                System.out.println(i);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e);
            }
        }
    }

    public static void main(String[] args) {
        SleepDemo thread = new SleepDemo();
        thread.start();
    }
}
