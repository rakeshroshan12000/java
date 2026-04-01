//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MessageStore store = new FileMessageStore("messages.log");
        Broker broker = new Broker(store);

        broker.getOrCreateTopic("sports", 3);

        Subscriber s1 = new CreateSubscriber("S1");
        broker.subscribe("sports", "groupA", s1);

        Publisher p1 = new Publisher("P1", broker);

        p1.publish("sports", "Football match today!");
        p1.publish("sports", "Cricket match tomorrow!");
        p1.publish("sports", "Basketball finals next week!");

        Thread.sleep(1000);

        // Replay from offset 1 (skip first message)
        System.out.println("\nReplaying messages from offset 1 for groupA...");
        broker.replay("sports", "groupA", 0, 1);

        Thread.sleep(1000);
        broker.shutdown();
    }

}