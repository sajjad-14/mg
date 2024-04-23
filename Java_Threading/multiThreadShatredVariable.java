class SharedVariableDemo {
    private int sharedVariable = 0;

    // Synchronized method to modify the shared variable
    public synchronized void modifySharedVariable() {
        this.sharedVariable++;
        System.out.println("Shared Variable Updated: " + sharedVariable);
    }

    public static void main(String[] args) {
        SharedVariableDemo demo = new SharedVariableDemo();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                demo.modifySharedVariable();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                demo.modifySharedVariable();
            }
        });

        t1.start();
        t2.start();
    }
}
