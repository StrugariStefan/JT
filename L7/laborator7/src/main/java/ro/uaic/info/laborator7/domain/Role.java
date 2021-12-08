package ro.uaic.info.laborator7.domain;

import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 *
 * @author stefa
 */
@ApplicationScoped
public class Role {
    
    public static final String ADMIN = "admin";
    public static final String AUTHOR = "author";
    public static final String REVIEWER = "reviewer";
    
    private final List<String> supportedRoles = Arrays.asList(new String[] { ADMIN, AUTHOR, REVIEWER});;
    
    @Produces 
    @SupportedRoles 
    List<String> getSupportedRoles() {
        return supportedRoles;
    }
}
