public class CreateSubscriber implements Subscriber{
    private final String name;
    public CreateSubscriber(String name) {
        this.name = name;
    }
    @Override
    public void onMessage(String topic, int partition, Message message) {
        System.out.println("Subscriber " + name + " received message on topic " + topic + " partition " + partition + ": " + message);
    }
    @Override
    public String getName() {
        return name;
    }
}
