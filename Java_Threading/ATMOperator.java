class BankAccount {
    private int balance = 1000;

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " is about to withdraw");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " has completed withdrawal");
        } else {
            System.out.println("Not enough balance for " + Thread.currentThread().getName() + " to withdraw " + amount);
        }
    }
}

class ATM extends Thread {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            account.withdraw(200);
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        ATM t1 = new ATM(account);
        ATM t2 = new ATM(account);
        t1.setName("Customer 1");
        t2.setName("Customer 2");
        t1.start();
        t2.start();
    }
}
