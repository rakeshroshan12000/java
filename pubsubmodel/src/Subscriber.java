public interface Subscriber {
    void onMessage(String topic,int partition, Message message);
    String getName();
}
