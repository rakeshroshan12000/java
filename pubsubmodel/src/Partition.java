import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Partition {
    private final int id;
    private final List<Message> messages = Collections.synchronizedList(new ArrayList<>());
    public Partition(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void addMessage(Message message) {
        messages.add(message);
    }
    public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }
}
