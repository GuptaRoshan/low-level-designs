package problems.rateLimiter.algorithms;

import java.util.concurrent.atomic.AtomicLong;

public class TokenBucket {
    private final int capacity;
    private final int refillRate; // Tokens per second to refill
    private final AtomicLong tokens;
    private final AtomicLong lastRefillTime; // Last Refill Time

    public TokenBucket(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = new AtomicLong(capacity);
        this.lastRefillTime = new AtomicLong(System.nanoTime());
    }


    public synchronized boolean allowedRequest() {
        refillTokens();
        if (tokens.get() > 0) {
            tokens.decrementAndGet();
            return true;
        }
        return false;
    }

    private void refillTokens() {
        long currentTime = System.nanoTime();
        long timeElapsed = (currentTime - lastRefillTime.get()) / 1_000_000_000L; // In seconds;
        long tokensToAdd = timeElapsed * refillRate; // Should be greater than 0

        if (tokensToAdd > 0) {
            long maxToken = Math.min(tokens.get() + tokensToAdd, capacity);
            tokens.set(maxToken);
            lastRefillTime.set(currentTime);
        }
    }

}
