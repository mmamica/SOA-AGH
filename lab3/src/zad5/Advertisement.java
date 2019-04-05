package zad5;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="Advertisement")
@ViewScoped
public class Advertisement {

    private int clicker=0;


    private static final String[] addList=new String[]{"https://sprawnymarketing.pl/wp-content/uploads/2016/12/unnamed-5.png",
            "https://dih.pl/wp-content/uploads/2015/04/blog-na-luzie-big.png",
            "https://podroze.smcloud.net/t/photos/t/144056/gdzie-na-wakacje-znajdz-najlepsze-loty-z-warszawy_1129071.jpg",
            "http://bi.gazeta.pl/im/24/c0/12/z19661092IH,Bo-milosc-jest-skomplikowana.jpg"};

    public String generate(){
        int randomNumber = (int)(Math.random() * 4);
        return addList[randomNumber];
    }

    public void add(){
        clicker++;
    }

    public int getClicker() {
        return clicker;
    }

    public void setClicker(int clicker) {
        this.clicker = clicker;
    }
}
