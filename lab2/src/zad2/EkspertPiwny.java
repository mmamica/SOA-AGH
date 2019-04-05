package zad2;

import java.util.ArrayList;

public class EkspertPiwny {
    private ArrayList<Piwo> baza;

    public EkspertPiwny(){
        baza = new ArrayList<>();
        baza.add(new Piwo("biale", "Żywiec"));
        baza.add(new Piwo("zote", "Książęce"));
        baza.add(new Piwo("ciemne", "Litovel"));
    }

    public String wyszukaj(String kolor){
        for(Piwo p : baza){
            System.out.println(p.getKolor());
            System.out.println(kolor);
            if(p.getKolor().equals(kolor)){
                return p.getNazwa();
            }
        }
        return ("nie znaleziono");
    }
}

