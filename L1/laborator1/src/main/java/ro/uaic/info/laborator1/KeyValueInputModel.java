package ro.uaic.info.laborator1;

/**
 *
 * @author stefa
 */
public class KeyValueInputModel {
    private final String key;
    private final int value;
    private final boolean mock;
    private final boolean sync;
    
    public KeyValueInputModel(
            String key,
            int value,
            boolean mock,
            boolean sync) {
        this.key = key;
        this.value = value;
        this.mock = mock;
        this.sync = sync;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public int getValue() {
        return value;
    }

    public boolean isMock() {
        return mock;
    }

    public boolean isSync() {
        return sync;
    }
}
