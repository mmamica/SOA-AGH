import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.sql.Date;

@Entity
public class Loan {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "bookid", nullable = false)
    private Book bookId;

    @OneToOne
    @JoinColumn(name = "readerrid", nullable = false)
    private Reader readerId;

    @Column(nullable = false)
    private Date startLoan;

    @Column(nullable = false)
    private Date endLoan;

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartLoan() {
        return startLoan;
    }

    public void setStartLoan(Date startLoan) {
        this.startLoan = startLoan;
    }

    public Date getEndLoan() {
        return endLoan;
    }

    public void setEndLoan(Date endLoan) {
        this.endLoan = endLoan;
    }

    public Reader getReaderId() {
        return readerId;
    }

    public void setReaderId(Reader readerId) {
        this.readerId = readerId;
    }

    public Loan (Book bookId, Reader readerId, Date startLoan, Date endLoan){
        this.bookId=bookId;
        this.readerId=readerId;
        this.startLoan=startLoan;
        this.endLoan=endLoan;
    }

    public Loan(){}
}
