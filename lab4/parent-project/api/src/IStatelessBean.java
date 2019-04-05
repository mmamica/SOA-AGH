public interface IStatelessBean {

    public int checkPrice(int row, int number);
    public boolean checkAvailability(int row, int number);
    public int getNumberOfRows();
    public int getNumberOfNumbers();
    //public Seat getSeat(int row, int number);
    public boolean checkPassword(String login, String password);
    //public User getUser(String login);
    public boolean checkUser(String login);
}
