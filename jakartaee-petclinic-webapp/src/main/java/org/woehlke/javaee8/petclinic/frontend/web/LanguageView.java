package org.woehlke.javaee8.petclinic.frontend.web;

import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface LanguageView extends Serializable {

    Map<String,String> getCountries();
    void setCountries(List<SelectItem> countries);

    Locale getLocale();
    void setLocale(Locale locale);

    String getLocaleSelected();
    void setLocaleSelected(String localeSelected);

    String changeLanguage();

    String getMsgCantDeleteSpecialty();
}
