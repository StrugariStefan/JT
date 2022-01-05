package ro.uaic.info.laborator9.presentation;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import ro.uaic.info.laborator9.business.UsersService;
import ro.uaic.info.laborator9.crosscutting.Logged;
import ro.uaic.info.laborator9.domain.Group;
import ro.uaic.info.laborator9.domain.SupportedRoles;
import ro.uaic.info.laborator9.domain.User;

/**
 *
 * @author stefa
 */
@Named
@RequestScoped
@RolesAllowed("admin")
public class UserRegistrationViewModel {
    @NotNull
    private String username;
    
    @NotNull
    private String password;
    
    private String selectedRole;
    
    private Group[] existingRoles;
    
    @Inject
    @SupportedRoles
    private List<Group> supportedRoles;
    
    @Inject
    private UsersService usersService;
    
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
    
    public Group[] getExistingRoles() {
        existingRoles = (Group[])supportedRoles.toArray();
        
        return existingRoles;
    }
    
    @Logged
    public String add() {
        Group group = supportedRoles.stream().filter(x -> x.getGroupid().equals(selectedRole)).findFirst().get();
        
        User user = new User(username, password, group);
        usersService.RegisterUser(user);
        
        return "registration.xhtml";
    }

}
