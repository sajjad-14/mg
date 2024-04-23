class SynchronizedBlockDemo {
    private int balance = 100;

    // Method where a synchronized block is used
    public void withdraw(int amount) {
        synchronized (this) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawal of " + amount + " successful. New balance: " + balance);
            } else {
                System.out.println("Insufficient balance");
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedBlockDemo account = new SynchronizedBlockDemo();

        Thread t1 = new Thread(() -> account.withdraw(75));
        Thread t2 = new Thread(() -> account.withdraw(50));

        t1.start();
        t2.start();
    }
}
