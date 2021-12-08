package ro.uaic.info.laborator7.presentation;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import ro.uaic.info.laborator7.business.UsersService;
import ro.uaic.info.laborator7.crosscutting.Logged;
import ro.uaic.info.laborator7.domain.SupportedRoles;
import ro.uaic.info.laborator7.domain.User;

/**
 *
 * @author stefa
 */
@Named
@RequestScoped
public class UserRegistrationViewModel {
    @NotNull
    private String username;
    
    @NotNull
    private String password;
    
    private String selectedRole;
    
    private String[] existingRoles;
    
    @Inject
    @SupportedRoles
    private List<String> supportedRoles;
    
    @Inject
    private UsersService usersService;
    
    @Inject
    private LoginSession loginSession;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }
    
    public String getSelectedRole() {
        return selectedRole;
    }
    
    public String[] getExistingRoles() {
        existingRoles = (String[])supportedRoles.toArray();
        
        return existingRoles;
    }
    
    @Logged
    public String add() {
        if (!loginSession.isAdmin()) {
            return "index.xhtml";
        }
        
        User user = new User(username, password, selectedRole);
        
        usersService.RegisterUser(user);
        
        return "registration.xhtml";
    }

}
