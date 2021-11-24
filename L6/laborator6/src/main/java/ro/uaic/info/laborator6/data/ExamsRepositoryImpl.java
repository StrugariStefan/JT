package ro.uaic.info.laborator6.data;

import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ro.uaic.info.laborator6.domain.Exam;
import ro.uaic.info.laborator6.domain.ExamsRepository;
import ro.uaic.info.laborator6.domain.Student;

/**
 *
 * @author stefa
 */
@Stateless
public class ExamsRepositoryImpl implements ExamsRepository {
    @PersistenceContext
    EntityManager em;
    
    @Override
    public void create(Exam exam) {
        em.persist(exam);
    }
    
    @Override
    public Exam getById(int id) {
        return em.find(Exam.class, id);
    }

    @Override
    public List<Exam> get() {
        TypedQuery<Exam> query = em.createQuery("SELECT e FROM Exam e", Exam.class);
        List<Exam> exams = query.getResultList();
        
        return exams;
    }
    
    @Override
    public void update(Exam exam) {
        em.merge(exam);
    }
    
    @Override
    public void delete(Exam exam) {
        em.remove(exam);
    }

    @Override
    public List<Exam> findByCriteria(String examName, String studentName, LocalDateTime startDate, LocalDateTime endDate) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Exam> query = builder.createQuery(Exam.class);

        Root<Exam> exam = query.from(Exam.class);
        Join<Exam, Student> student = exam.join(Exam_.attendingStudents);

        Predicate filter = builder.conjunction();
        
        if (examName != null) {
            filter = builder.and(filter,
                    builder.like(exam.get(Exam_.name), examName));
        }
        
        if (studentName != null) {
            filter = builder.and(filter,
                builder.like(student.get(Student_.name), studentName));
        }
        
        if (startDate != null && endDate != null) {
            filter = builder.and(filter,
                builder.between(exam.get(Exam_.startingTime), startDate, endDate));
        }
        
        query.where(filter);
        
        TypedQuery<Exam> q = em.createQuery(query);
        List<Exam> exams = q.getResultList();
        
        return exams;
    }
}
