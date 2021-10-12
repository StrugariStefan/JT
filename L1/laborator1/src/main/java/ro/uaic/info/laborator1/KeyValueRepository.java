package ro.uaic.info.laborator1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author stefa
 */
public class KeyValueRepository {
    private final String repositoryFilePath = "repository.txt";
    private Path pathToRepository;
    
    public KeyValueRepository() throws IOException {
        initRepository();
    }
    
    public void updateRepository(KeyValueInputModel keyValueInputModel) throws IOException  {
        String repeatedKeyWithTimestampString = buildRepeatedKeyWithTimestampString(keyValueInputModel.getKey(), keyValueInputModel.getValue());
        
        if (keyValueInputModel.isSync()) {
            FileChannel file = FileChannel.open(pathToRepository, StandardOpenOption.APPEND);
            file.write(ByteBuffer.wrap(repeatedKeyWithTimestampString.getBytes()), file.size());
        } else {
            AsynchronousFileChannel asyncFile = AsynchronousFileChannel.open(pathToRepository,  StandardOpenOption.WRITE);
            asyncFile.write(ByteBuffer.wrap(repeatedKeyWithTimestampString.getBytes()), asyncFile.size());
        }
    }
    
    public String getRepositoryContent() throws IOException  {
        StringBuilder contentBuilder = new StringBuilder();
        
        BufferedReader br = new BufferedReader(new FileReader(repositoryFilePath));

        String line;
        while ((line = br.readLine()) != null) {
            contentBuilder.append(line).append("\n");
        }
        
        return contentBuilder.toString();
    }
    
    private void initRepository() throws IOException {
        pathToRepository = Paths.get(repositoryFilePath);
        
        if(!Files.exists(pathToRepository)){
            Files.createFile(pathToRepository);
        }
    }
    
    private String buildRepeatedKeyWithTimestampString(String key, int value) {
        StringBuilder repeatedKeyWithTimestampStringBuilder = new StringBuilder();

        for (int i = 0; i < value; ++i) {
            repeatedKeyWithTimestampStringBuilder.append(key).append(" ");
        }
        
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        repeatedKeyWithTimestampStringBuilder.append(timestamp).append("\n");

        return repeatedKeyWithTimestampStringBuilder.toString();
    }   
}
