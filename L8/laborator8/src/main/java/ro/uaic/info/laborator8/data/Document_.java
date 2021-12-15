package ro.uaic.info.laborator8.data;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ro.uaic.info.laborator8.domain.Document;
import ro.uaic.info.laborator8.domain.User;

/**
 *
 * @author stefa
 */
@StaticMetamodel(Document.class)
public class Document_ {
    public static volatile SingularAttribute<Document, Integer> id;
    public static volatile SingularAttribute<Document, String> name;
    public static volatile SingularAttribute<Document, String> filename;
    public static volatile SingularAttribute<Document, byte[]> content;
    public static volatile SingularAttribute<Document, User> author;
}
