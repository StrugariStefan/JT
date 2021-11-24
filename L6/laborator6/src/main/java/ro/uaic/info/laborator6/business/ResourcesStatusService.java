package ro.uaic.info.laborator6.business;

import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ro.uaic.info.laborator6.domain.Exam;
import ro.uaic.info.laborator6.domain.ExamsRepository;
import ro.uaic.info.laborator6.domain.Resource;
import ro.uaic.info.laborator6.domain.ResourcesRepository;

/**
 *
 * @author stefa
 */
@Stateless
public class ResourcesStatusService {
    @EJB
    private ResourcesRepository resourcesRepository;
    
    @EJB
    private ExamsRepository examsRepository;
    
    public List<Resource> getAvailableResources() {
        return 
            resourcesRepository
                .get()
                .stream()
                .filter((Resource r) -> r.isAvailable())
                .collect(Collectors.toList());
    }
    
    public List<Exam> getExamsForWhichResourcesWhereNotAssigned() {
        return
            examsRepository
                .get()
                .stream()
                .filter((Exam e) -> !e.hasResourcesAssigned())
                .collect(Collectors.toList());
    }
}
