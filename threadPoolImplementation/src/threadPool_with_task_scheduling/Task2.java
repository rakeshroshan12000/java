package threadPool_with_task_scheduling;

public class Task2 implements Comparable<Task2>{
    private final Runnable runnable;
    private final int priority;
    private final long deadline;
    public Task2(Runnable runnable, int priority, long deadline) {
        this.runnable = runnable;
        this.priority = priority;
        this.deadline = deadline;
    }
    public void execute() {
        runnable.run();
    }
    @Override
    public int compareTo(Task2 other) {
        if (this.priority != other.priority) {
            return Integer.compare(other.priority, this.priority); // Higher priority first
        }
        return Long.compare(this.deadline, other.deadline); // Earlier deadline first
    }
}
