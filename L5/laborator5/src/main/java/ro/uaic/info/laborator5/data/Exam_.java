package ro.uaic.info.laborator5.data;

import java.time.LocalDateTime;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ro.uaic.info.laborator5.domain.Exam;
import ro.uaic.info.laborator5.domain.Student;

/**
 *
 * @author stefa
 */
@StaticMetamodel(Exam.class)
public class Exam_ {
    public static volatile SingularAttribute<Exam, String> name;
    public static volatile SingularAttribute<Exam, LocalDateTime> startingTime;
    public static volatile SingularAttribute<Exam, Short> durationInMinutes;
    public static volatile SingularAttribute<Exam, Integer> id;
    public static volatile SingularAttribute<Exam, Student> attendingStudents;
}
