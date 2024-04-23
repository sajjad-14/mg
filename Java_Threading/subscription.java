import java.util.Scanner;

class SubscriptionAccount {
    private double balance;

    public SubscriptionAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited $" + amount + ". New balance: $" + balance);
    }

    public synchronized void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Withdrawal failed: Not enough balance.");
        } else {
            balance -= amount;
            System.out.println("Withdrew $" + amount + ". Remaining balance: $" + balance);
        }
    }

    public synchronized void payForPremium(double amount) {
        if (amount > balance) {
            System.out.println("Payment for premium failed: Not enough balance.");
        } else {
            balance -= amount;
            System.out.println("Paid $" + amount + " for premium. Remaining balance: $" + balance);
        }
    }
}

class WithdrawThread extends Thread {
    private SubscriptionAccount account;
    private double amount;

    public WithdrawThread(SubscriptionAccount account, double amount) {
        this.account = account;
        this.amount = amount;
        this.setPriority(MAX_PRIORITY); // Highest priority
    }

    @Override
    public void run() {
        account.withdraw(amount);
    }
}

class DepositThread extends Thread {
    private SubscriptionAccount account;
    private double amount;

    public DepositThread(SubscriptionAccount account, double amount) {
        this.account = account;
        this.amount = amount;
        this.setPriority(NORM_PRIORITY); // Normal priority
    }

    @Override
    public void run() {
        account.deposit(amount);
    }
}

class PremiumThread extends Thread {
    private SubscriptionAccount account;
    private double amount;

    public PremiumThread(SubscriptionAccount account, double amount) {
        this.account = account;
        this.amount = amount;
        this.setPriority(MIN_PRIORITY); // Lowest priority
    }

    @Override
    public void run() {
        account.payForPremium(amount);
    }
}

public class SubscriptionManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();

        SubscriptionAccount account = new SubscriptionAccount(initialBalance);

        // Creating threads for each operation
        System.out.print("Enter amount to withdraw: ");
        double withdrawAmount = scanner.nextDouble();
        WithdrawThread withdrawThread = new WithdrawThread(account, withdrawAmount);

        System.out.print("Enter amount to deposit: ");
        double depositAmount = scanner.nextDouble();
        DepositThread depositThread = new DepositThread(account, depositAmount);

        System.out.print("Enter premium subscription fee: ");
        double premiumAmount = scanner.nextDouble();
        PremiumThread premiumThread = new PremiumThread(account, premiumAmount);

        // Starting threads
        depositThread.start();
        withdrawThread.start();
        premiumThread.start();

        // Joining threads to ensure all operations complete before terminating
        try {
            depositThread.join();
            withdrawThread.join();
            premiumThread.join();
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted: " + e.getMessage());
        }

        scanner.close();
    }
}
