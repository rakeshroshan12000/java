import java.util.ArrayList;
import java.util.List;

public class Search implements SearchEngine{
    @Override
    public List<Document> search(List<Document> documents, Query query) {
        List<Document> result = new ArrayList<>();
        for(Document doc : documents) {
            boolean match = true;
            for(Condition condition : query.getConditions()) {
                Object value = doc.toMap().get(condition.getKey());
                if(value == null || !value.equals(condition.getValue())) {
                    match = false;
                    break;
                }
                match = evaluateCondition(value, condition);
            }
            if(match)
                result.add(doc);
        }
        return result;
    }

    private boolean evaluateCondition(Object value, Condition condition) {
        switch(condition.getOperators()) {
            case EQ:
                return value.equals(condition.getValue());
//            case CONTAINS:
//                return value.contains(condition.getValue());
            case GT:
                if(value instanceof Integer) {
                    return (Integer)value > (Integer)condition.getValue();
                }
                throw new IllegalArgumentException("GT operator only supports Integer values");
            case LT:
                if(value instanceof Integer) {
                    return (Integer)value < (Integer)condition.getValue();
                }
                throw new IllegalArgumentException("LT operator only supports Integer values");
            default:
                throw new IllegalArgumentException("Unsupported operator");
        }
    }
}
