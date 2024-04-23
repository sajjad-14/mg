import java.util.Scanner;

class InsuranceAccount {
    private double amount;

    // Constructor to set the initial insurance amount
    public InsuranceAccount(double amount) {
        this.amount = amount;
    }

    // Synchronized method to modify the insurance amount
    public synchronized void transact(double amount) {
        if (this.amount + amount < 0) {
            System.out.println("Transaction declined: Insufficient funds");
        } else {
            this.amount += amount;
            System.out.printf("Transaction successful: Current balance is %.2f\n", this.amount);
        }
    }
}

class InsuranceTransaction implements Runnable {
    private InsuranceAccount account;
    private double amount;

    public InsuranceTransaction(InsuranceAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        account.transact(amount);
    }
}

public class Main {
    public static void main(String[] args) {
        InsuranceAccount account = new InsuranceAccount(100000); // Initial amount of 1 lakh
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of transactions:");
        int numberOfTransactions = scanner.nextInt();

        Thread[] threads = new Thread[numberOfTransactions];

        for (int i = 0; i < numberOfTransactions; i++) {
            System.out.println("Enter transaction amount for transaction " + (i + 1) + " (positive for deposit, negative for withdrawal):");
            double amount = scanner.nextDouble();

            // You can use either extends Thread or implements Runnable
            threads[i] = new Thread(new InsuranceTransaction(account, amount));
            threads[i].start();
        }

        // Waiting for all threads to finish
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
