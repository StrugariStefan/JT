package ro.uaic.info.laborator7.business;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import ro.uaic.info.laborator7.crosscutting.LoggerConfiguration;
import ro.uaic.info.laborator7.domain.Document;
import ro.uaic.info.laborator7.domain.ResourceCreationOpened;

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
    public void SubmitDocument(Document document) {
        if (!isResourceCreationOpened.get()) {
            return;
        }
        
        logger.log(Level.INFO, "Document with name {0} is submitted by {1} author.", new Object[]{document.getName(), document.getAuthor().getUsername()});
        
        documentsService.SubmitDocument(document);
    }
}
