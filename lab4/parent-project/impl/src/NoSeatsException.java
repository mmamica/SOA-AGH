import javax.ejb.ApplicationException;
import javax.ejb.EJBException;

@ApplicationException(rollback=true)
public class NoSeatsException extends EJBException {
    public NoSeatsException() {
        super();
    }

    public NoSeatsException(String message) {
        super(message);
    }
}
