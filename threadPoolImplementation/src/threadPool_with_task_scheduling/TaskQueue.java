package threadPool_with_task_scheduling;

public interface TaskQueue {
    void add(Task2 task2);
    Task2 poll();
}
