package ro.uaic.info.laborator9.business;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import ro.uaic.info.laborator9.domain.User;
import ro.uaic.info.laborator9.domain.UsersRepository;

/**
 *
 * @author stefa
 */
@RequestScoped
public class UsersServiceImpl implements UsersService {
    
    @Inject
    private UsersRepository usersRepository;
    
    @Override
    public void RegisterUser(User user) {
        usersRepository.Create(user);
    }
    
}
