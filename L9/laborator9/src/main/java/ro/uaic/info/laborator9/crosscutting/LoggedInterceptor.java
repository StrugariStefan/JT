package ro.uaic.info.laborator9.crosscutting;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author stefa
 */
@Logged
@Interceptor
public class LoggedInterceptor implements Serializable {
    
    @Inject
    @LoggerConfiguration
    private Logger logger;
    
    @AroundInvoke
    public Object logMessage(InvocationContext invocationContext) throws Exception {
        Object result = null;
        
        logger.log(Level.INFO, "Entering method: {0} in class {1}", new Object[]{invocationContext.getMethod().getName(), invocationContext.getMethod().getDeclaringClass().getName()});
            
        try {
            result = invocationContext.proceed();
        } catch (Exception e) {
            String exceptionMessage = e.getMessage();
            logger.log(Level.SEVERE, "Error: {0}", exceptionMessage);
            throw e;
        }

        return result;
    }
}
