package si.um.feri.measurements.jsf;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Named("language")
@SessionScoped
public class LanguageBean implements Serializable{
    private static final long serialVersionUID = 1L;

    private String localeCode;

    private static Map<String,Object> countries;
    static{
        Locale.Builder b = new Locale.Builder();
        countries = new LinkedHashMap<String,Object>();
        countries.put("English", Locale.ENGLISH); //label, value
        countries.put("Slovensko", b.setLanguage("sl").build());
    }

    public Map<String, Object> getCountriesInMap() {
        return countries;
    }


    public String getLocaleCode() {
        return localeCode;
    }


    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    //value change event listener
    public void countryLocaleCodeChanged(ValueChangeEvent e){

        String newLocaleValue = e.getNewValue().toString();

        //loop country map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {

            if(entry.getValue().toString().equals(newLocaleValue)){

                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale((Locale)entry.getValue());

            }
        }
    }

}
