import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean(name = "book")
public class BookBean {
    private int id;

    public int getEditId() {
        return editId;
    }

    public void setEditId(int editId) {
        this.editId = editId;
    }

    private int editId;
    private String authorName;
    private String editedAuthorName;
    private String authorSurname;
    private String editedAuthorSurname;
    private String title;
    private String editedTitle;
    private String isbn;
    private String editedIsbn;
    private int year;
    private int editedYear;
    private int editedPrice;
    private int price;
    private Boolean addBtn;


    public List bookListFromDb() {
        return DataBase.getAllBookDetails();
    }

    public String addNewBook(BookBean book) throws IllegalAccessException {
        return DataBase.createNewBook(book);
    }

    public String deleteBookById(int id) {
        return DataBase.deleteBookDetails(id);
    }

    public String editBookDetailsById() {
        editId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedBookId"));
        return "bookEdit.xhtml";
    }

    public String updateBookDetails(BookBean bean) throws IllegalAccessException {
       return DataBase.updateBookDetails(bean.getEditId(), bean.getAuthorName(), bean.getAuthorSurname(), bean.getTitle(), bean.getIsbn(), bean.getYear(), bean.getPrice());
    }

    public int getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getEditedAuthorName() {
        return editedAuthorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public String getEditedAuthorSurname() {
        return editedAuthorSurname;
    }

    public String getTitle() {
        return title;
    }

    public String getEditedTitle() {
        return editedTitle;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getEditedIsbn() {
        return editedIsbn;
    }

    public int getYear() {
        return year;
    }

    public int getEditedYear() {
        return editedYear;
    }

    public int getEditedPrice() {
        return editedPrice;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setEditedAuthorName(String editedAuthorName) {
        this.editedAuthorName = editedAuthorName;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public void setEditedAuthorSurname(String editedAuthorSurname) {
        this.editedAuthorSurname = editedAuthorSurname;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEditedTitle(String editedTitle) {
        this.editedTitle = editedTitle;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setEditedIsbn(String editedIsbn) {
        this.editedIsbn = editedIsbn;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setEditedYear(int editedYear) {
        this.editedYear = editedYear;
    }

    public void setEditedPrice(int editedPrice) {
        this.editedPrice = editedPrice;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    public Boolean getAddBtn() {
        return addBtn;
    }

    public void setAddBtn(Boolean addBtn) {
        this.addBtn = addBtn;
    }

    public int getPrice() {
        return price;
    }

    public int setPrice() {
        return price;
    }


}
