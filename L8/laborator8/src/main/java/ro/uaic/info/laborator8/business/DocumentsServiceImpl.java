package ro.uaic.info.laborator8.business;

import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import ro.uaic.info.laborator8.api.filters.DocumentsCache;
import ro.uaic.info.laborator8.domain.Document;
import ro.uaic.info.laborator8.domain.DocumentsRepository;
import ro.uaic.info.laborator8.domain.User;

/**
 *
 * @author stefa
 */
@RequestScoped
public class DocumentsServiceImpl implements DocumentsService {

    @Inject
    private DocumentsRepository documentsRepository;
    
    @Inject
    private DocumentsCache documentsCache;
    
    @Override
    public void submitDocument(Document document) {
        documentsRepository.create(document);
        clearCache(document);
    }

    @Override
    public void updateDocument(int documentId, String filename, byte[] content, User author) {
        Optional<Document> maybeDocument = documentsRepository.getById(documentId);
        
        if (!maybeDocument.isPresent()) {
            throw new IllegalArgumentException("Document with id " + documentId + " does not exist.");
        }
        
        Document document = maybeDocument.get();
        
        document.updateFilenameAndContent(filename, content, author);
        documentsRepository.update(document);
        clearCache(document);
    }
    
    @Override
    public void deleteDocument(int documentId, User author) {
        Optional<Document> maybeDocument = documentsRepository.getById(documentId);
        
        if (!maybeDocument.isPresent()) {
            throw new IllegalArgumentException("Document with id " + documentId + " does not exist.");
        }
        
        Document document = maybeDocument.get();
        
        if (!document.hasSpecificAuthor(author)) {
            throw new IllegalArgumentException("Author with id " + author.getId() + " is not allowed to delete document with id " + document.getId() + ".");
        }
        
        documentsRepository.delete(document);
        clearCache(document);
    }

    @Override
    public List<Document> getDocuments(int authorId) {
        List<Document> documents;
        
        if (authorId != 0) {
            documents = documentsRepository.getByAuthorId(authorId);
        } else {
            documents = documentsRepository.getAll();
        }
        
        return documents;
    }
    
    private void clearCache(Document document) {
        documentsCache.clearCachedDocumentsView(0);
        documentsCache.clearCachedDocumentsView(document.getAuthor().getId());
    }
}
