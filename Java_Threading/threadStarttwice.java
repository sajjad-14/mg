class StartTwiceDemo extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }

    public static void main(String[] args) {
        StartTwiceDemo thread = new StartTwiceDemo();
        thread.start();  // Start the thread the first time
        try {
            thread.start();  // Attempt to start the thread a second time
        } catch (IllegalThreadStateException e) {
            System.out.println("Cannot start a thread twice.");
        }
    }
}
