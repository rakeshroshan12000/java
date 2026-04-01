public class Condition {
    private String key;
    private Object value;
    private Operators operators;

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public Operators getOperators() {
        return operators;
    }

    public Condition(String key, Object value, Operators operators) {
        this.key = key;
        this.value = value;
        this.operators = operators;
    }

}
