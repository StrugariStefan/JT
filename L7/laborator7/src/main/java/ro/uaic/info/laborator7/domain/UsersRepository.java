package ro.uaic.info.laborator7.domain;

import java.util.Optional;

/**
 *
 * @author stefa
 */
public interface UsersRepository {
    public Optional<User> getByUsernameAndPassword(String username, String password);
    
    public void Create(User user);
}
