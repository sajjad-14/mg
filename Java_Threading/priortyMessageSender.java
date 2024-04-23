import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Message implements Comparable<Message> {
    private String content;
    private int priority;

    public Message(String content, int priority) {
        this.content = content;
        this.priority = priority;
    }

    public String getContent() {
        return content;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Message other) {
        return Integer.compare(other.priority, this.priority); // Higher priority messages first
    }
}

class MessageProcessor implements Runnable {
    private Queue<Message> messageQueue;

    public MessageProcessor(Queue<Message> messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            Message message;
            synchronized (messageQueue) {
                while (messageQueue.isEmpty()) {
                    try {
                        messageQueue.wait(); // Wait if the queue is empty
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                message = messageQueue.poll(); // Get the highest priority message
            }
            processMessage(message);
        }
    }

    private void processMessage(Message message) {
        System.out.println("Processing message: " + message.getContent() + ", Priority: " + message.getPriority());
        // Simulate message processing
        try {
            Thread.sleep(100); // Simulate processing time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class MessageProcessingSystem {
    public static void main(String[] args) {
        Queue<Message> messageQueue = new PriorityQueue<>();
        ExecutorService executor = Executors.newFixedThreadPool(3); // Create a thread pool with 3 threads

        // Start message processors
        for (int i = 0; i < 3; i++) {
            executor.submit(new MessageProcessor(messageQueue));
        }

        // Add messages with different priorities
        messageQueue.add(new Message("Important message", 10));
        messageQueue.add(new Message("Urgent message", 20));
        messageQueue.add(new Message("Normal message", 5));

        // Notify waiting threads about new messages
        synchronized (messageQueue) {
            messageQueue.notifyAll();
        }

        // Shutdown the executor after processing all messages
        executor.shutdown();
    }
}
