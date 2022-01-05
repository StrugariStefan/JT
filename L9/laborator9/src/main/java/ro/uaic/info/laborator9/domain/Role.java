package ro.uaic.info.laborator9.domain;

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
    
    private final List<Group> supportedRoles = Arrays.asList(new Group[] { new Group(ADMIN), new Group(AUTHOR), new Group(REVIEWER)});;
    
    @Produces 
    @SupportedRoles 
    List<Group> getSupportedRoles() {
        return supportedRoles;
    }
}
