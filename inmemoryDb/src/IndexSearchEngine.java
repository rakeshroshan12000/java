import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class IndexedSearchEngine<V extends Document> implements SearchEngine<V> {
    private final Map<String, Map<Object, Set<V>>> indexes = new ConcurrentHashMap<>();

    public void index(V doc) {
        Map<String, Object> fields = doc.toMap();
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            indexes.computeIfAbsent(entry.getKey(), k -> new ConcurrentHashMap<>())
                    .computeIfAbsent(entry.getValue(), v -> ConcurrentHashMap.newKeySet())
                    .add(doc);
        }
    }

    @Override
    public List<V> search(Collection<V> documents, Query query) {
        List<V> results = new ArrayList<>();

        if (query.getLogicalOperator() == Operator.AND) {
            Set<V> candidate = new HashSet<>(documents);
            for (Condition c : query.getConditions()) {
                candidate.retainAll(filter(c, documents));
            }
            results.addAll(candidate);
        } else if (query.getLogicalOperator() == Operator.OR) {
            Set<V> candidate = new HashSet<>();
            for (Condition c : query.getConditions()) {
                candidate.addAll(filter(c, documents));
            }
            results.addAll(candidate);
        }
        return results;
    }

    private Set<V> filter(Condition c, Collection<V> documents) {
        Set<V> matches = new HashSet<>();
        for (V doc : documents) {
            Object fieldValue = doc.toMap().get(c.field);
            if (fieldValue == null) continue;

            switch (c.operator) {
                case EQ:
                    if (fieldValue.equals(c.value)) matches.add(doc);
                    break;
                case CONTAINS:
                    if (fieldValue.toString().contains(c.value.toString())) matches.add(doc);
                    break;
                case GT:
                    if (fieldValue instanceof Number && ((Number) fieldValue).doubleValue() > Double.parseDouble(c.value.toString())) {
                        matches.add(doc);
                    }
                    break;
                case LT:
                    if (fieldValue instanceof Number && ((Number) fieldValue).doubleValue() < Double.parseDouble(c.value.toString())) {
                        matches.add(doc);
                    }
                    break;
            }
        }
        return matches;
    }
}
