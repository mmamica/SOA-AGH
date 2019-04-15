import javax.faces.bean.ManagedBean;
import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "authorid", nullable = false)
    private Author authorId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int amount;

    public Book() {
    }

    public Book(Author authorId, String title, int amount){
        this.authorId=authorId;
        this.title=title;
        this.amount=amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//
//    public Author getAuthorId() {
//        return authorId;
//    }
//
//    public void setAuthorId(Author authorId) {
//        this.authorId = authorId;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Author getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Author authorId) {
        this.authorId = authorId;
    }
}

