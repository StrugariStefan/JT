package ro.uaic.info.laborator4.presentation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author stefa
 */
@Named
@ApplicationScoped
public class VersionBean {
    private String version;
    
    public String getVersion() {
        String ver = this.getClass().getPackage().getImplementationVersion();
        
        return ver;
    }
}
