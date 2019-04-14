import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DataBase {

    private static final String PERSISTENCE_UNIT_NAME = "bookApp";
    private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory("bookApp").createEntityManager();
    private static EntityTransaction transactionObj = entityMgrObj.getTransaction();


    public static List getAllBookDetails() {
        Query queryObj = entityMgrObj.createQuery("SELECT s FROM Book s");
        List bookList = queryObj.getResultList();
        return bookList;
    }

    public static String createNewBook(BookBean book) throws IllegalAccessException {
        try {
            if (!transactionObj.isActive()) {
                transactionObj.begin();
            }

            Book newBookObj = new Book(book.getAuthorName(),book.getAuthorName(),book.getTitle(),book.getIsbn(), book.getYear(),book.getPrice());
            entityMgrObj.persist(newBookObj);
            transactionObj.commit();
            return "booksList.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage("Try again!"));
            return "booksList.xhtml";
        }
    }

    public static String deleteBookDetails(int id) {
        if (!transactionObj.isActive()) {
            transactionObj.begin();
        }

        Book deleteBookObj = new Book();
        if (isBookIdPresent(id)) {
            deleteBookObj.setId(id);
            entityMgrObj.remove(entityMgrObj.merge(deleteBookObj));
        }
        transactionObj.commit();
        return "booksList.xhtml?faces-redirect=true";
    }

    public static String updateBookDetails(int bookId, String name, String surname, String updatedBookTitle, String isbn, int year, int price) throws IllegalAccessException {
        try {
            if (!transactionObj.isActive()) {
                transactionObj.begin();
            }
            if (isBookIdPresent(bookId)) {
                System.out.println(name);
                System.out.println(updatedBookTitle);
                Query queryObj = entityMgrObj.createQuery("UPDATE Book s SET s.authorName=:name, s.authorSurname=:surname, s.title=:title,s.isbn=:isbn,s.price=:price,s.year=:year WHERE s.id= :id");
                queryObj.setParameter("id", bookId);
                queryObj.setParameter("name", name);
                queryObj.setParameter("surname", surname);
                queryObj.setParameter("year", year);
                queryObj.setParameter("isbn", isbn);
                queryObj.setParameter("price", price);
                queryObj.setParameter("title", updatedBookTitle);
                int updateCount = queryObj.executeUpdate();
                if (updateCount > 0) {
                    System.out.println("Record For Id: " + bookId + " Is Updated");
                }
            }
            transactionObj.commit();
            FacesContext.getCurrentInstance().addMessage("editBookForm:bookId", new FacesMessage("Book Record #" + bookId + " Is Successfully Updated In Db"));
            return "bookEdit.xhtml";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("editBookForm:bookId", new FacesMessage("Book Record #" + bookId + " Is not in Db"));
            return "bookEdit.xhtml";
        }
    }

    private static boolean isBookIdPresent(int bookId) {
        boolean idResult = false;
        Query queryObj = entityMgrObj.createQuery("SELECT s FROM Book s WHERE s.id = :id");
        queryObj.setParameter("id", bookId);
        Book selectedBookId = (Book) queryObj.getSingleResult();
        if (selectedBookId != null) {
            idResult = true;
        }
        return idResult;
    }
}