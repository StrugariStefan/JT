package ro.uaic.info.laborator8.domain;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author stefa
 */
public interface DocumentsRepository {
    public void create(Document document);
    
    public void update(Document document);
    
    public void delete(Document document);
    
    public Optional<Document> getById(int id);
    
    public List<Document> getAll();
    
    public List<Document> getByAuthorId(int authorId);
}
