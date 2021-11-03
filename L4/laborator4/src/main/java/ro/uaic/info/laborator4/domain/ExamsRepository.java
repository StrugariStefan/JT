package ro.uaic.info.laborator4.domain;

import java.util.List;

/**
 *
 * @author stefa
 */
public interface ExamsRepository {
    public void Create(Exam exam);
    
    public List<Exam> Get();
}
