import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Topic {
    private final String name;
    private final List<Partition> partitions;
    private final ConcurrentHashMap<String,ConsumerGroup> consumerGroups = new ConcurrentHashMap<>();
    public Topic(String name, int numberOfPartitions) {
        this.name = name;
        this.partitions = new ArrayList<>();
        for (int i = 0; i < numberOfPartitions; i++) {
            partitions.add(new Partition(i));
        }
    }
    public String getName() {
        return name;
    }
    public List<Partition> getPartitions() {
        return partitions;
    }
    public ConsumerGroup addConsumerGroup(String consumerName) {
        return consumerGroups.computeIfAbsent(consumerName, ConsumerGroup::new);
    }
    public ConsumerGroup getConsumerGroup(String consumerName) {
        return consumerGroups.get(consumerName);
    }
    public Collection<ConsumerGroup> getConsumerGroups() {
        return consumerGroups.values();
    }
}
