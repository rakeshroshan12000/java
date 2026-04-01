public class Task implements Runnable {
    private final String taskName;
    public Task(String taskName) {
        this.taskName = taskName;
    }
    @Override
    public void run() {
        System.out.println("Executing task " + taskName + "On thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Completed task " + taskName);
    }
}
