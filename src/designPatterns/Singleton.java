package designPatterns;


public class Singleton {

    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}


// Double locking mechanism

class Singleton0 {
    private static volatile Singleton0 instance; // Volatile ensures visibility of changes across threads

    private Singleton0() {
    }

    public static Singleton0 getInstance() {
        if (instance == null) {  // First check (no locking)
            synchronized (Singleton.class) {  // Locking to ensure only one thread enters
                if (instance == null) {  // Second check to ensure instance is still null
                    instance = new Singleton0();
                }
            }
        }
        return instance;
    }
}


class Singleton1 {
    private static final Singleton1 INSTANCE = new Singleton1();  // eager initialization

    private Singleton1() { }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}