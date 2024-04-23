public class Singleton {
    private static volatile Singleton instance;  // Volatile variable to ensure changes are visible across threads

    private Singleton() {}  // Private constructor to prevent instantiation

    public static Singleton getInstance() {
        if (instance == null) {  // First check (no locking)
            synchronized (Singleton.class) {  // Synchronize on the class object
                if (instance == null) {  // Second check (with locking)
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
