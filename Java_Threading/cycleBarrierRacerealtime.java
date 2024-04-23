import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    // Runnable task for each thread
    private static class Task implements Runnable {
        private CyclicBarrier barrier;

        public Task(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting at the barrier.");
                barrier.await();  // Waiting at the barrier
                System.out.println(Thread.currentThread().getName() + " has crossed the barrier.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final int NUM_THREADS = 3;  // Number of threads to wait at the barrier

        CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS, new Runnable() {
            @Override
            public void run() {
                // This task will be executed once all threads reach the barrier
                System.out.println("All participants have arrived at the barrier, let the race begin!");
            }
        });

        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Task(barrier), "Runner " + (i + 1));
            threads[i].start();
        }
    }
}
