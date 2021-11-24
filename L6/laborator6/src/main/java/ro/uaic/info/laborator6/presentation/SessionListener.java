package ro.uaic.info.laborator6.presentation;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 * Web application lifecycle listener.
 *
 * @author stefa
 */
public class SessionListener implements HttpSessionListener {

    private static int count;
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        count++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        count--;
    }
    
    public static int getCount() {
        return count;
    }
}
