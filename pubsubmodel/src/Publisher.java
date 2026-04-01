public class Publisher {
    private final String name;
    private final Broker broker;

    public Publisher(String name, Broker broker) {
        this.name = name;
        this.broker = broker;
    }

    public void publish(String topic, String content) {
        Message message = new Message(content);
        int partition = broker.choosePartition(topic, message);
        System.out.println("Publisher [" + name + "] published to " +
                topic + " partition " + partition + ": " + content);
        broker.publish(topic, partition, message);
    }

}
