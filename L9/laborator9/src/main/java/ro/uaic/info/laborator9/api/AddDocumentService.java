package ro.uaic.info.laborator9.api;

import java.io.IOException;
import java.io.InputStream;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import ro.uaic.info.laborator9.api.Models.DocumentModel;
import ro.uaic.info.laborator9.business.DocumentsService;
import ro.uaic.info.laborator9.domain.Document;
import ro.uaic.info.laborator9.domain.User;
import ro.uaic.info.laborator9.domain.UsersRepository;

/**
 *
 * @author stefa
 */
@RequestScoped
@Named
@Path("/documents")
public class AddDocumentService {
    
    @Inject
    private SecurityContext securityContext;
    
    @Inject
    private UsersRepository usersRepository;
    
    @Inject 
    private DocumentsService documentsService;
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createDocument(
        @FormDataParam("content") InputStream uploadedDocumentInputStream,
        @FormDataParam("content") FormDataContentDisposition fileDetail,
        @FormDataParam("document") FormDataBodyPart jsonPart) throws IOException {
        
        if (!securityContext.isCallerInRole("author")) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        jsonPart.setMediaType(MediaType.APPLICATION_JSON_TYPE);
        DocumentModel documentModel = jsonPart.getValueAs(DocumentModel.class);
        
        String filename = fileDetail.getFileName();
        
        byte[] content = IOUtils.toByteArray(uploadedDocumentInputStream);
        uploadedDocumentInputStream.read(content);
        
        String userid = securityContext.getCallerPrincipal().getName();
        User user = usersRepository.getByUsername(userid).get();
        Document document = new Document(documentModel.name, filename, content, user);
        
        documentsService.submitDocument(document);
        
        return Response.status(Response.Status.CREATED).build();
    }
}
