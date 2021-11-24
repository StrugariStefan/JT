package ro.uaic.info.laborator6.data;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import ro.uaic.info.laborator6.domain.Resource;
import ro.uaic.info.laborator6.domain.ResourcesRepository;

/**
 *
 * @author stefa
 */
@Stateless
public class ResourcesRepositoryImpl implements ResourcesRepository {
    @PersistenceContext
    EntityManager em;
    
    @Override
    public List<Resource> get() {
        TypedQuery<Resource> query = em.createQuery("SELECT r FROM Resource r", Resource.class);
        List<Resource> resources = query.getResultList();
        
        return resources;
    }

    @Override
    public void update(Resource resource) {
        em.merge(resource);
    }
}
