// Define a class that extends the Thread class
class MyThread extends Thread {
    // This method runs when the thread starts
    public void run() {
        System.out.println("Thread is running...");
    }
    
    public static void main(String[] args) {
        // Create an instance of MyThread
        MyThread thread = new MyThread();
        // Start the execution of the thread
        thread.start();
    }
}
