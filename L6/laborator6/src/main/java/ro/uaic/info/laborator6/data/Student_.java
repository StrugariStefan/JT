package ro.uaic.info.laborator6.data;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ro.uaic.info.laborator6.domain.Exam;
import ro.uaic.info.laborator6.domain.Student;

/**
 *
 * @author stefa
 */
@StaticMetamodel(Student.class)
public class Student_ {
    public static volatile SingularAttribute<Student, String> name;
    public static volatile SingularAttribute<Student, Integer> id;
    public static volatile SingularAttribute<Student, Exam> examsToAttend;
}
