
package ro.uaic.info.laborator7.data;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import ro.uaic.info.laborator7.domain.Document;
import ro.uaic.info.laborator7.domain.DocumentsRepository;

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
    public void Create(Document document) {
        em.persist(document);
    }
}
