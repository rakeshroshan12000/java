import java.util.Collection;
import java.util.List;

interface SearchEngine<V extends Document> {
    List<V> search(Collection<V> documents, Query query);
}
