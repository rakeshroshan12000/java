import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPool {
    public static ThreadPool instance;
    private final BlockingQueue<Runnable> taskQueue;
    private final Thread[] workers;
    private final AtomicBoolean isShutdownInitiated;
    private ThreadPool(int poolSize) {
        this.taskQueue = new LinkedBlockingQueue<>();
        this.workers = new Thread[poolSize];
        this.isShutdownInitiated = new AtomicBoolean(false);
        for (int i = 0; i < poolSize; i++) {
            workers[i] = new Worker(taskQueue);
            workers[i].start();
        }
    }
    public static synchronized ThreadPool getInstance(int poolSize) {
        if (instance == null) {
            instance = new ThreadPool(poolSize);
        }
        return instance;
    }
    public void submit(Runnable task) {
        if (!isShutdownInitiated.get()) {
            taskQueue.offer(task);
        }
    }
    public void shutdown() {
        isShutdownInitiated.set(true);
        for (Thread worker : workers) {
            worker.interrupt();
        }
    }
}
