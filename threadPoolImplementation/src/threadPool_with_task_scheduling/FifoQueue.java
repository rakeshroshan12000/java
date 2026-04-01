package threadPool_with_task_scheduling;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FifoQueue implements TaskQueue {
    private final BlockingQueue<Task2> queue = new LinkedBlockingQueue<>();
    public synchronized void add(Task2 task) {
        queue.add(task);
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
        return queue.poll();
    }

    public synchronized void drainToList(java.util.List<Task2> list) {
        queue.drainTo(list);
    }
}
