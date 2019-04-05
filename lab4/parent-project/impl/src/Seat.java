import javax.ejb.EJB;

public class Seat {

    private int row;
    private int number;
    private int price;
    private boolean booked = false;


    public Seat(int row, int number, int price) {
        this.row = row;
        this.number = number;
        this.price = price;
    }

    public Seat(){}

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public Boolean notBooked(){
        return !booked;
    }

}
