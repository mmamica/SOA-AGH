import javax.persistence.*;
import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "authorId", cascade = {CascadeType.ALL})
    private Set<Book> books;

    public Author(String name){
        this.name=name;
    }

    public Author(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}