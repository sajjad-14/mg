class BankAccount {
    private int balance = 1000;

    // Synchronized method to handle withdrawal
    public synchronized boolean withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " is trying to withdraw " + amount);
            try {
                Thread.sleep(100); // simulate transaction delay
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance: " + balance);
            return true;
        }
        System.out.println("Insufficient funds for " + Thread.currentThread().getName() + " to withdraw " + amount + ". Current balance: " + balance);
        return false;
    }
}

class AccountHolder extends Thread {
    private BankAccount account;
    private int amount;

    public AccountHolder(BankAccount account, String name, int amount) {
        super(name);
        this.account = account;
        this.amount = amount;
    }

    public void run() {
        boolean result = account.withdraw(amount);
        if (!result) {
            System.out.println(Thread.currentThread().getName() + " could not complete the withdrawal due to insufficient funds.");
        }
    }
}

public class TransactionTest {
    public static void main(String[] args) {
        BankAccount sharedAccount = new BankAccount();
        AccountHolder holder1 = new AccountHolder(sharedAccount, "Account Holder 1", 500);
        AccountHolder holder2 = new AccountHolder(sharedAccount, "Account Holder 2", 700);
        holder1.start();
        holder2.start();
    }
}
