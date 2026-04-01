import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryDb {
    ConcurrentHashMap<Long,User> users = new ConcurrentHashMap<>();
    AtomicInteger idGenerator = new AtomicInteger(1);
    public void insert(String name, String email, String department, Integer age) {
        long id = idGenerator.getAndIncrement();
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setDepartment(department);
        user.setAge(age);
        users.put(id, user);
    }

    public User findById(long id) {
        return users.get(id);
    }

    public void delete(DeleteQuery query) {
        List<Document> results = search(query);
        for(Object obj : results) {
            User user = (User) obj;
            users.remove(user.getId());
        }
    }

    public void deleteById(long id) {
        users.remove(id);
    }

    public void updateById(long id,UpdateQuery query) {
        User user = users.get(id);
        if(user != null) {
            updateData(user, query);
        }
    }

    public void update(UpdateQuery query) {
       List<Document> results = search(query);
         for(Object obj : results) {
                User user = (User) obj;
                updateData(user, query);
            }
    }

    public void updateData(User user,UpdateQuery query) {
        for(String key : query.getUpdatevalues().keySet()) {
            Object value = query.getUpdatevalues().get(key);
            switch(key) {
                case "name":
                    user.setName((String)value);
                    break;
                case "email":
                    user.setEmail((String)value);
                    break;
                case "department":
                    user.setDepartment((String)value);
                    break;
                case "age":
                    user.setAge((Integer)value);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported field: " + key);
            }
        }
    }

    public List<Document> search(Query query) {
        SearchEngine searchEngine = new Search();
        return searchEngine.search(new ArrayList<>(users.values()), query);
    }
}
