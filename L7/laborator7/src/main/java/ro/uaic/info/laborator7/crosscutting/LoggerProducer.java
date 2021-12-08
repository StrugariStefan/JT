package ro.uaic.info.laborator7.crosscutting;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 *
 * @author stefa
 */
@ApplicationScoped
public class LoggerProducer {
    
    private static final Logger logger = Logger.getLogger("Log");
    
    private LoggerProducer() throws IOException {
        FileHandler fh = new FileHandler("LogFile.log");  
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);
    }
    
    @Produces
    @LoggerConfiguration
    Logger getLogger() throws IOException {
        return logger;
    }
}
