package ro.uaic.info.laborator7.business;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import ro.uaic.info.laborator7.domain.Document;
import ro.uaic.info.laborator7.domain.DocumentsRepository;

/**
 *
 * @author stefa
 */
@RequestScoped
public class DocumentsServiceImpl implements DocumentsService {

    @Inject
    private DocumentsRepository documentsRepository;
    
    @Override
    public void SubmitDocument(Document document) {
        documentsRepository.Create(document);
    }
}
