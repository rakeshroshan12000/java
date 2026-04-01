package threadPool_with_task_scheduling;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class LifoQueue implements TaskQueue {
    private final BlockingDeque<Task2> queue = new LinkedBlockingDeque<>();

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

    public synchronized void drainToList(java.util.List<Task2> list) {
        queue.drainTo(list);
    }
}
