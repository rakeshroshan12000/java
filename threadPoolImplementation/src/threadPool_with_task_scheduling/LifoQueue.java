package threadPool_with_task_scheduling;

import java.util.concurrent.BlockingDeque;

public class LifoQueue implements TaskQueue {
    private final BlockingDeque<Task2> queue;
    public LifoQueue(BlockingDeque<Task2> queue) {
        this.queue = queue;
    }
    public synchronized void add(Task2 task) {
        queue.addFirst(task);
        notify();
    }
    public synchronized Task2 poll() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        return queue.pollFirst();
    }
}
