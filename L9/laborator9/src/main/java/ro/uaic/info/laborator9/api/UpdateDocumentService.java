package ro.uaic.info.laborator9.api;

import java.io.IOException;
import java.io.InputStream;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import ro.uaic.info.laborator9.business.DocumentsService;
import ro.uaic.info.laborator9.domain.User;
import ro.uaic.info.laborator9.domain.UsersRepository;

/**
 *
 * @author stefa
 */
@RequestScoped
@Named
@Path("/documents")
public class UpdateDocumentService {
    
    @Inject
    private SecurityContext securityContext;
    
    @Inject
    private UsersRepository usersRepository;
    
    @Inject 
    private DocumentsService documentsService;
    
    @PUT
    @Path("/{documentId}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response updateDocument(
        @PathParam("documentId") int documentId,
        @FormDataParam("content") InputStream uploadedDocumentInputStream,
        @FormDataParam("content") FormDataContentDisposition fileDetail) throws IOException {
        
        if (!securityContext.isCallerInRole("author")) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        String filename = fileDetail.getFileName();
        
        byte[] content = IOUtils.toByteArray(uploadedDocumentInputStream);
        uploadedDocumentInputStream.read(content);
        
        String userid = securityContext.getCallerPrincipal().getName();
        User author = usersRepository.getByUsername(userid).get();
        
        documentsService.updateDocument(documentId, filename, content, author);
        
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
