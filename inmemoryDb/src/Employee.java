import java.util.HashMap;
import java.util.Map;

class Employee {
    private final String empId;
    private final String empName;
    private final Department department;

    public Employee(String empId, String empName, Department department) {
        this.empId = empId; this.empName = empName; this.department = department;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("empId", empId);
        map.put("empName", empName);
        if (department != null) map.putAll(department.toMap());
        return map;
    }
}