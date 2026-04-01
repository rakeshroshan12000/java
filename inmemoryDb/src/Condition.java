class Condition {
    String field;
    Object value;
    Operator operator;

    public Condition(String field, Object value, Operator operator) {
        this.field = field; this.value = value; this.operator = operator;
    }
}
