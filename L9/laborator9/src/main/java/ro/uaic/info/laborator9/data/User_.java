package ro.uaic.info.laborator9.data;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ro.uaic.info.laborator9.domain.Group;
import ro.uaic.info.laborator9.domain.User;

/**
 *
 * @author stefa
 */
@StaticMetamodel(User.class)
public class User_ {
    public static volatile SingularAttribute<User, String> userid;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Group> group;
}
