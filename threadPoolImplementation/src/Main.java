//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ThreadPool threadPool = ThreadPool.getInstance(3);
        for (int i = 1; i <= 10; i++) {
            threadPool.submit(new Task("Task-" + i));
        }
        threadPool.shutdown();
    }
}