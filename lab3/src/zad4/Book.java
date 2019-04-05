package zad4;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="DataBase")
@ViewScoped
public class Book {
    private String title;
    private String author;
    private String type;
    private Double price;
    private String currency;
    private Integer pages;
    private boolean selected;
    private NBPConnector nbp=new NBPConnector();
    private Double pln;

    public Book(String title, String author, String type, Double price, String currency, Integer pages) throws Exception {
        this.title = title;
        this.author = author;
        this.type = type;
        this.price = price;
        this.currency = currency;
        this.pages = pages;
        this.selected = false;
        pln=calculate(currency,"PLN",price);
    }

    public Book(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String genre) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }


    public boolean isSelected() {
        return selected;
    }

    public boolean getSelected(){
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected; }


    private Double calculate(String from,String to,Double value) throws Exception{
        Double fromNbp=1.0;
        Double toNbp=1.0;
        if(!from.equals("PLN")){
            fromNbp = Double.parseDouble(nbp.exchangeRate(from.toLowerCase()));
        }
        if(!to.equals("PLN")){
            toNbp=Double.parseDouble(nbp.exchangeRate(to.toLowerCase()));
        }
        Double newValue=(value*fromNbp)/toNbp;
        return Math.round(newValue * 100.0) / 100.0;
    }

    public Double getPln() {
        return pln;
    }

    public void setPln(Double pln) {
        this.pln = pln;
    }
}
