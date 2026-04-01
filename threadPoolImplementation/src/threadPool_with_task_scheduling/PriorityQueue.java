package threadPool_with_task_scheduling;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityQueue implements TaskQueue{
    private final PriorityBlockingQueue<Task2> queue = new PriorityBlockingQueue<>();
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
}
