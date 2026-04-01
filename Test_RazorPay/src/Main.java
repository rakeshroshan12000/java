import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        InMemoryDb db = new InMemoryDb();
        db.insert("rakesh","r@gmail.com","IT",30);
        db.insert("suresh","b@gmail.com","HR",25);

        db.findById(1);
        db.updateById(1, new UpdateQuery().set("age", 31));
        User user = db.findById(1);
        System.out.println(user.toString());
        List<Document> docs = db.search(new SearchQuery().where("name", "suresh", Operators.EQ));
        System.out.println(docs.toString());
        db.deleteById(2);
        db.findById(2);
        List<Document> doc2 = db.search(new SearchQuery().where("name", "suresh", Operators.EQ));
        System.out.println(doc2.toString());
        db.insert("suresh","b@gmail.com","HR",25);
        User u = db.findById(3);
        UpdateQuery uq = new UpdateQuery();
        uq.where("name", "suresh", Operators.EQ);
        uq.set("department", "Finance");
        db.update(uq);
        User u2 = db.findById(3);


    }
}