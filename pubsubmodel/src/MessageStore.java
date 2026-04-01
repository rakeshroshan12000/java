public interface MessageStore {
    void saveMessage(String topic, int partition, Message message);
}
