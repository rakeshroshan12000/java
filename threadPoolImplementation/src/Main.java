import threadPool_with_task_scheduling.LifoQueue;
import threadPool_with_task_scheduling.PriorityQueue;
import threadPool_with_task_scheduling.TaskQueue;
import threadPool_with_task_scheduling.ThreadPool2;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        ThreadPool threadPool = ThreadPool.getInstance(3);
//        for (int i = 1; i <= 10; i++) {
//            threadPool.submit(new Task("Task-" + i));
//        }
//        threadPool.shutdown();
        TaskQueue queue = new LifoQueue(); // try FifoQueue or LifoQueue
        ThreadPool2 pool = new ThreadPool2( 3, queue);

        pool.submit(() -> System.out.println("Task A executed"), 1, System.currentTimeMillis() + 1000);
        pool.submit(() -> System.out.println("Task B executed"), 5, System.currentTimeMillis() + 500);
        pool.submit(() -> System.out.println("Task C executed"), 3, System.currentTimeMillis() + 2000);
        pool.shutdown();
    }
}