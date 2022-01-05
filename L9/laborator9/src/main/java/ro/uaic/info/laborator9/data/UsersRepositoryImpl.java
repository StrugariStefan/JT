package ro.uaic.info.laborator9.data;

import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import ro.uaic.info.laborator9.domain.User;
import ro.uaic.info.laborator9.domain.UsersRepository;

/**
 *
 * @author stefa
 */
@RequestScoped
@Transactional
public class UsersRepositoryImpl implements UsersRepository {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public Optional<User> getByUsername(String userid) {
        if (userid == null) {
            throw new IllegalArgumentException("Username should not be null or empty.");
        }
        
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);

        Root<User> exam = query.from(User.class);
        Predicate filter = builder.like(exam.get(User_.userid), userid);
        
        query.where(filter);
        
        TypedQuery<User> q = em.createQuery(query);
        List<User> users = q.getResultList();
        
        return Optional.ofNullable(users.get(0));
    }

    @Override
    public void Create(User user) {
        em.persist(user);
    }
}
