package ro.uaic.info.laborator8.business;

import java.util.List;
import ro.uaic.info.laborator8.domain.Document;
import ro.uaic.info.laborator8.domain.User;

/**
 *
 * @author stefa
 */
public interface DocumentsService {
    public void submitDocument(Document document);
    
    public void updateDocument(int documentId, String filename, byte[] content, User author);
    
    public void deleteDocument(int documentId, User author);
    
    public List<Document> getDocuments(int authorId);
}
