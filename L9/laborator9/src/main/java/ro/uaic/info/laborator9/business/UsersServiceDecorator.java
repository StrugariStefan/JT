package ro.uaic.info.laborator9.business;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import ro.uaic.info.laborator9.domain.ResourceCreationOpened;
import ro.uaic.info.laborator9.domain.User;

/**
 *
 * @author stefa
 */
@Decorator
public class UsersServiceDecorator implements UsersService {

    @Inject
    @Delegate
    @Any 
    UsersService usersService;
    
    @Inject
    @ResourceCreationOpened 
    Instance<Boolean> isResourceCreationOpened;
    
    @Override
    public void RegisterUser(User user) {
        if (!isResourceCreationOpened.get()) {
            return;
        }
        
        usersService.RegisterUser(user);
    }
}
