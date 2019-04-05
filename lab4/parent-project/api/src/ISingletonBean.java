import java.util.LinkedList;

public interface ISingletonBean {
    public LinkedList<Seat> getSeatList();
    public int getSeatPrice(int row, int number);
    public void buyTicket(int row, int number);
}
