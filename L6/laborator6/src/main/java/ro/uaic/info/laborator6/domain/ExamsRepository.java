package ro.uaic.info.laborator6.domain;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author stefa
 */
public interface ExamsRepository {
    public void create(Exam exam);
    
    public Exam getById(int id);
    
    public List<Exam> get();
    
    public void update(Exam exam);
    
    public void delete(Exam exam);
    
    public List<Exam> findByCriteria(String examName, String studentName, LocalDateTime startDate, LocalDateTime endDate);
}
