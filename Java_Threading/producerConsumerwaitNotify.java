class ProducerConsumer {
    private int product;

    // Producer method
    public synchronized void produce() throws InterruptedException {
        while (product > 0) {
            wait();  // Wait if there is already product
        }
        product++;  // Produce a new product
        System.out.println("Produced: " + product);
        notify();  // Notify the consumer
    }

    // Consumer method
    public synchronized void consume() throws InterruptedException {
        while (product == 0) {
            wait();  // Wait if there is no product
        }
        System.out.println("Consumed: " + product);
        product--;  // Consume the product
        notify();  // Notify the producer
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();

        Thread producerThread = new Thread(() -> {
            try {
                while (true) {
                    pc.produce();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    pc.consume();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
