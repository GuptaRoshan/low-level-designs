package problems.rateLimiter.algorithms;


import java.util.Random;
import java.util.concurrent.*;

@SuppressWarnings("all")
public class LeakyBucket {

    private final BlockingQueue<Integer> bucket;
    private final int bucketCapacity;

    public LeakyBucket(int bucketCapacity, long leakRateInMillis) {
        this.bucketCapacity = bucketCapacity;
        this.bucket = new ArrayBlockingQueue<>(bucketCapacity);
        // Schedule a task to "leak" requests at the specified rate
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> bucket.poll(), leakRateInMillis, leakRateInMillis, TimeUnit.MILLISECONDS);
    }

    public boolean allowRequest() {
        return bucket.offer(new Random().nextInt());
    }

    public static void main(String[] args) throws InterruptedException {
        LeakyBucket limiter = new LeakyBucket(3, 500); // Capacity 3, leaks every 500ms

        System.out.println("--- Burst Test ---");
        for (int i = 0; i < 5; i++) {
            System.out.println("Request " + (i + 1) + ": " + (limiter.allowRequest() ? "Added to bucket" : "Dropped"));
        }

        System.out.println("\n--- Bucket leaking... ---");
        Thread.sleep(2000);

        System.out.println("\n--- New Request ---");
        System.out.println("Request 6: " + (limiter.allowRequest() ? "Added to bucket" : "Dropped"));
    }

}