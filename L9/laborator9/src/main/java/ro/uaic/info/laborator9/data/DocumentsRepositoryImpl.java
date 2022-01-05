package ro.uaic.info.laborator9.data;

import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import ro.uaic.info.laborator9.domain.Document;
import ro.uaic.info.laborator9.domain.DocumentsRepository;
import ro.uaic.info.laborator9.domain.User;

/**
 *
 * @author stefa
 */
@RequestScoped
@Transactional
public class DocumentsRepositoryImpl implements DocumentsRepository {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public void create(Document document) {
        em.persist(document);
    }

    @Override
    public void update(Document document) {
        em.merge(document);
    }
    
    @Override
    public void delete(Document document) {
        if (!em.contains(document)) {
            document = em.merge(document);
        }
        
        em.remove(document);
    }

    @Override
    public Optional<Document> getById(int id) {
        Document document = em.find(Document.class, id);
        
        return Optional.ofNullable(document);
    }

    @Override
    public List<Document> getAll() {
        TypedQuery<Document> query = em.createQuery("SELECT d FROM Document d", Document.class);
        List<Document> documents = query.getResultList();
        
        return documents;
    }

    @Override
    public List<Document> getByAuthorId(String authorId) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Document> query = builder.createQuery(Document.class);

        Root<Document> document = query.from(Document.class);
        Join<Document, User> author = document.join(Document_.author);
        
        Predicate filter = builder.equal(author.get(User_.userid), authorId);
        
        query.where(filter);
        
        TypedQuery<Document> q = em.createQuery(query);
        List<Document> documents = q.getResultList();
        
        return documents;
    }
}
