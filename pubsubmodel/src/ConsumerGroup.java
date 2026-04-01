import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsumerGroup {
    private final String name;
    private final CopyOnWriteArrayList<Subscriber> subscribers = new CopyOnWriteArrayList<>();
    private final ConcurrentHashMap<Integer, AtomicInteger> offsets = new ConcurrentHashMap<>();
    public ConsumerGroup(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }
    public List<Subscriber> getSubscribers() {
        return subscribers;
    }
    public int getOffset(int topicPartition) {
        return offsets.getOrDefault(topicPartition, new AtomicInteger(0)).get();
    }
    public void commitOffset(int topicPartition, int offset) {
        offsets.computeIfAbsent(topicPartition, tp -> new AtomicInteger(0)).set(offset);
    }
}
