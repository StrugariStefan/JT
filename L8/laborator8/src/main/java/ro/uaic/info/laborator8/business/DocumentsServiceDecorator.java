package ro.uaic.info.laborator8.business;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import ro.uaic.info.laborator8.crosscutting.LoggerConfiguration;
import ro.uaic.info.laborator8.domain.Document;
import ro.uaic.info.laborator8.domain.ResourceCreationOpened;
import ro.uaic.info.laborator8.domain.User;

/**
 *
 * @author stefa
 */
@Decorator
public class DocumentsServiceDecorator implements DocumentsService {

    @Inject
    @Delegate
    @Any 
    DocumentsService documentsService;
    
    @Inject
    @ResourceCreationOpened 
    Instance<Boolean> isResourceCreationOpened;
    
    @Inject
    @LoggerConfiguration
    Logger logger;
    
    @Override
    public void submitDocument(Document document) {
        if (!isResourceCreationOpened.get()) {
            return;
        }
        
        logger.log(Level.INFO, "Document with name {0} is submitted by {1} author.", new Object[]{document.getName(), document.getAuthor().getUsername()});
        
        documentsService.submitDocument(document);
    }

    @Override
    public void updateDocument(int documentId, String filename, byte[] content, User author) {
        if (!isResourceCreationOpened.get()) {
            return;
        }
        
        logger.log(Level.INFO, "Document with id {0} is being updated by {1} author", new Object[]{documentId, author.getUsername()});
        
        documentsService.updateDocument(documentId, filename, content, author);
    }

    @Override
    public void deleteDocument(int documentId, User author) {
        if (!isResourceCreationOpened.get()) {
            return;
        }
        
        logger.log(Level.INFO, "Document with id {0} is being deleted by {1} author", new Object[]{documentId, author.getUsername()});
        
        documentsService.deleteDocument(documentId, author);
    }

    @Override
    public List<Document> getDocuments(int authorId) {
        if (authorId != 0) {
            logger.log(Level.INFO, "Documents of author with id {0} are being queried.", new Object[]{authorId});
        } else {
            logger.log(Level.INFO, "All documents are being queried.");
        }
        
        return documentsService.getDocuments(authorId);
    }
}
