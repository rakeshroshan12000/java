import java.io.Serializable;

public class Message implements Serializable {
    private final String content;
    private final long timestamp;
    public Message(String content) {
        this.content = content;
        this.timestamp = System.currentTimeMillis();
    }
    public String getContent() {
        return content;
    }
    public long getTimestamp() {
        return timestamp;
    }
    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
