package ro.uaic.info.laborator1.filters;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author stefa
 */
public class BrowserFilter implements Filter {
    public static final String KEY_BROWSER_IDS = "browserIds";
    public static final String KEY_BAD_BROWSER_URL = "badBrowserUrl";
 
    private String[] browserIds;
    
    @Override
    public void init(FilterConfig cfg) throws ServletException
    {
        this.browserIds = new String[] { "Chrome", "Firefox", "Safari", "Opera", "MSIE 8", "MSIE 7", "MSIE 6" };
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        String userAgent = ((HttpServletRequest) request).getHeader("User-Agent");
        for (String browser_id : browserIds)
        {
            if (userAgent.contains(browser_id))
            {
                chain.doFilter(request, response);
                return;
            }
        }
        
        try (PrintWriter out = response.getWriter()) {
            out.println("This is the response for when the request is made from a non-browser application.");
        }
    }
    
    @Override
    public void destroy()
    {
        this.browserIds = null;
    }
}
