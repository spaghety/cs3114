/**
 * This class stores the handle and metadata of an object stored in the memory manager.
 */

/**
 * @author Phillip Jordan (alexj14)
 *
 */
public class SemRecord {
    private int id;
    private int semIndex;
    private int semSize;
    public SemRecord(int id, int index, int size) {
        semIndex = index;
        semSize = size;
    }
    public int getIndex() {
        return semIndex;
    }
    public int getSize() {
        return semSize;
    }
    public int getId() {
        return id;
    }
}
