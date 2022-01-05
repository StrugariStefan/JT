package ro.uaic.info.laborator9.domain;

import java.util.Calendar;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 *
 * @author stefa
 */
@ApplicationScoped
public class ResourceCreationTimeFrame {
    
    @Produces
    @Named
    @ResourceCreationOpened
    boolean IsResourceCreationOpened() {
        
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        
//        return hour > 2 && hour <= 13;
        return true;
    }
}
