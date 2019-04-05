package zad5;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

@ManagedBean(name="validator")
@ViewScoped
public class Validator {
    private static final Pattern NAME_PATTERN = Pattern.compile("[A-Z][a-z]*");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    private static final Pattern BRA_PATTERN = Pattern.compile("[A-K]");

    public void validateName(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value==null || value.equals("")){
            throw new ValidatorException(new FacesMessage(("Pole wymagane!")));
        }
        String name = (String) value;
        boolean matches = NAME_PATTERN.matcher(name).matches();
        if (!matches) {
            throw new ValidatorException(new FacesMessage("Podaj poprawne imię!"));
        }
    }

    public void validateEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        if(value==null || value.equals("")){
            throw new ValidatorException(new FacesMessage("Pole wymagane!"));
        }
        String email = (String) value;
        boolean matches = EMAIL_PATTERN.matcher(email).matches();
        if (!matches) {
            throw new ValidatorException(new FacesMessage("Podaj poprawny email!"));
        }
    }

    public void validateAge(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        if(value==null || value.equals("")){
            throw new ValidatorException(new FacesMessage("Podaj dane"));
        }
        if(((Integer) value < 10) || ((Integer) value > 100)){
            throw new ValidatorException(new FacesMessage("Podaj poprawny wiek!"));
        }
    }

    public void validateBreast(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value==null || value.equals("")){
            throw new ValidatorException(new FacesMessage("Podaj dane"));
        }
        double val=(Double) value;
        if(val<80 || val>120){
            throw new ValidatorException(new FacesMessage("Dane spoza zakresu"));
        }
    }

    public void validateBra(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value==null || value.equals("")){
            throw new ValidatorException(new FacesMessage("Pole wymagane!"));
        }
        String bra = (String) value;
        boolean matches = BRA_PATTERN.matcher(bra).matches();
        if (!matches) {
            throw new ValidatorException(new FacesMessage("Podaj poprawne dane!"));
        }
    }

    public void validateWaist(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value==null || value.equals("")){
            throw new ValidatorException(new FacesMessage("Podaj dane"));
        }
        double val=(Double) value;
        if(val<50 || val>120){
            throw new ValidatorException(new FacesMessage("Dane spoza zakresu"));
        }
    }

    public void validateHips(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value==null || value.equals("")){
            throw new ValidatorException(new FacesMessage("Podaj dane"));
        }
        double val=(Double) value;
        if(val<80 || val>130){
            throw new ValidatorException(new FacesMessage("Dane spoza zakresu"));
        }
    }


    public void validateAbs(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value==null || value.equals("")){
            throw new ValidatorException(new FacesMessage("Podaj dane"));
        }
        double val=(Double) value;
        if(val<50 || val>120){
            throw new ValidatorException(new FacesMessage("Dane spoza zakresu"));
        }
    }

    public void validateShoulders(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value==null || value.equals("")){
            throw new ValidatorException(new FacesMessage("Podaj dane"));
        }
        double val=(Double) value;
        if(val<70 || val>160){
            throw new ValidatorException(new FacesMessage("Dane spoza zakresu"));
        }
    }




}
