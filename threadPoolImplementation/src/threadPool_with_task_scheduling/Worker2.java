package threadPool_with_task_scheduling;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Worker2 extends Thread {
    private final TaskQueue taskQueue;
    private final AtomicBoolean isshutdownInitiated;
    public Worker2(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
        this.isshutdownInitiated = new AtomicBoolean(false);
    }
    public void shutdown() {
        isshutdownInitiated.set(true);
        this.interrupt(); // wake up if blocked
    }
    public void run() {
        while (!isshutdownInitiated.get()) {
                Task2 task = taskQueue.poll();
                if(task != null) {
                    System.out.println("Worker " + Thread.currentThread().getName() + " executing task ");
                    task.execute();
                } else if(isshutdownInitiated.get()) {
                    Thread.currentThread().interrupt();
                    break;
                }
        }
    }
}
