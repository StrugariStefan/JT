package ro.uaic.info.laborator8.presentation;

import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import ro.uaic.info.laborator8.crosscutting.Logged;
import ro.uaic.info.laborator8.domain.Role;
import ro.uaic.info.laborator8.domain.User;
import ro.uaic.info.laborator8.domain.UsersRepository;

/**
 *
 * @author stefa
 */
@Named
@RequestScoped
public class LoginViewModel {
    @NotNull
    private String username;
    
    @NotNull
    private String password;
    
    @Inject
    private UsersRepository usersRepository;
    
    @Inject 
    private LoginSession loginSession;
    
    @Inject
    @Any
    Event<User> loginEvent;
    
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
    
    @Logged
    public String login() {
        Optional<User> maybeUser = usersRepository.getByUsernameAndPassword(username, password);
        
        if (!maybeUser.isPresent()) {
            return "index.xhtml";
        }
        
        User user = maybeUser.get();
        loginEvent.fire(user);
        
        if (user.getRole().equals(Role.ADMIN)) {
            return "registration.xhtml";
        }
        
        if (user.getRole().equals(Role.AUTHOR)) {
            return "submission.xhtml";
        }
        
        if (user.getRole().equals(Role.REVIEWER)) {
            return "index.xhtml";
        }
        
        return "index.xhtml";
    }
}
