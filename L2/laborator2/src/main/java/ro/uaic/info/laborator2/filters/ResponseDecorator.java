package ro.uaic.info.laborator2.filters;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stefa
 */
public class ResponseDecorator implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);
        
        chain.doFilter(request, responseWrapper);
        
        String content = responseWrapper.toString();
        String modifiedContent = "<p>Prelude</p>" + content + "<p>Coda</p>";
        
        PrintWriter out = response.getWriter();
        out.write(modifiedContent);
        out.flush();
    }
}
