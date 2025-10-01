package multithreading;

import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool {

    private final LinkedBlockingQueue<Runnable> taskQueue;
    private final Thread[] workers;
    private volatile boolean isShutdown = false;

    public CustomThreadPool(int numOfWorker) {
        this.taskQueue = new LinkedBlockingQueue<>();
        this.workers = new Thread[numOfWorker];


        for (int i = 0; i < numOfWorker; i++) {
            workers[i] = new Thread(this::worker);
            workers[i].start();
        }

    }

    public void submitTask(Runnable task) throws InterruptedException {
        if (!isShutdown) {
            taskQueue.put(task); // safer than add()
        } else {
            throw new IllegalStateException("ThreadPool is shutting down, cannot accept new tasks.");
        }
    }

    public void shutDown() {
        if (!isShutdown) {
            isShutdown = true;
            for (Thread worker : workers) {
                worker.interrupt();
            }
        }
    }

    private void worker() {
        try {
            while (!isShutdown || !taskQueue.isEmpty()) {
                Runnable task = taskQueue.take();

                // A Runnable’s run() method can throw an unchecked (RuntimeException) at any time.
                //If you don’t catch it, the exception will propagate up and terminate the worker thread.
                // Once the worker thread dies, the pool permanently loses one worker → fewer threads to execute tasks.
                try {
                    task.run();
                } catch (RuntimeException e) {
                    System.err.println(Thread.currentThread().getName() + " - Task execution failed: " + e);
                }

            }
        } catch (InterruptedException ignored) {
            // If InterruptedException is accidental, then ignore

            // If it was intentional, then propagate InterruptedException
            if (isShutdown) {
                Thread.currentThread().interrupt(); // restore flag
            }
            
        }
    }

}
