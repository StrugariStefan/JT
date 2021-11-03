package ro.uaic.info.laborator4.presentation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author stefa
 */
@Named
@ApplicationScoped
public class SessionBean {
    private int sessionCount;
    
    public String getSessionCount() {
        return sessionCount == 0 ? "" : String.valueOf(sessionCount);
    }
    
    public void setSessionCount() {
        sessionCount = SessionListener.getCount();
    }
}
