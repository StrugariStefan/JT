package ro.uaic.info.laborator9.api;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import ro.uaic.info.laborator9.business.DocumentsService;
import ro.uaic.info.laborator9.crosscutting.Logged;
import ro.uaic.info.laborator9.domain.User;
import ro.uaic.info.laborator9.domain.UsersRepository;

/**
 *
 * @author stefa
 */
@RequestScoped
@Named
@Path("/documents")
public class DeleteDocumentService {
    
    @Inject
    private SecurityContext securityContext;
    
    @Inject
    private UsersRepository usersRepository;
    
    @Inject 
    private DocumentsService documentsService;
    
    @DELETE
    @Path("/{documentId}")
    @Logged
    public Response updateDocument(
        @PathParam("documentId") int documentId) {
        
        if (!securityContext.isCallerInRole("author")) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        String userid = securityContext.getCallerPrincipal().getName();
        User author = usersRepository.getByUsername(userid).get();
        
        documentsService.deleteDocument(documentId, author);
        
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
