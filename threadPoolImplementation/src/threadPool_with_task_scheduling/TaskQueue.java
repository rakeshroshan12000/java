package threadPool_with_task_scheduling;

import java.util.List;

public interface TaskQueue {
    void add(Task2 task2);
    Task2 poll();
    void drainToList(List<Task2> list);
}
