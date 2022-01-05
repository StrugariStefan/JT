package ro.uaic.info.laborator9.api;

import java.util.HashSet;
import java.util.Set;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 *
 * @author stefa
 */
@DatabaseIdentityStoreDefinition(
 dataSourceLookup = "jdbc/MSSQL",
 callerQuery =
 "select PASSWORD from USERS where USERID = ?",
 groupsQuery =
 "select GROUPID from USERS where USERID = ?")
@ApplicationPath("/webapi")
public class DocumentApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        
        classes.add(AddDocumentService.class);
        classes.add(UpdateDocumentService.class);
        classes.add(DeleteDocumentService.class);
        classes.add(ViewDocumentService.class);
        classes.add(MultiPartFeature.class);
        
        return classes;
    }
}
