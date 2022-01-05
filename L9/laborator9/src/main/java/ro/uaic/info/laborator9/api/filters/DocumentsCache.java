package ro.uaic.info.laborator9.api.filters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import ro.uaic.info.laborator9.api.Models.DocumentDto;

/**
 *
 * @author stefa
 */
@ApplicationScoped
public class DocumentsCache {
    private Map<String, List<DocumentDto>> cache;
    
    public DocumentsCache() {
        this.cache = new HashMap<>();
    }
    
    public void updateCachedDocumentsView(String authorId, List<DocumentDto> documents) {
        cache.put(authorId, documents);
    }
    
    public List<DocumentDto> getDocumetsViewFromCache(String authorId) {
        return cache.get(authorId);
    }
    
    public void clearCachedDocumentsView(String authorId) {
        cache.remove(authorId);
    }
}
