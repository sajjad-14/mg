class Counter {
    private int count = 0;

    // Synchronized method to ensure only one thread can access it at a time
    public synchronized void increment() {
        count++;  // Increment the shared counter
        System.out.println("Count after increment: " + count);
    }

    public static void main(String[] args) {
        Counter counter = new Counter();

        // Creating two threads that access the same Counter instance
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();
    }
}
