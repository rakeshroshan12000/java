import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Broker {
    private final ConcurrentHashMap<String,Topic> topics = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final MessageStore store;

    public Broker(MessageStore store) { this.store = store; }

    public Topic getOrCreateTopic(String name, int partitions) {
        return topics.computeIfAbsent(name, k -> new Topic(name, partitions));
    }

    public void subscribe(String topicName, String groupName, Subscriber subscriber) {
        Topic topic = topics.get(topicName);
        if (topic != null) {
            ConsumerGroup group = topic.addConsumerGroup(groupName);
            group.addSubscriber(subscriber);
            System.out.println("Subscriber [" + subscriber.getName() + "] subscribed to " +
                    topicName + " in group " + groupName);
        }
    }

    public void publish(String topicName, int partitionId, Message message) {
        Topic topic = topics.get(topicName);
        if (topic != null) {
            Partition partition = topic.getPartitions().get(partitionId);
            partition.addMessage(message);
            store.saveMessage(topicName, partitionId, message);

            // Deliver to all groups respecting offsets
            for (ConsumerGroup group : topic.getConsumerGroups()) {
                int currentOffset = group.getOffset(partitionId);
                int newOffset = currentOffset + 1;
                group.commitOffset(partitionId, newOffset);

                for (Subscriber sub : group.getSubscribers()) {
                    executor.submit(() -> {
                        try {
                            sub.onMessage(topicName, partitionId, message);
                        } catch (Exception e) {
                            System.err.println("Delivery failed to subscriber [" +
                                    sub.getName() + "]: " + e.getMessage());
                        }
                    });
                }
            }
        }
    }

    // Replay messages from a given offset
    public void replay(String topicName, String groupName, int partitionId, int fromOffset) {
        Topic topic = topics.get(topicName);
        if (topic != null) {
            Partition partition = topic.getPartitions().get(partitionId);
            ConsumerGroup group = topic.addConsumerGroup(groupName);
            List<Message> messages = partition.getMessages();

            for (int i = fromOffset; i < messages.size(); i++) {
                Message msg = messages.get(i);
                for (Subscriber sub : group.getSubscribers()) {
                    executor.submit(() -> sub.onMessage(topicName, partitionId, msg));
                }
                group.commitOffset(partitionId, i + 1);
            }
        }
    }

    // Simple round-robin partitioner
    private final ConcurrentHashMap<String, AtomicInteger> partitionCounter = new ConcurrentHashMap<>();
    public int choosePartition(String topicName, Message message) {
        Topic topic = topics.get(topicName);
        if (topic == null) return 0;
        AtomicInteger counter = partitionCounter.computeIfAbsent(topicName, k -> new AtomicInteger(0));
        return counter.getAndIncrement() % topic.getPartitions().size();
    }

    public void shutdown() { executor.shutdown(); }



}
