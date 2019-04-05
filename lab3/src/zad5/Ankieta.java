package zad5;

import javax.faces.validator.ValidatorException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean(name="Ankieta")
@ViewScoped
public class Ankieta {
    private String name;
    private String email;
    private Integer age;
    private String gender;
    private String education;
    private Double height;
    private Double breast;
    private String bra;
    private Double waist;
    private Double hips;
    private Double abs;
    private Double shoulders;
    private Double minHeight=150.0;
    private Double maxHeight=185.0;
    private String priceRange;
    private String howOften;
    private String clothes;
    private String color;
    private boolean firstPart=false;
    private boolean secondPart=false;
    private String[] displayClothes;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        createClothesList();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getBreast() {
        return breast;
    }

    public void setBreast(Double breast) {
        this.breast = breast;
    }

    public String getBra() {
        return bra;
    }

    public void setBra(String bra) {
        this.bra = bra;
    }

    public Double getWaist() {
        return waist;
    }

    public void setWaist(Double waist) {
        this.waist = waist;
    }

    public Double getHips() {
        return hips;
    }

    public void setHips(Double hips) {
        this.hips = hips;
    }

    public Double getAbs() {
        return abs;
    }

    public void setAbs(Double abs) {
        this.abs = abs;
    }

    public Double getShoulders() {
        return shoulders;
    }

    public void setShoulders(Double shoulders) {
        this.shoulders = shoulders;
    }

    public void addMeasures(){
        if(gender.equals("female")){
            minHeight=150.0;
            maxHeight=185.0;
            breast=0.0;
            bra=null;
            waist=0.0;
            hips=0.0;

        }else{
            minHeight=165.0;
            maxHeight=200.0;
            abs=0.0;
            shoulders=0.0;
        }
    }


    public void validateHeight(FacesContext context, UIComponent component,
                               Object value) throws ValidatorException {
        if(value==null || value.equals("")){
            throw new ValidatorException(new FacesMessage("Podaj wzrost"));
        }
        double val=(Double) value;
        if(val<minHeight || val>maxHeight){
            throw new ValidatorException(new FacesMessage("Zły zakres"));
        }
    }

    public void setFirstPart(){
        firstPart=true;   }
    public void setSecondPart(){
        secondPart=true;
        System.out.println("HEJKA");
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getHowOften() {
        return howOften;
    }

    public void setHowOften(String howOften) {
        this.howOften = howOften;
    }

    public String getClothes() {
        return clothes;
    }

    public void setClothes(String clothes) {
        this.clothes = clothes;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFirstPart() {
        return firstPart;
    }

    public void setFirstPart(boolean firstPart) {
        this.firstPart = firstPart;
    }

    public String[] getDisplayClothes() {
        return displayClothes;
    }

    public void setDisplayClothes(String[] displayClothes) {
        this.displayClothes = displayClothes;
    }


    public void createClothesList(){
        if(gender.equals("female")){
            displayClothes=new String[]{"sukienki", "bluzki", "żakiety", "obcasy", "torebki"};
        }else{
            displayClothes=new String[]{"adidasy","spodie","garnitury","koszule","krawaty", "marynarki"};
        }
    }

    public String displaySecondPart(){
        if(firstPart){
            return "block";
        }return "none";
    }

    public String displayPanel(){
        System.out.println(secondPart);
        if(secondPart){
            return "block";
        }return "none";
    }

    public String displayGender(String gender){
        if(this.gender==gender){
            return "block";
        }return "none";
    }

    public boolean isSecondPart() {
        return secondPart;
    }

    public void setSecondPart(boolean secondPart) {
        this.secondPart = secondPart;
    }
}
