class Inventory {
    private int stock = 50; // Assume initial stock

    public synchronized void updateStock(int soldItems) {
        if (stock >= soldItems) {
            System.out.println("Selling " + soldItems + " items");
            stock -= soldItems;
            System.out.println("Current stock: " + stock);
        } else {
            System.out.println("Insufficient stock to sell " + soldItems);
        }
    }
}

class Customer extends Thread {
    private Inventory inventory;
    private int purchaseAmount;

    public Customer(Inventory inventory, int purchaseAmount) {
        this.inventory = inventory;
        this.purchaseAmount = purchaseAmount;
    }

    public void run() {
        inventory.updateStock(purchaseAmount);
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Customer customer1 = new Customer(inventory, 10);
        Customer customer2 = new Customer(inventory, 5);
        customer1.start();
        customer2.start();
    }
}
