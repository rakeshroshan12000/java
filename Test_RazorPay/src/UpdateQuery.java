import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateQuery implements Query{
    private Map<String,Object> updatevalues = new HashMap<>();
    @Override
    public Query where(String key, Object value, Operators operator) {
        conditions.add(new Condition(key,value,operator));
        return this;
    }
    public UpdateQuery set(String key, Object value) {
        updatevalues.put(key, value);
        return this;
    }
    public Map<String, Object> getUpdatevalues() {
        return updatevalues;
    }
    public List<Condition> getConditions() {
        return conditions;
    }

    public Operators getOperator() {
        return operator;
    }

}
