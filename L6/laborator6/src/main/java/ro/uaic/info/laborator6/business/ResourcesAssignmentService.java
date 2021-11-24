package ro.uaic.info.laborator6.business;

import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import ro.uaic.info.laborator6.domain.Exam;
import ro.uaic.info.laborator6.domain.Resource;
import ro.uaic.info.laborator6.domain.ResourcesRepository;

/**
 *
 * @author stefa
 */
@Stateful
public class ResourcesAssignmentService {
    @EJB
    private ResourcesRepository resourcesRepository;
    
    @EJB
    private ResourcesAssignmentsMap resourcesAssignmentsMap;
    
    public void assingResourcesForExam(Exam exam, List<Resource> resources) {
        List<Integer> resourcesIds = 
            resources
                .stream()
                .map(r -> r.getId())
                .collect(Collectors.toList());
        
        resourcesAssignmentsMap.Update(resourcesIds);
        
        resources.forEach((Resource r) -> {
            r.addExam(exam);
            resourcesRepository.update(r);
        });
    }
}
