package ro.uaic.info.laborator9.api;

import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import ro.uaic.info.laborator9.api.Models.DocumentDto;
import ro.uaic.info.laborator9.api.filters.Cached;
import ro.uaic.info.laborator9.business.DocumentsService;
import ro.uaic.info.laborator9.domain.Document;

/**
 *
 * @author stefa
 */
@RequestScoped
@Named
@Path("/documents")
public class ViewDocumentService {
    
    @Inject 
    private DocumentsService documentsService;
    
    @GET
    @Cached
    @Produces(MediaType.APPLICATION_JSON)
    public List<DocumentDto> createDocument(@DefaultValue("0") @QueryParam("authorId") String authorId) {
        
        List<Document> documents = documentsService.getDocuments(authorId);
        
        List<DocumentDto> documentDtos = 
                documents
                        .stream()
                        .map(d -> new DocumentDto(d.getId(), d.getName(), d.getFilename(), d.getContent()))
                        .collect(Collectors.toList());
        
        return documentDtos;
    }
    
}
