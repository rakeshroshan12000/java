import java.util.HashMap;
import java.util.Map;

class Department {
    private final String deptId;
    private final String deptName;

    public Department(String deptId, String deptName) {
        this.deptId = deptId; this.deptName = deptName;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("deptId", deptId);
        map.put("deptName", deptName);
        return map;
    }
}