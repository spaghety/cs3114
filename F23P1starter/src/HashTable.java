
/**
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.05
 */

// import java.util.Arrays;

/**
 * This class sets up the hash table storing all the seminars.
 */
public class HashTable {
    public static SemRecord TOMBSTONE = new SemRecord(0, 0, 0);
    private SemRecord[] records;
    private FreeBlock[] freespace;
    private int size;

    /**
     * Constructor
     * 
     * @param memsize
     *            the total size of memory to allocate with the byte array.
     *            Will always be a power of 2.
     * @param hashsize
     *            the number of slots to start with initially (may increase
     *            with time) will always be a power of 2.
     */
    public HashTable(int hashsize) {
        records = new SemRecord[hashsize];
        size = hashsize;
    }


    /**
     * Doubles the capacity in case maximum capacity is reached.
     * 
     * @return true if successful, false if not.
     */
    private boolean doubleCap() {
        SemRecord[] tempArr = new SemRecord[records.length * 2];
        for (int i = 0; i < records.length; i++) {
            if (records[i] != null) {
                tempArr[records[i].getId() % tempArr.length] = records[i];
            }
        }
        return true;
    }


    /**
     * Returns the hash destination.
     * 
     * @param id
     *            id of the object being entered in.
     * @return the hashed id to be used as an index.
     */
    private int hash(int id) {
        int index = id % size;
        int h2 = (((id / size) % (size / 2)) * 2) + 1;
        while (records[index] != null && !records[index].isTombstone()) {
            index += h2;
            index %= size;
        }
        return index;
    }


    /**
     * Inserts new item into the hash table.
     * 
     * @param id
     *            id of the item to be inserted.
     * @param size
     *            Seminar object to be inserted.
     * @param index
     * @return true if successful, false if not.
     */
    public boolean insert(int id, int size, int index) {
        if (this.find(index) > -1) {
            return false;
        }
        SemRecord ref = new SemRecord(id, index, size);
        records[hash(id)] = ref;
        return true;
    }


    /**
     * Removes items from the hash table by id.
     * 
     * @param id
     *            id of the item to be removed.
     * @return a reference to the data in the memory manager
     */
    public int remove(int id) {
        for (int i = 0; i < size; i++) {
            if (records[i].getId() == id) {
                records[i].makeTombstone();
                return records[i].getIndex();
            }
        }
        return -1;
    }


    /**
     * Finds an item in the hash table by index.
     * 
     * @param index
     *            index of the item to be found.
     * @return Array index if item is found, -1 if not.
     */
    public int find(int index) {
        for (int i = 0; i < size; i++) {
            if (records[i] instanceof SemRecord) {
                if (records[i].getIndex() == index) {
                    return i;
                }
            }
        }
        return -1;
    }


    /**
     * Gets the underlying array.
     * 
     * @return The underlying array
     */
    public SemRecord[] getArray() {
        return records;
    }


    /**
     * Prints out either the free blocks of space in the memory or the contents
     * of
     * the hash table by id and index.
     * 
     * @param type
     *            true if printing out the hash table, false if printing out
     *            free
     *            memory blocks.
     * @return the string for the parser to print.
     */
    public String printout(boolean type) {
        StringBuilder result = new StringBuilder();
        if (type) {
            for (int i = 0; i < records.length; i++) {
                if (records[i] != null && !records[i].isTombstone()) {
                    result.append("\n" + i);
                }
            }
        }
        return result.toString();
    }

}
