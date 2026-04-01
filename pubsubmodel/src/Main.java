//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}

/**
 * import java.util.*;
 * import java.util.concurrent.*;
 *
 * // Subscriber interface
 * interface Subscriber {
 *     void onMessage(String topic, String message);
 * }
 *
 * // Publisher class
 * class Publisher {
 *     private final Broker broker;
 *
 *     public Publisher(Broker broker) {
 *         this.broker = broker;
 *     }
 *
 *     public void publish(String topic, String message) {
 *         broker.publish(topic, message);
 *     }
 * }
 *
 * // Broker with persistence + replay
 * class Broker {
 *     private final Map<String, List<Subscriber>> topicSubscribers = new ConcurrentHashMap<>();
 *     private final Map<String, List<String>> topicMessages = new ConcurrentHashMap<>();
 *     private final ExecutorService executor;
 *
 *     public Broker(int threadPoolSize) {
 *         this.executor = Executors.newFixedThreadPool(threadPoolSize);
 *     }
 *
 *     // Subscribe with optional replay
 *     public void subscribe(String topic, Subscriber subscriber, boolean replayHistory) {
 *         topicSubscribers
 *             .computeIfAbsent(topic, t -> new CopyOnWriteArrayList<>())
 *             .add(subscriber);
 *
 *         // Replay old messages if requested
 *         if (replayHistory) {
 *             List<String> history = topicMessages.getOrDefault(topic, Collections.emptyList());
 *             for (String msg : history) {
 *                 executor.submit(() -> subscriber.onMessage(topic, msg));
 *             }
 *         }
 *     }
 *
 *     // Unsubscribe
 *     public void unsubscribe(String topic, Subscriber subscriber) {
 *         List<Subscriber> subscribers = topicSubscribers.get(topic);
 *         if (subscribers != null) {
 *             subscribers.remove(subscriber);
 *         }
 *     }
 *
 *     // Publish message (store + async deliver)
 *     public void publish(String topic, String message) {
 *         // Store message for replay
 *         topicMessages
 *             .computeIfAbsent(topic, t -> new CopyOnWriteArrayList<>())
 *             .add(message);
 *
 *         // Deliver asynchronously
 *         List<Subscriber> subscribers = topicSubscribers.get(topic);
 *         if (subscribers != null && !subscribers.isEmpty()) {
 *             for (Subscriber subscriber : subscribers) {
 *                 executor.submit(() -> {
 *                     try {
 *                         subscriber.onMessage(topic, message);
 *                     } catch (Exception e) {
 *                         System.err.println("Error delivering message: " + e.getMessage());
 *                     }
 *                 });
 *             }
 *         }
 *     }
 *
 *     // Graceful shutdown
 *     public void shutdown() {
 *         executor.shutdown();
 *         try {
 *             if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
 *                 executor.shutdownNow();
 *             }
 *         } catch (InterruptedException e) {
 *             executor.shutdownNow();
 *             Thread.currentThread().interrupt();
 *         }
 *     }
 * }
 *
 * // Example subscriber
 * class PrintSubscriber implements Subscriber {
 *     private final String name;
 *
 *     public PrintSubscriber(String name) {
 *         this.name = name;
 *     }
 *
 *     @Override
 *     public void onMessage(String topic, String message) {
 *         System.out.printf("[%s] Received on topic '%s': %s%n", name, topic, message);
 *         try {
 *             Thread.sleep(300); // Simulate processing delay
 *         } catch (InterruptedException e) {
 *             Thread.currentThread().interrupt();
 *         }
 *     }
 * }
 *
 * // Main test
 * public class PersistentPubSubDemo {
 *     public static void main(String[] args) {
 *         Broker broker = new Broker(4);
 *
 *         Publisher publisher = new Publisher(broker);
 *
 *         Subscriber subA = new PrintSubscriber("SubscriberA");
 *         Subscriber subB = new PrintSubscriber("SubscriberB");
 *
 *         // Subscriber A joins without replay
 *         broker.subscribe("sports", subA, false);
 *
 *         // Publish some messages
 *         publisher.publish("sports", "Team A won the match!");
 *         publisher.publish("sports", "Player X scored a hat-trick!");
 *
 *         // Subscriber B joins with replay
 *         broker.subscribe("sports", subB, true);
 *
 *         // Publish more messages
 *         publisher.publish("sports", "Season finale is next week!");
 *
 *         // Allow async tasks to complete
 *         try {
 *             Thread.sleep(2000);
 *         } catch (InterruptedException e) {
 *             Thread.currentThread().interrupt();
 *         }
 *
 *         broker.shutdown();
 *     }
 * }
 */