import javax.ejb.*;

@Remote(IStatefulBean.class)
@LocalBean
@Stateful
public class StatefulBean implements IStatefulBean {
    private User user;

    @EJB
    SingletonBean singletonBean;

    @EJB
    StatelessBean statelessBean;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void bookSeat(int row, int number){
        if(statelessBean.checkAvailability(row, number)){
            if (statelessBean.checkPrice(row, number)>user.getBudget()){
                throw new NoMoneyException("Brak fundyszy");
            }
            else{
                singletonBean.buyTicket(row, number);
                user.setBudget(user.getBudget()-statelessBean.checkPrice(row, number));
                statelessBean.getSeat(row, number).setBooked(true);
                user.getSeats().add(statelessBean.getSeat(row, number));
            }
        }
    }


}
