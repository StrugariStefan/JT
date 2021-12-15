package ro.uaic.info.laborator8.data;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ro.uaic.info.laborator8.domain.User;

/**
 *
 * @author stefa
 */
@StaticMetamodel(User.class)
public class User_ {
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> userRole;
}
