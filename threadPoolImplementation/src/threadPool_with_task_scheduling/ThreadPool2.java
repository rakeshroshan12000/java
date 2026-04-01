package threadPool_with_task_scheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPool2 {
    private final Worker2[] workers;
    private final TaskQueue taskQueue;
    private final AtomicBoolean isShutdownInitiated;
    public ThreadPool2(int poolSize, TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
        this.workers = new Worker2[poolSize];
        this.isShutdownInitiated = new AtomicBoolean(false);
        for (int i = 0; i < poolSize; i++) {
            Worker2 worker = new Worker2(taskQueue);
            workers[i] = worker;
            worker.start();
        }
    }
    public void submit(Runnable runnable,int priority,long deadline) {
        Task2 task = new Task2(runnable, priority, deadline);
        taskQueue.add(task);
    }
     public void shutdown() {
        isShutdownInitiated.set(true);
        for (Thread worker : workers) {
            worker.interrupt();
        }
    }

    public void awaitTermination() {
        for (Thread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public List<Task2> shutdownNow() {
        System.out.println("Immediate shutdown initiated...");
        for (Worker2 w : workers) {
            w.shutdown();
        }
        List<Task2> remainingTasks = new ArrayList<>();
        taskQueue.drainToList(remainingTasks);
        return remainingTasks;
    }

    // Await termination with timeout
    public boolean awaitTermination(long timeoutMillis) {
        long endTime = System.currentTimeMillis() + timeoutMillis;
        for (Worker2 w : workers) {
            long timeLeft = endTime - System.currentTimeMillis();
            if (timeLeft <= 0) {
                System.out.println("Timeout reached before all workers finished.");
                return false;
            }
            try {
                w.join(timeLeft);
            } catch (InterruptedException ignored) {}
        }
        System.out.println("All workers terminated within timeout.");
        return true;
    }
}
