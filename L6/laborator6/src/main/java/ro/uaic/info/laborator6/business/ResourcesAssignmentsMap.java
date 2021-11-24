package ro.uaic.info.laborator6.business;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import ro.uaic.info.laborator6.domain.Resource;
import ro.uaic.info.laborator6.domain.ResourcesRepository;

/**
 *
 * @author stefa
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Startup
public class ResourcesAssignmentsMap implements Serializable {
    @EJB
    private ResourcesRepository resourcesRepository;
    
    private List<Resource> resources;
    
    private Map<Integer, Integer> resourceAssignments;
    
    @PostConstruct
    public void init() {
        resources = resourcesRepository.get();
        resourceAssignments = new HashMap<>();
        
        resources.forEach((Resource r) -> {
            resourceAssignments.put(r.getId(), r.getCapacity());
        });
    }
    
    @Lock(LockType.WRITE)
    public void Update(List<Integer> resourcesIds) {
        if (!isAssignmentValid(resourcesIds)) {
            throw new IllegalArgumentException("Not all resources are available.");
        }
        
        resourcesIds.forEach(id -> {
            resourceAssignments.put(id, resourceAssignments.get(id) - 1);
        });
    }
    
    @Lock(LockType.READ)
    public boolean isAssignmentValid(List<Integer> resourcesIds) {
        return resourcesIds.stream().noneMatch(id -> (resourceAssignments.get(id) == 0));
    }
}
