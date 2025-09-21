package multithreading;


import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ScheduledTask implements Comparable<ScheduledTask> {

    private final Runnable task;
    private final long intervalMillis; // 0 = one-time task
    private long nextExecutionTime; // For Periodic Task
    private boolean cancelled = false;

    public ScheduledTask(Runnable task, long delayMillis, long intervalMillis) {
        this.task = task;
        this.intervalMillis = intervalMillis;
        this.nextExecutionTime = System.currentTimeMillis() + delayMillis;// This will run after specified delay
    }

    public void run() {
        if (!cancelled) {
            task.run();
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public long getNextExecutionTime() {
        return nextExecutionTime;
    }

    public void updateNextExecutionTime() {
        nextExecutionTime = System.currentTimeMillis() + intervalMillis; // Update next Execution time
    }

    public boolean isPeriodic() {
        return intervalMillis > 0;
    }

    @Override
    public int compareTo(ScheduledTask other) {
        return Long.compare(this.nextExecutionTime, other.nextExecutionTime);
    }

}

public class TaskScheduler {

    private final PriorityQueue<ScheduledTask> taskQueue = new PriorityQueue<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition taskAvailable = lock.newCondition();
    private volatile boolean shutdown = false;

    public TaskScheduler() {
        Thread thread = new Thread(this::runWorker); // Run the worker thread which polls the task and runs it
        thread.setDaemon(true);
        thread.start();
    }


    public void scheduleOneTime(Runnable task, long delayMillis) {
        lock.lock();
        try {
            taskQueue.offer(new ScheduledTask(task, delayMillis, 0));
            taskAvailable.signalAll();  // Wake up the worker thread
        } finally {
            lock.unlock();
        }
    }


    public void scheduleAtFixedRate(Runnable task, long delayMillis, long intervalMillis) {
        lock.lock();
        try {
            taskQueue.offer(new ScheduledTask(task, delayMillis, intervalMillis));
            taskAvailable.signalAll(); // Wake up the worker thread
        } finally {
            lock.unlock();
        }
    }


    @SuppressWarnings("all")
    private void runWorker() {
        while (!shutdown) {
            lock.lock();
            try {
                long now = System.currentTimeMillis();
                ScheduledTask nextTask = taskQueue.peek();

                if (nextTask == null) {
                    taskAvailable.await(); // Wait for new tasks
                } else if (nextTask.getNextExecutionTime() <= now) {

                    nextTask = taskQueue.poll();
                    if (!nextTask.isCancelled()) { // If the task is not canceled, then run the task
                        nextTask.run();
                        if (nextTask.isPeriodic()) { // If the task is periodic, then update execution time and put it into queue
                            nextTask.updateNextExecutionTime();
                            taskQueue.offer(nextTask); // Reschedule the periodic task
                        }
                    }

                } else {
                    long delay = nextTask.getNextExecutionTime() - now;
                    taskAvailable.await(delay, TimeUnit.MILLISECONDS);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } finally {
                lock.unlock();
            }
        }
    }

    public void shutdown() {
        lock.lock();
        try {
            shutdown = true;
            taskAvailable.signalAll(); // Wake up the worker thread to let it exit
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting Task Scheduler Test...");
        TaskScheduler scheduler = new TaskScheduler();

        // Schedule a one-time task to run in 2 seconds
        scheduler.scheduleOneTime(() -> {
            String formattedTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()),
                            java.time.ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("hh:mm:ss a"));

            System.out.println("One-time task executed at " + formattedTime);
        }, 2000);


        // Schedule a periodic task to run every 3 seconds, starting in 1 second
        scheduler.scheduleAtFixedRate(() -> {
            String formattedTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()),
                    java.time.ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("hh:mm:ss a"));

            System.out.println("Periodic task executed at " + formattedTime);
        }, 1000, 3000);


        // Schedule another one-time task to run after the first periodic task
        scheduler.scheduleOneTime(() -> {
            String formattedTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()),
                    java.time.ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("hh:mm:ss a"));

            System.out.println("Second one-time task executed at " + formattedTime);
        }, 5000);


        // Keep the main thread alive for 10 seconds to see the scheduled tasks run
        System.out.println("Main thread sleeping for 10 seconds...");
        Thread.sleep(10000);

        scheduler.shutdown();
        System.out.println("Task Scheduler has been shut down.");
    }
}
