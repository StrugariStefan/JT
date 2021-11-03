package ro.uaic.info.laborator4.data;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ro.uaic.info.laborator4.domain.Exam;
import ro.uaic.info.laborator4.domain.ExamsRepository;

/**
 *
 * @author stefa
 */
@Stateless
public class ExamsRepositoryImpl implements ExamsRepository {
    @PersistenceContext
    EntityManager em;
    
    @Override
    public void Create(Exam exam) {
        em.persist(exam);
    }

    @Override
    public List<Exam> Get() {
        TypedQuery<Exam> query = em.createQuery("SELECT e FROM Exam e", Exam.class);
        List<Exam> exams = query.getResultList();
        
        return exams;
    }
}
