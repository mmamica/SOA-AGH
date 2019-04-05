package zad4;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Console;
import java.util.*;

@ManagedBean(name="Logic")
@ViewScoped
public class Logic {
    private NBPConnector nbp=new NBPConnector();
    private Double priceMin=0.0;
    private Double priceMax;
    private Set<Double> priceRange;
    private Set<String> currencyList;
    private String type;
    private Set<String> typeList;
    private LinkedList<Book> allBooks= (new DataBase()).getBooks();
    private LinkedList<Book> booksToDisplay= (new DataBase()).getBooks();
    private LinkedList<Book> selectedBooks = new LinkedList<>();
    private String currency="PLN";
    private Map<String,Boolean> isHidden=new HashMap<>();
    private Boolean ifTitle=true;
    private Boolean ifAuthor=true;
    private Boolean ifType=true;
    private Boolean ifPrice=true;
    private Boolean ifCurrency=true;
    private Boolean ifPages=true;
    private Boolean ifPLN=true;
    private Boolean ifPricePLN=true;

    private Integer booksSize=0;
    private Double booksPrice=0.0;

    public void setIsHidden() {
        isHidden.put("title", true);
        isHidden.put("author", true);
        isHidden.put("type", true);
        isHidden.put("price", true);
        isHidden.put("currency", true);
        isHidden.put("pages", true);
    }

    public Map<String, Boolean> getIsHidden() {
        return isHidden;
    }

    public Logic() throws Exception {
        setPriceRange();
        createCurrencyList();
        createTypeList();
        setIsHidden();
    };

    public Double getPriceMin() {
        return priceMin;
    }

    public void setPriceMax(Double priceMax) throws Exception {
        this.priceMax = priceMax;
        setBooksToDisplay();
    }

    public void setPriceMin(Double priceMin) throws Exception {
        this.priceMin = priceMin;
        setBooksToDisplay();
    }

    public Double getPriceMax() {
        return priceMax;
    }

    public Set<Double> getPriceRange() {
        return priceRange;
    }

    public void setPriceRange() throws Exception {
        this.priceRange = new TreeSet<Double>(){};
        for(Book b : allBooks){
            if(b.getCurrency().equals(currency)) {
                priceRange.add(b.getPrice());
            }else{
                priceRange.add(calculate(b.getCurrency(),currency,b.getPrice()));
            }
        }

        Double current=priceMin;
        for(Book b: allBooks) {
            current=calculate(b.getCurrency(),currency,b.getPrice());
        }
        priceRange.add(0.0);
        priceMax=current;
        priceMax=current;

    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) throws Exception {
        this.currency = currency;
        setBooksToDisplay();
    }

    public Set<String> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(Set<String> currencyList) {
        this.currencyList = currencyList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws Exception {
        this.type = type;
        setBooksToDisplay();
    }

    public Set<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(Set<String> typeList) {
        this.typeList = typeList;
    }

    public LinkedList<Book> getBooksToDisplay() {
        return booksToDisplay;
    }

    public void setBooksToDisplay() throws Exception {
        LinkedList<Book> newBooksToDisplay = new LinkedList<>();
        for(Book b : allBooks){
            if(calculate(b.getCurrency(),currency,b.getPrice())>=priceMin && calculate(b.getCurrency(),currency,b.getPrice())<=priceMax){
                if(b.getType().equals(type)){
                    newBooksToDisplay.add(b);
                };
            }
        }
        booksToDisplay=newBooksToDisplay;
    }


    private void createCurrencyList() throws Exception{
        currencyList=new HashSet<String>();
        for(Book b : allBooks){
            currencyList.add(b.getCurrency());
        }
    }


    private void createTypeList() throws Exception{
        typeList=new HashSet<String>();
        for(Book b : allBooks){
            typeList.add(b.getType());
        }
    }

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


    public void setIfTitle(Boolean ifTitle) {
        this.ifTitle = ifTitle;
    }

    public Boolean getIfTitle() {
        return ifTitle;
    }

    public Boolean getIfAuthor() {
        return ifAuthor;
    }

    public void setIfAuthor(Boolean ifAuthor) {
        this.ifAuthor = ifAuthor;
    }

    public Boolean getIfType() {
        return ifType;
    }

    public void setIfType(Boolean ifType) {
        this.ifType = ifType;
    }

    public Boolean getIfPrice() {
        return ifPrice;
    }

    public void setIfPrice(Boolean ifPrice) {
        this.ifPrice = ifPrice;
    }

    public Boolean getIfCurrency() {
        return ifCurrency;
    }

    public void setIfCurrency(Boolean ifCurrency) {
        this.ifCurrency = ifCurrency;
    }

    public Boolean getIfPages() {
        return ifPages;
    }

    public void setIfPages(Boolean ifPages) {
        this.ifPages = ifPages;
    }

    public LinkedList<Book> getSelectedBooks() {
        return selectedBooks;
    }

    public void setSelectedBooks() throws Exception {
        selectedBooks = new LinkedList<>();
        for(Book b : booksToDisplay){
            System.out.println(b.getSelected());
            if(b.isSelected()){
                selectedBooks.add(b);
                booksPrice=booksPrice+calculate(b.getCurrency(),"PLN",b.getPrice());
            }
        }
        booksSize=selectedBooks.size();
    }

    public Integer getBooksSize() {
        return booksSize;
    }

    public void setBooksSize(Integer booksSize) {
        this.booksSize = booksSize;
    }

    public Double getBooksPrice() {
        return booksPrice;
    }

    public void setBooksPrice(Double booksPrice) {
        this.booksPrice = booksPrice;
    }

    public Boolean getIfPLN() {
        return ifPLN;
    }

    public void setIfPLN(Boolean ifPLN) {
        this.ifPLN = ifPLN;
    }

    public Boolean getIfPricePLN() {
        return ifPricePLN;
    }

    public void setIfPricePLN(Boolean ifPricePLN) {
        this.ifPricePLN = ifPricePLN;
    }
}

