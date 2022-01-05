package ro.uaic.info.laborator9.api.Models;

/**
 *
 * @author stefa
 */
public class DocumentDto {
    public int id;
    
    public String name;
    
    public String filename;
    
    public byte[] content;
    
    public DocumentDto(int id, String name, String filename, byte[] content) {
        this.id = id;
        this.name = name;
        this.filename = filename;
        this.content = content;
    }
}
