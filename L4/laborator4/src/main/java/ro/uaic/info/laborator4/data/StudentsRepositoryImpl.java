package ro.uaic.info.laborator4.data;

import javax.ejb.Stateless; 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ro.uaic.info.laborator4.domain.Student;
import ro.uaic.info.laborator4.domain.StudentsRepository;

/**
 *
 * @author stefa
 */
@Stateless
public class StudentsRepositoryImpl implements StudentsRepository {
    @PersistenceContext
    EntityManager em;
    
    @Override
    public void Create(Student student) {
        em.persist(student);
    }
}
