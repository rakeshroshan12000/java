import java.util.List;

public interface SearchEngine {
    List<Document> search(List<Document> documents, Query query);
}
