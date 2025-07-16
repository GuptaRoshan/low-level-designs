package designPatterns;


/**
 * <h2>Problem:</h2>
 * <p>
 * You want to select an algorithm or behavior at runtime, depending on the situation or input.
 * This is useful when different algorithms can be applied to solve a problem but need to be chosen dynamically.
 * </p>
 *
 * <h2>Solution:</h2>
 * <p>
 * The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable.
 * This allows the algorithm to vary independently from the clients that use it. The client can choose the appropriate
 * strategy at runtime.
 * </p>
 *
 * <h3>When to use:</h3>
 * <ul>
 *   <li><strong>Multiple algorithms:</strong> When you have different ways of doing something, such as different sorting algorithms (e.g., quicksort, mergesort, bubblesort).</li>
 *   <li><strong>Payment systems:</strong> When you need to switch between different payment methods dynamically, such as credit card, PayPal, or cash payment.</li>
 *   <li><strong>Compression algorithms:</strong> When a client might choose different compression techniques (e.g., zip, gzip, tar) based on user preference or system conditions.</li>
 * </ul>
 *
 * <h3>Example usage:</h3>
 * <pre>
 * PaymentStrategy strategy = new CreditCardPayment();
 * strategy.pay(100);
 * </pre>
 *
 * <p>This interface defines the Strategy pattern for payment strategies.</p>
 */
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}

public class Strategy {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.setPaymentStrategy(new CreditCardPayment());
        cart.checkout(100);  // Output: Paid 100 using Credit Card
        cart.setPaymentStrategy(new PayPalPayment());
        cart.checkout(200);  // Output: Paid 200 using PayPal
    }
}
