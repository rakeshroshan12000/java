import java.util.HashMap;
import java.util.Map;

class User implements Document {
    private final String id;
    private final String name;
    private final int age;
    private final Employee employee;

    public User(String id, String name, int age, Employee employee) {
        this.id = id; this.name = name; this.age = age; this.employee = employee;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("age", age);
        if (employee != null) map.putAll(employee.toMap());
        return map;
    }

    @Override
    public String toString() {
        return "User{id='" + id + "', name='" + name + "', age=" + age + ", employee=" + employee + "}";
    }
}
