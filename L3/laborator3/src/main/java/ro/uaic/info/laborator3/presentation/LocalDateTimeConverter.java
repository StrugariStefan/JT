package ro.uaic.info.laborator3.presentation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author stefa
 */
@FacesConverter("localDateTimeConverter")
public class LocalDateTimeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(string, formatter);
        
        return dateTime;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        return t.toString();
    }
    
}
