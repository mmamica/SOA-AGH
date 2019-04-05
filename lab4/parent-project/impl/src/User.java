import java.util.LinkedList;

public class User {

    private String name;
    private String login;
    private String password;
    private int budget;
    private LinkedList<Seat> seats = new LinkedList<Seat>();

    public User(String name, String login, String password, int budget) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.budget = budget;
    }

    public User(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public LinkedList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(LinkedList<Seat> seats) {
        this.seats = seats;
    }
}
