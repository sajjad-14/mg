import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Inventory {
    private Map<String, Integer> stockItems;

    public Inventory() {
        this.stockItems = new HashMap<>();
    }

    public synchronized void updateInventory(String item, int quantity) {
        int currentQty = stockItems.getOrDefault(item, 0);
        stockItems.put(item, currentQty + quantity);
        System.out.println("Updated " + item + " to " + (currentQty + quantity));
    }

    public void displayInventory() {
        System.out.println("Current Inventory:");
        stockItems.forEach((item, quantity) -> System.out.println(item + ": " + quantity));
    }
}

class InventoryUpdater implements Runnable {
    private Inventory inventory;
    private String item;
    private int quantity;

    public InventoryUpdater(Inventory inventory, String item, int quantity) {
        this.inventory = inventory;
        this.item = item;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        inventory.updateInventory(item, quantity);
    }
}

public class InventorySystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of updates:");
        int updates = scanner.nextInt();

        Thread[] threads = new Thread[updates];

        for (int i = 0; i < updates; i++) {
            System.out.println("Enter item name for update " + (i + 1) + ":");
            String item = scanner.next();
            System.out.println("Enter quantity for update " + (i + 1) + ":");
            int quantity = scanner.nextInt();

            threads[i] = new Thread(new InventoryUpdater(inventory, item, quantity));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }

        inventory.displayInventory();
        scanner.close();
    }
}
