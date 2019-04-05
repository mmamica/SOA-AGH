import javax.ejb.*;
import java.util.LinkedList;

@Remote(IRemoteStatelessBean. class)
@LocalBean
@Stateless
public class StatelessBean implements IStatelessBean{

    @EJB
    SingletonBean singleton;
    public int checkPrice(int row, int number) {
        return singleton.getSeatPrice(row, number);
    }

    public boolean checkAvailability(int row, int number) {
        return !getSeat(row, number).isBooked();
    }

    public int getNumberOfRows() {
        return singleton.getNumberOfRows();
    }

    public int getNumberOfNumbers() {
        return singleton.getNumberOfSeats();
    }

    public Seat getSeat(int row, int number) {
        for(Seat s : singleton.getSeatList()){
            if(s.getRow()==row && s.getNumber()==number){
                return s;
            };
        };
        return null;
    }

    public boolean checkPassword(String login, String password) {
        for(User u:singleton.getUsers()){
            if(u.getLogin().equals(login) && u.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public User getUser(String login) {
        for(User u:singleton.getUsers()){
            if(u.getLogin().equals(login)){
                return u;
            }
        }
        return null;
    }

    public boolean checkUser(String login) {
        for(User u:singleton.getUsers()){
            if(u.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }

    public int sum(User user){
        int sum=0;
        for(Seat s:user.getSeats()){
            sum+=s.getPrice();
        }
        return sum;
    }

    public LinkedList<Seat> getSeats(){
        return singleton.getSeats();
    }
}
