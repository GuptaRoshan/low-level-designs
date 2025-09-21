package problems;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Product entity
class Product {
    private final String id;
    private final String name;
    private final double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

// CartItem entity
class CartItem {
    private final Product product;
    private int quantity;
    private long lastUpdatedAt;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.lastUpdatedAt = System.currentTimeMillis();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.lastUpdatedAt = System.currentTimeMillis();
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public long getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    @Override
    public String toString() {
        return product.getName() + " x " + quantity + " = $" + getTotalPrice();
    }
}

@SuppressWarnings("all")
class Cart {
    private static final long EXPIRY_TIME = 30 * 60 * 1000; // 30 minutes
    private final String sessionID;
    private final Map<String, CartItem> items = new ConcurrentHashMap<>();

    Cart(String sessionID) {
        this.sessionID = sessionID;
    }

    // Add Item
    public void addItem(Product product, int quantity) {
        if (items.containsKey(product.getId())) {
            CartItem item = items.get(product.getId());
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            items.put(product.getId(), new CartItem(product, quantity));
        }
    }

    // Remove Item
    public void updateItemQuantity(Product product, int quantity) {
        if (items.containsKey(product.getId())) {
            if (quantity <= 0) {
                items.remove(product.getId());
            } else {
                items.get(product.getId()).setQuantity(quantity);
            }
        }
    }

    // Remove Item
    public void removeItem(Product product) {
        items.remove(product.getId());
    }

    public void clearOldItems() {
        long now = System.currentTimeMillis();

        for (Map.Entry<String, CartItem> entry : items.entrySet()) {
            if (now - entry.getValue().getLastUpdatedAt() > EXPIRY_TIME) {
                items.remove(entry.getKey());
            }
        }
    }

    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty");
        } else {
            items.values().forEach(System.out::println);
        }
    }

    public void checkout() {
    }
}

public class ShoppingCartService {
    private final Map<String, Cart> sessionManager = new ConcurrentHashMap<>();

    public Cart createSession(String sessionID) {
        return sessionManager.computeIfAbsent(sessionID, (key) -> new Cart(sessionID));
    }

    public void startExpiryScheduler() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            for (String session : sessionManager.keySet()) {
                sessionManager.get(session).clearOldItems();
            }
        }, 0, 5, TimeUnit.SECONDS);
    }



    // Main method to demonstrate functionality
    public static void main(String[] args) throws InterruptedException {
        ShoppingCartService service = new ShoppingCartService();

        // Start the expiry scheduler
        service.startExpiryScheduler();

        // Create some products
        Product p1 = new Product("101", "Laptop", 1000);
        Product p2 = new Product("102", "Headphones", 100);
        Product p3 = new Product("103", "Mouse", 50);

        // Create session 1
        Cart cart1 = service.createSession("session1");
        cart1.addItem(p1, 1);
        cart1.addItem(p2, 2);
        System.out.println("Cart1 contents:");
        cart1.viewCart();

        // Create session 2
        Cart cart2 = service.createSession("session2");
        cart2.addItem(p2, 1);
        cart2.addItem(p3, 3);
        System.out.println("Cart2 contents:");
        cart2.viewCart();

        // Simulate waiting so that some items expire
        System.out.println("Waiting 35 minutes for expiry simulation...");
        Thread.sleep(35 * 60 * 1000); // 35 minutes

        System.out.println("After expiry:");
        System.out.println("Cart1 contents:");
        cart1.viewCart();
        System.out.println("Cart2 contents:");
        cart2.viewCart();
    }

}
