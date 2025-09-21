package problems.rateLimiter.algorithms;


import java.util.concurrent.atomic.AtomicLong;

public class FixedWindowCounter {

    private final long windowSizeInMillis;
    private final int limit;
    private final AtomicLong requestCount;
    private long windowStartTime;

    public FixedWindowCounter(int limit, long windowSizeInSeconds) {
        this.limit = limit;
        this.windowSizeInMillis = windowSizeInSeconds * 1000;
        this.windowStartTime = System.currentTimeMillis();
        this.requestCount = new AtomicLong(0);
    }

    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();

        // If the current time is in a new window, reset the counter
        if (currentTime - windowStartTime >= windowSizeInMillis) {
            windowStartTime = currentTime;
            requestCount.set(0);
        }

        // Check if the current request count is below the limit
        if (requestCount.get() < limit) {
            requestCount.incrementAndGet();
            return true;
        }
        return false;
    }


    public static void main(String[] args) throws InterruptedException {
        FixedWindowCounter limiter = new FixedWindowCounter(3, 2); // 3 requests per 2 seconds

        System.out.println("--- Window 1: Bursting requests ---");
        for (int i = 0; i < 5; i++) {
            System.out.println("Request " + (i + 1) + ": " + (limiter.allowRequest() ? "Allowed" : "Denied"));
            Thread.sleep(100);
        }

        System.out.println("\n--- Waiting for the next window... ---");
        Thread.sleep(2000); // Wait for the window to pass

        System.out.println("Request 6: " + (limiter.allowRequest() ? "Allowed" : "Denied"));
    }
}

