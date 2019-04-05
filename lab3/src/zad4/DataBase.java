package zad4;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.LinkedList;

@ManagedBean(name="Book")
@ViewScoped
public class DataBase {
    private LinkedList<Book> books = new LinkedList<Book>();

    public DataBase() throws Exception {
        books.add(new Book("Blaze", "Stephen King", "criminal", 10.0,"USD", 250));
        books.add(new Book("Balladyna", "Juliusz Słowacki", "drama", 30.0,"PLN", 200));
        books.add(new Book("Dziady", "Adam Mickiewicz", "drama", 22.0,"PLN", 300));
        books.add(new Book("Millenium", "Stieg Larson", "criminal", 10.0,"EUR", 500));
        books.add(new Book("Hrabia Monte Christo", "Alexander Dumas", "criminal", 30.0,"EUR", 550));

    }

    public void setBooks(LinkedList<Book> books) {
        this.books = books;
    }

    public LinkedList<Book> getBooks() {
        return books;
    }
}
