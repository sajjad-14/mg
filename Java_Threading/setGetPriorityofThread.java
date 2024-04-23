// Set and get the priority of a thread
class PriorityDemo extends Thread {
    public void run() {
        System.out.println("Running thread name: " + Thread.currentThread().getName() +
                           ", Priority: " + Thread.currentThread().getPriority());
    }

    public static void main(String[] args) {
        PriorityDemo t1 = new PriorityDemo();
        PriorityDemo t2 = new PriorityDemo();
        // Set the priority of threads
        t1.setPriority(MIN_PRIORITY); // Set to minimum priority
        t2.setPriority(MAX_PRIORITY); // Set to maximum priority
        t1.start();
        t2.start();
    }
}
