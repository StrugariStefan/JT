package ro.uaic.info.laborator6.domain;

import java.util.List;

/**
 *
 * @author stefa
 */
public interface ResourcesRepository {
    public List<Resource> get();
    
    public void update(Resource resource);
}
