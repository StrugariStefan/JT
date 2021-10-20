package ro.uaic.info.laborator2.dictionary;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

/**
 *
 * @author stefa
 */
@Stateless
public class WordsRepositoryImpl implements WordsRepository {
    @PersistenceUnit
    EntityManagerFactory emf;
    
    @PersistenceContext
    EntityManager em;  
    
    public WordsRepositoryImpl() {
        emf = Persistence.createEntityManagerFactory("DictionaryService");
        em = emf.createEntityManager();
    }
    
    @Override
    public List<Word> Get() {
        TypedQuery<Word> query = em.createQuery("SELECT w FROM Word w", Word.class);
        List<Word> words = query.getResultList();
        
        return words;
    }

    @Override
    public void Create(Word word) {
        em.persist(word);
    }
}
