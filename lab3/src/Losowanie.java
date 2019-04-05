import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "Losowanie")
@RequestScoped

public class Losowanie {
    private String name;
    private String last_name;
    private String age;

    public Losowanie() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String wyslij(){
        if (Math.random() < 0.2)
            return "Wygrana";
        else
            return "Przegrana";
    }
}
