import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@ManagedBean(name = "book")
public class Manager {

    private static final String PERSISTANCE_UNIT_NAME = "NewPersistenceUnit";
    private static EntityManager entityManager = Persistence.createEntityManagerFactory("NewPersistenceUnit").createEntityManager();
    private static EntityTransaction entityTransaction = entityManager.getTransaction();

    private String authorName;
    private int id;
    private String baseFilter;
    private String middleFilter;
    private String finalFilter;
    private String finalBase;
    private List<Loan> loans;

    public int getEditId() {
        return editId;
    }

    public void setEditId(int editId) {
        this.editId = editId;
    }

    private int editId;
    private String title;
    private int amount;
    private Integer user;
    private String startDate;
    private String endDate;

    public static List getAllBooks() {
        String jpql = "SELECT b FROM Book b";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
        return query.getResultList();
    }

    public static List getAllBooksAvailable() {
        String jpql = "SELECT b FROM Book b where b.amount>0";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
        return query.getResultList();
    }

    public static List getAllUsers() {
        String jpql = "SELECT b FROM Reader b";
        TypedQuery<Reader> query = entityManager.createQuery(jpql, Reader.class);
        return query.getResultList();
    }

    public String editBookDetailsById() {
        editId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedBookId"));
        return "editBook.xhtml";
    }

    public static List getAllLoans() {
        String jpql = "SELECT b FROM Loan b";
        TypedQuery<Loan> query = entityManager.createQuery(jpql, Loan.class);
        return query.getResultList();
    }


    public String editBook() throws NoResultException {
        if (!entityTransaction.isActive()) {
            entityTransaction.begin();
        }
        String jpql = "SELECT a FROM Author a WHERE a.name=:authorName";
        TypedQuery<Author> query = entityManager.createQuery(jpql, Author.class)
                .setParameter("authorName", authorName);
        try {
            Author author = query.getSingleResult();
            String jpql2 = "SELECT b FROM Book b WHERE b.id=:id";
            TypedQuery<Book> query2 = entityManager.createQuery(jpql2, Book.class)
                    .setParameter("id", editId);
            Book book = query2.getSingleResult();
            book.setAmount(amount);
            book.setAuthorId(author);
            book.setTitle(title);
            entityTransaction.begin();
            entityManager.merge(book);
            entityTransaction.commit();

        }
        catch (Exception e){
            Author author1= new Author(authorName);
            String jpql3 = "SELECT b FROM Book b WHERE b.id=:id";
            TypedQuery<Book> query3 = entityManager.createQuery(jpql3, Book.class)
                    .setParameter("id", editId);
            Book book = query3.getSingleResult();
            book.setAmount(amount);
            entityManager.persist(author1);
            entityTransaction.commit();
            entityTransaction.begin();
            book.setAuthorId(author1);
            book.setTitle(title);
            entityManager.merge(book);
            entityTransaction.commit();
        }
        finally {
            return "index.xhtml";
        }

    }



    public String addBook() throws NoResultException {
            if (!entityTransaction.isActive()) {
                entityTransaction.begin();
            }
            String jpql = "SELECT a FROM Author a WHERE a.name=:authorName";
            TypedQuery<Author> query = entityManager.createQuery(jpql, Author.class)
                    .setParameter("authorName", authorName);
            try {
                Author author = query.getSingleResult();
                Book newBook = new Book(author, title, amount);
                entityManager.persist(newBook);
                entityTransaction.commit();

            }
            catch (Exception e){
                Author author1= new Author(authorName);
                entityManager.persist(author1);
                entityTransaction.commit();
                entityTransaction.begin();
                Book newBook = new Book(author1, title, amount);
                entityManager.persist(newBook);
                entityTransaction.commit();
            }
            finally {
                return "index.xhtml";
            }
    }


    public String addLoan() throws ParseException {
        if (!entityTransaction.isActive()) {
            entityTransaction.begin();
        }
        String jpql = "SELECT b FROM Book b WHERE b.title=:title";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class)
                .setParameter("title", title);
        System.out.println(title);
        System.out.println(user);
        String jpql2 = "SELECT r FROM Reader r WHERE r.id=:id";
        TypedQuery<Reader> query2 = entityManager.createQuery(jpql2, Reader.class)
                .setParameter("id", user);
            Book book = query.getSingleResult();
            Reader reader = query2.getSingleResult();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("SIEMSON");
            System.out.println(startDate);
            System.out.println(endDate);
            java.util.Date startD = sdf1.parse(startDate);
            java.sql.Date sqlStartDate = new java.sql.Date(startD.getTime());
            java.util.Date endD = sdf1.parse(endDate);
            java.sql.Date sqlEndDate = new java.sql.Date(endD.getTime());
            Loan loan = new Loan (book, reader, sqlStartDate, sqlEndDate );
            entityManager.persist(loan);
            entityTransaction.commit();
            int amount = book.getAmount() -1;
            book.setAmount(amount);
            entityTransaction.begin();
            entityManager.merge(book);
            entityTransaction.commit();

        return "index.xhtml";

    }



    public static String deleteBook(Integer id) {
        if (!entityTransaction.isActive()) {
            entityTransaction.begin();
        }

        String jpql = "SELECT b FROM Book b WHERE b.id=:id";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class)
                .setParameter("id", id);
        Book book = query.getSingleResult();
        book.setAmount(book.getAmount()-1);
        if(book.getAmount()==0){
            entityManager.remove(entityManager.merge(book));
            entityTransaction.commit();
        }
        else {
            entityManager.merge(book);
            entityTransaction.commit();

        }

        return "booksList.xhtml?faces-redirect=true";
    }


    public static String deleteLoan(Integer id) {
        if (!entityTransaction.isActive()) {
            entityTransaction.begin();
        }

        String jpql = "SELECT l FROM Loan l WHERE l.id=:id";
        TypedQuery<Loan> query = entityManager.createQuery(jpql, Loan.class)
                .setParameter("id", id);
        Loan loan = query.getSingleResult();
        Book book = loan.getBookId();
        book.setAmount(book.getAmount() + 1);
        entityManager.merge(book);
        entityManager.remove(loan);
        entityTransaction.commit();
        return "booksList.xhtml?faces-redirect=true";
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBaseFilter() {
        return baseFilter;
    }

    public void setBaseFilter(String baseFilter) {
        this.baseFilter = baseFilter;
    }

    public String getMiddleFilter() {
        return middleFilter;
    }

    public void setMiddleFilter(String middleFilter) {
        this.middleFilter = middleFilter;
    }

    public String getFinalFilter() {
        return finalFilter;
    }

    public void setFinalFilter(String finalFilter) {
        this.finalFilter = finalFilter;
    }

    public String getFinalBase() {
        return finalBase;
    }

    public void setFinalBase(String finalBase) {
        this.finalBase = finalBase;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

