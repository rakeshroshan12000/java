import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class InMemoryDB<K, V extends Document> {
    private final ConcurrentHashMap<K, V> store = new ConcurrentHashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final IndexedSearchEngine<V> searchEngine;

    public InMemoryDB(IndexedSearchEngine<V> searchEngine) {
        this.searchEngine = searchEngine;
    }

    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            store.put(key, value);
            searchEngine.index(value); // update index
        } finally {
            lock.writeLock().unlock();
        }
    }

    public V get(K key) {
        lock.readLock().lock();
        try {
            return store.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void delete(K key) {
        lock.writeLock().lock();
        try {
            store.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public List<V> search(Query query) {
        lock.readLock().lock();
        try {
            return searchEngine.search(store.values(), query);
        } finally {
            lock.readLock().unlock();
        }
    }
}
