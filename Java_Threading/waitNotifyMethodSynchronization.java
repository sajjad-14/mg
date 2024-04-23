class WaitNotifyDemo {
    private String message;

    public synchronized void writeMessage(String msg) {
        this.message = msg;
        notify();  // Notify waiting threads
    }

    public synchronized String readMessage() {
        while (this.message == null) {
            try {
                wait();  // Wait until message is available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        String msg = this.message;
        this.message = null;  // Reset message
        return msg;
    }

    public static void main(String[] args) {
        WaitNotifyDemo demo = new WaitNotifyDemo();

        // Thread to write a message
        Thread writer = new Thread(() -> {
            demo.writeMessage("Hello, World!");
        });

        // Thread to read a message
        Thread reader = new Thread(() -> {
            System.out.println("Message received: " + demo.readMessage());
        });

        reader.start();
        writer.start();
    }
}
