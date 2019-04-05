import javax.ejb.ApplicationException;
import javax.ejb.EJBException;

@ApplicationException(rollback = true)
public class NoMoneyException extends EJBException {
    public NoMoneyException() {
        super();
    }

    public NoMoneyException(String message) {
        super("Brak funduszy");
    }
}
