package multithreading;

import java.util.concurrent.*;

public class BoundedBlockingQueue {

    private final ConcurrentLinkedQueue<Integer> queue;
    private final Semaphore emptySlots;
    private final Semaphore itemsAvailable;


    public BoundedBlockingQueue(int capacity) {
        this.emptySlots = new Semaphore(capacity, true);
        this.itemsAvailable = new Semaphore(0, true);
        this.queue = new ConcurrentLinkedQueue<>();
    }

    public void enqueue(int element) throws InterruptedException {
        emptySlots.acquire();
        queue.offer(element);
        itemsAvailable.release();
    }

    @SuppressWarnings("all")
    public int dequeue() throws InterruptedException {
        int item;
        itemsAvailable.acquire();
        item = queue.poll();
        emptySlots.release();

        return item;
    }

    public int size() {
         return queue.size();
    }


    // Main Method
    public static void main(String[] args) throws InterruptedException {
        final BoundedBlockingQueue queue = new BoundedBlockingQueue(5);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        Runnable producerTask = () -> {
            for (int i = 0; i < 20; i++) {
                try {
                    queue.enqueue(i);
                    System.out.println(Thread.currentThread().getName() + " produced: " + i);
                    Thread.sleep((long) (Math.random() * 100));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable consumerTask = () -> {
            for (int i = 0; i < 20; i++) {
                try {
                    int item = queue.dequeue();
                    System.out.println(Thread.currentThread().getName() + " consumed: " + item);
                    Thread.sleep((long) (Math.random() * 150));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };


        // Submit producers
        executor.execute(producerTask);
        executor.execute(producerTask);
        // Submit consumers
        executor.execute(consumerTask);
        executor.execute(consumerTask);
        // Graceful shutdown
        executor.shutdown();
        System.out.println("All tasks completed");
    }

}
