package ro.uaic.info.laborator6.data;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ro.uaic.info.laborator6.domain.Exam;
import ro.uaic.info.laborator6.domain.Resource;

/**
 *
 * @author stefa
 */
@StaticMetamodel(Exam.class)
public class Resource_ {
    public static volatile SingularAttribute<Resource, String> name;
    public static volatile SingularAttribute<Resource, Integer> capacity;
    public static volatile SingularAttribute<Resource, Integer> id;
    public static volatile SingularAttribute<Resource, Exam> examsThatUseTheResource;
}
