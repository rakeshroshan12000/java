import java.util.ArrayList;
import java.util.List;

public interface Query {
    List<Condition> conditions = new ArrayList<>();
    Operators operator = Operators.AND;
    Query where(String key, Object value, Operators operator);
    List<Condition> getConditions();
    Operators getOperator();
}
