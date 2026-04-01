public interface Subscriber {
    void onMessage(String topic,int partition, String message);
    String getName();
}
