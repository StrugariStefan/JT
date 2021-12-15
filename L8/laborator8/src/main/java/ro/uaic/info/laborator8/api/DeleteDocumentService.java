package ro.uaic.info.laborator8.api;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import ro.uaic.info.laborator8.business.DocumentsService;
import ro.uaic.info.laborator8.crosscutting.Logged;
import ro.uaic.info.laborator8.domain.User;
import ro.uaic.info.laborator8.presentation.LoginSession;

/**
 *
 * @author stefa
 */
@RequestScoped
@Named
@Path("/documents")
public class DeleteDocumentService {
    @Inject
    private LoginSession loginSession;
    
    @Inject 
    private DocumentsService documentsService;
    
    @DELETE
    @Path("/{documentId}")
    @Logged
    public Response updateDocument(
        @PathParam("documentId") int documentId) {
        
        if (!loginSession.isAuthor()) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        User author = loginSession.getUser().get();
        
        documentsService.deleteDocument(documentId, author);
        
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
