import java.util.ArrayList;
import java.util.List;

public class SearchQuery implements Query {

    @Override
    public Query where(String key, Object value, Operators operator) {
        conditions.add(new Condition(key,value,operator));
        return this;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public Operators getOperator() {
        return operator;
    }
}
