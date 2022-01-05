package ro.uaic.info.laborator9.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author stefa
 */
@Entity(name = "Document")
@Table(name = "DOCUMENTS")
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "FILENAME")
    private String filename;
    
    @Lob
    @Column(name = "CONTENT")
    private byte[] content;
    
    public Document() {
    }
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    private User author;
    
    public Document(
        @NotNull @NotEmpty String name,
        @NotNull @NotEmpty String filename,
        @NotNull byte[] content,
        @NotNull User author) {
        
        this.name = name;
        this.filename = filename;
        this.content = content;
        this.author = author;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getFilename() {
        return filename;
    }
    
    public byte[] getContent() {
        return content;
    }
    
    public User getAuthor() {
        return author;
    }
    
    public void updateFilenameAndContent(
        @NotNull @NotEmpty String filename,
        @NotNull byte[] content,
        @NotNull User author) {
        
        if (!author.getUserid().equals(this.author.getUserid())) {
            throw new IllegalArgumentException("Author with id " + author.getUserid() + " is not allowed to update document with id " + this.id + ".");
        }
        
        this.filename = filename;
        this.content = content;
    }
    
    public boolean hasSpecificAuthor(
        @NotNull User author) {
        return author.getUserid().equals(this.author.getUserid());
    }
}
