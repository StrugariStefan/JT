package ro.uaic.info.laborator9.domain;

import java.util.Optional;

/**
 *
 * @author stefa
 */
public interface UsersRepository {
    public Optional<User> getByUsername(String userid);
    
    public void Create(User user);
}
