class InterruptDemo extends Thread {
    public void run() {
        try {
            Thread.sleep(2000);  // Simulate time-consuming task
            System.out.println("Task completed");
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted");
        }
    }

    public static void main(String[] args) {
        InterruptDemo thread = new InterruptDemo();
        thread.start();
        thread.interrupt();  // Interrupt the thread while sleeping
    }
}
