import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileMessageStore implements MessageStore{
    private final String filePath;

    public FileMessageStore(String filePath) { this.filePath = filePath; }

    public synchronized void saveMessage(String topic, int partition, Message message) {
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("[" + topic + "][partition-" + partition + "] " + message.toString());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Failed to persist message: " + e.getMessage());
        }
    }

}
