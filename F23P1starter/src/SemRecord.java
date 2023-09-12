/**
 * This class stores the handle and metadata of an object stored in the memory
 * manager.
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 */

public class SemRecord {
    private int id;
    private int semIndex;
    private int semSize;
    private boolean stone;

    /**
     * Constructor
     * 
     * @param id
     *            the id of the seminar used in the hash algorithm
     * @param index
     *            the index of the serialized seminar object in the memory
     *            manager byte array
     * @param size
     *            the length of the array being inserted
     */
    public SemRecord(int id, int index, int size) {
        semIndex = index;
        semSize = size;
        stone = false;
    }


    /**
     * Gets the index of the serialized seminar object in the memory
     * manager byte array
     * 
     * @return The index as described
     */
    public int getIndex() {
        return semIndex;
    }


    /**
     * Gets the length of the array being inserted
     * 
     * @return The length
     */
    public int getSize() {
        return semSize;
    }


    /**
     * Gets the id of the seminar used in the hash algorithm
     * 
     * @return The id as described
     */
    public int getId() {
        return id;
    }


    /**
     * Checks if a record is a tombstone
     * 
     * @return true or false, determined by the boolean var stone
     */
    public boolean isTombstone() {
        return stone;
    }


    /**
     * Makes a record a tombstone when it is removed from the hash table
     */
    public void makeTombstone() {
        stone = true;
    }


    @Override
    public boolean equals(Object other) {
        if (!other.getClass().equals(this.getClass())) {
            return false;
        }
        SemRecord that = (SemRecord) other;
        if (stone) {
            return false;
        }
        if (that.isTombstone()) {
            return false;
        }
        return this.getIndex() == that.getIndex();
    }
}
