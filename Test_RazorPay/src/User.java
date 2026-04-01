import java.util.HashMap;
import java.util.Map;

public class User implements Document{
    long id;
    String name;
    String email;
    String department;
    Integer age;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public Map<String,Object> toMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("email", email);
        map.put("department", department);
        map.put("age", age);
        return map;
    }

    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "', department='" + department + "', age=" + age + "}";
    }
}
