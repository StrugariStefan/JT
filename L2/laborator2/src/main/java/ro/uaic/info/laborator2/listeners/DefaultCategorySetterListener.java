package ro.uaic.info.laborator2.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author stefa
 */
public class DefaultCategorySetterListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        ServletContext servletContext = sce.getServletContext();
        
        String contextCategory = servletContext.getInitParameter("category");
        servletContext.setAttribute("defaultCategory", contextCategory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
