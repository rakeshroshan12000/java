package threadPool_with_task_scheduling;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPool2 {
    private final Thread[] workers;
    private final TaskQueue taskQueue;
    private final AtomicBoolean isShutdownInitiated;
    public ThreadPool2(int poolSize, TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
        this.workers = new Thread[poolSize];
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
}
