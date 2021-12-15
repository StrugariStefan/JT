package ro.uaic.info.laborator8.api.filters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import ro.uaic.info.laborator8.api.Models.DocumentDto;

/**
 *
 * @author stefa
 */
@ApplicationScoped
public class DocumentsCache {
    private Map<Integer, List<DocumentDto>> cache;
    
    public DocumentsCache() {
        this.cache = new HashMap<>();
    }
    
    public void updateCachedDocumentsView(int authorId, List<DocumentDto> documents) {
        cache.put(authorId, documents);
    }
    
    public List<DocumentDto> getDocumetsViewFromCache(int authorId) {
        return cache.get(authorId);
    }
    
    public void clearCachedDocumentsView(int authorId) {
        cache.remove(authorId);
    }
}
