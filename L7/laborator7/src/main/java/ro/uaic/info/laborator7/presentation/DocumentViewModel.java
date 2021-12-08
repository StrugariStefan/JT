package ro.uaic.info.laborator7.presentation;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;
import org.apache.commons.io.IOUtils;
import ro.uaic.info.laborator7.business.DocumentsService;
import ro.uaic.info.laborator7.crosscutting.Logged;
import ro.uaic.info.laborator7.domain.Document;
import ro.uaic.info.laborator7.domain.User;

/**
 *
 * @author stefa
 */
@Named
@RequestScoped
public class DocumentViewModel {
    
    @NotNull
    private String name;
    
    @NotNull
    private Part uploadedFile;
    
    private String filename;
    
    private byte[] content;
    
    @Inject
    private LoginSession loginSession;
    
    @Inject 
    private DocumentsService documentsService;
    
    public Part getUploadedFile() {
        return uploadedFile;
    }
    
    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Logged
    public String upload() throws IOException {
        if (!loginSession.isAuthor()) {
            return "index.xhtml";
        }
        
        filename = Paths.get(uploadedFile.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        InputStream input = uploadedFile.getInputStream();
        
        content = IOUtils.toByteArray(input);
        
        input.read(content);
        
        User user = loginSession.getUser().get();
        
        Document document = new Document(name, filename, content, user);
        
        documentsService.SubmitDocument(document);
        
        return "submission.xhtml";
    }
    
}
