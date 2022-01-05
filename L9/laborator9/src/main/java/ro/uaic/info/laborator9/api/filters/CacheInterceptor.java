package ro.uaic.info.laborator9.api.filters;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import ro.uaic.info.laborator9.api.Models.DocumentDto;
import ro.uaic.info.laborator9.domain.Document;

/**
 *
 * @author stefa
 */
@Cached
@Interceptor
public class CacheInterceptor implements Serializable {

    @Inject
    private DocumentsCache documentsCache;
    
    @AroundInvoke
    public Object queryCache(InvocationContext invocationContext) throws Exception {
        String authorId = (String)invocationContext.getParameters()[0];
        
        List<DocumentDto> cachedDocuments = documentsCache.getDocumetsViewFromCache(authorId);
        
        List<DocumentDto> documentDtos;
        
        if (cachedDocuments != null) {
            return cachedDocuments;
        }
        
        documentDtos = (List<DocumentDto>)invocationContext.proceed();
        documentsCache.updateCachedDocumentsView(authorId, documentDtos);
        
        return documentDtos;
    }
}
