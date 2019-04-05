import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;

@ManagedBean(name = "Theater")
@SessionScoped
public class Theater {
    private int row;
    private int number;
    private String login;
    private String password;
    private LinkedList<Seat> seats=new LinkedList<>();


    @EJB
    StatelessBean statelessBean;

    @EJB
    StatefulBean statefulBean;

    @EJB
    SingletonBean singletonBean;




    public Theater(int row, int number, String login, String password,LinkedList<Seat> seats){
        this.row=row;
        this.number=number;
        this.login=login;
        this.password=password;
        this.seats=statelessBean.getSeats();
    }

    public Theater(){}
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String doLogin(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        System.out.println(login);
        System.out.println(password);
        if(!statelessBean.checkPassword(login,password)){
            FacesMessage facesMessage = new FacesMessage("Błąd logowania!");
            facesContext.addMessage(null, facesMessage);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", login);
            return "index";
        }
            statefulBean.setUser(statelessBean.getUser(login));


            return "homePage";
    }


    public void check(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!statelessBean.checkUser(value.toString())) {
            throw new ValidatorException(new FacesMessage("Błąd Logowania!"));
        }
    }

    public String logOut(){
        statefulBean.setUser(null);
        login=null;
        password=null;
        number=0;
        row=0;
        return "index";
    }

    public User getUser(){
        return statefulBean.getUser();
    }

    public int getNumberOfRows(){
        return (int) statelessBean.getNumberOfRows();
    }

    public int getNumberOfNumbers(){
        return (int) statelessBean.getNumberOfNumbers();
    }

    public boolean checkAvailability(int row, int number){
        return statelessBean.checkAvailability(row,number);
    }

    public int getPrice(int row, int number){
        return statelessBean.checkPrice(row, number);
    }

    public Seat getSeat(int row, int number){
        return statelessBean.getSeat(row, number);
    }

    public LinkedList<Seat> getSeats() {
        return statelessBean.getSeats();
    }

    public void book(Seat s) throws Exception{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            statefulBean.bookSeat(s.getRow(), s.getNumber());
        }
        catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(e.getMessage());
            facesContext.addMessage(null, facesMessage);        }
    }

    public LinkedList<Seat> getUserSeats(){
        return getUser().getSeats();
    }

    public int sumCost(){
        return statelessBean.sum(getUser());
    }
}
