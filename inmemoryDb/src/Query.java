import java.util.ArrayList;
import java.util.List;

class Query {
    private final List<Condition> conditions = new ArrayList<>();
    private Operator logicalOperator = Operator.AND; // default

    public Query where(String field, Object value, Operator operator) {
        conditions.add(new Condition(field, value, operator));
        return this;
    }

    public Query logical(Operator op) {
        this.logicalOperator = op;
        return this;
    }

    public List<Condition> getConditions() { return conditions; }
    public Operator getLogicalOperator() { return logicalOperator; }
}
