package ro.uaic.info.laborator2.dictionary;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author stefa
 */
@Entity(name = "Word")
@Table(name = "WORDS")
public class Word implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "VALUE", unique = true)
    private String value;
    
    @Column(name = "DEFINITION")
    private String definition;
    
    @Column(name = "CATEGORY")
    private String category;

    public Word() {
    }
    
    public Word(String value, String definition, String category) throws IllegalArgumentException {
        
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value should not be null or empty.");
        }
        
        if (definition == null || definition.isEmpty()) {
            throw new IllegalArgumentException("Definition should not be null or empty.");
        }
        
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category should not be null or empty");
        }
        
        this.value = value;
        this.definition = definition;
        this.category = category;
    }
    
    public int getId() {
        return id;
    }
    
    public String getValue() {
        return value;
    }
    
    public String getDefinition() {
        return definition;
    }

    public String getCategory() {
        return category;
    }
}
