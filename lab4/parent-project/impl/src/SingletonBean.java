import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.LinkedList;
import java.util.Random;

@Remote(IRemoteSingletonBean .class)
@LocalBean
@Singleton
public class SingletonBean implements ISingletonBean {
    private LinkedList<Seat> seats;
    private LinkedList<User> users;
    private final int numberOfRows=5;
    private final int numberOfSeats=5;


    @PostConstruct
    public void init() {
        seats = new LinkedList<Seat>();
        int prices[]={100,150,200};
        Random rand = new Random();
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                int randomIndex = rand.nextInt(3);
                seats.add(new Seat(i,j,prices[randomIndex]));
            }
            users = new LinkedList<User>();
            users.add(new User("Maria", "mamrysia", "haslo",1000));
            users.add(new User("Karolina", "karty", "haslo",600));
            users.add(new User("Agnieszka", "agusia", "haslo",800));
        }
    }

    @Lock
    public LinkedList<Seat> getSeatList() {
        return seats;
    }

    @Lock
    public int getSeatPrice(int row, int number) {
        for(Seat seat : seats){
            if(seat.getNumber()==number && seat.getRow()==row){
                return seat.getPrice();
            }
        }
        return 0;
    }

    @Lock
    public void buyTicket(int row, int number) {
        for(Seat seat : seats){
            if(seat.getNumber()==number && seat.getRow()==row){
                seat.setBooked(true);
            }
        }
    }

    public LinkedList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(LinkedList<Seat> seats) {
        this.seats = seats;
    }

    public LinkedList<User> getUsers() {
        return users;
    }

    public void setUsers(LinkedList<User> users) {
        this.users = users;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }
}
