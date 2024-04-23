import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized boolean transact(double amount) {
        if (this.balance + amount < 0) {
            System.out.println(Thread.currentThread().getName() + " - Transaction declined: Insufficient funds");
            return false;
        } else {
            this.balance += amount;
            System.out.println(Thread.currentThread().getName() + " - Transaction successful: New balance: $" + balance);
            return true;
        }
    }
}

class TransactionThread extends Thread {
    private BankAccount account;
    private double amount;

    public TransactionThread(BankAccount account, double amount, String threadName) {
        super(threadName);
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        account.transact(amount);
    }
}

public class BankSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter initial bank account balance:");
        double initialBalance = scanner.nextDouble();

        BankAccount account = new BankAccount(initialBalance);
        System.out.println("Enter the number of transactions:");
        int numberOfTransactions = scanner.nextInt();

        TransactionThread[] threads = new TransactionThread[numberOfTransactions];

        for (int i = 0; i < numberOfTransactions; i++) {
            System.out.println("Enter transaction amount for transaction " + (i + 1) + " (positive for deposit, negative for withdrawal):");
            double amount = scanner.nextDouble();
            threads[i] = new TransactionThread(account, amount, "Transaction Thread " + (i + 1));
            threads[i].start();
        }

        for (int i = 0; i < numberOfTransactions; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
