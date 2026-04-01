import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        InMemoryDB<String, User> db = new InMemoryDB<>(new IndexedSearchEngine<>());

        Department dept1 = new Department("D1", "Engineering");
        Department dept2 = new Department("D2", "HR");

        Employee emp1 = new Employee("E1", "Alice", dept1);
        Employee emp2 = new Employee("E2", "Bob", dept2);

        db.put("user:1", new User("1", "Rakesh Roshan", 35, emp1));
        db.put("user:2", new User("2", "John Doe", 28, emp2));
        db.put("user:3", new User("3", "Jane Smith", 40, emp1));

        // Range query: age > 30
        Query rangeQuery = new Query().where("age", 30, Operator.GT);
        System.out.println("Search age > 30 -> " + db.search(rangeQuery));

        // Logical AND: empName = Bob AND deptId = D2
        Query andQuery = new Query()
                .where("empName", "Bob", Operator.EQ)
                .where("deptId", "D2", Operator.EQ)
                .logical(Operator.AND);
        System.out.println("Search Bob in HR -> " + db.search(andQuery));

        // Logical OR: name contains 'Jane' OR deptName = Engineering
        Query orQuery = new Query()
                .where("name", "Jane", Operator.CONTAINS)
                .where("deptName", "Engineering", Operator.EQ)
                .logical(Operator.OR);
        System.out.println("Search Jane OR Engineering -> " + db.search(orQuery));
    }
}