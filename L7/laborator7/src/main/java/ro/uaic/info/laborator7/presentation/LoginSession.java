package ro.uaic.info.laborator7.presentation;

import java.io.Serializable;
import java.util.Optional;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import ro.uaic.info.laborator7.domain.Role;
import ro.uaic.info.laborator7.domain.User;

/**
 *
 * @author stefa
 */
@SessionScoped
public class LoginSession implements Serializable {
    private Optional<User> user;
    
    public Optional<User> getUser() {
        return user;
    }
    
    public void setUser(@Observes User user) {
        this.user = Optional.of(user);
    }
    
    public void logout() {
        user = Optional.empty();
    }
    
    public boolean isAuthor() {
        return hasRole(Role.AUTHOR);
    }
    
    public boolean isReviewer() {
        return hasRole(Role.REVIEWER);
    }
    
    public boolean isAdmin() {
        return hasRole(Role.ADMIN);
    }
    
    public boolean isLoggedOut() {
        return !user.isPresent();
    }
    
    private boolean hasRole(String role) {
        if (isLoggedOut()) {
            return false;
        }
        
        return user.get().getRole().equals(role);
    }
}
