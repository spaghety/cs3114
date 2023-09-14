/**
 * This class sets up the hash table storing all the seminars.
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.12
 */
public class HashTable {
// public static SemRecord TOMBSTONE = new SemRecord(0, 0, 0);
    private SemRecord[] records;
// private FreeBlock[] freespace;
    private int count;

    /**
     * Constructor
     * 
     * @param hashsize
     *            the number of slots to start with initially (may increase
     *            with time) will always be a power of 2.
     */
    public HashTable(int hashsize) {
        records = new SemRecord[hashsize];
        count = 0;
    }


    /**
     * Doubles the capacity in case maximum capacity is reached.
     */
    private void doubleCap() {
        SemRecord[] tempArr = records;
        records = new SemRecord[records.length * 2];
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] != null) {
                if (!tempArr[i].isTombstone()) {
                    records[hash(tempArr[i].getId())] = tempArr[i];
                }
            }
        }
        System.out.printf("Hash table expanded to %d records\n",
            records.length);
    }


    /**
     * Searches for a free space in the hash table
     * 
     * @param id
     *            id of the object being entered in.
     * @return the hashed id to be used as an index.
     */
    private int hash(int id) {
        int index = id % records.length;
        int h2 = (((id / records.length) % (records.length / 2)) * 2) + 1;
        while (records[index] != null && !records[index].isTombstone()) {
            index += h2;
            index %= records.length;
        }
        return index;
    }


    /**
     * Inserts new item into the hash table.
     * 
     * @param ref
     *            the handle to the location of the object in the memory manager
     *            returned by the memory manager insert method
     * @return true if successful, false if not
     */
    public boolean insert(SemRecord ref) {
        records[hash(ref.getId())] = ref;
        if (count * 2 >= records.length) {
            doubleCap();
        }
        count++;
        return true;
    }


    /**
     * Searches for an existing record in the hash table
     * 
     * @param id
     *            id to hash
     * @return index of existing record with a matching id or -1 if it could not
     *         be found
     */
    private int hashSearch(int id) {
        if (count == 0) {
            return -1;
        }
        int index = id % records.length;
        int h2 = (((id / records.length) % (records.length / 2)) * 2) + 1;
        int iter = 0;
        while (iter < records.length) {
        	if (records[index] != null && !records[index].isTombstone()) {
	            if (records[index].getId() == id) {
	                return index;
	            }
        	}
            index += h2;
            index %= records.length;
            iter++;
        }
        return -1;
        
        /*
        while (records[index] != null && !records[index].isTombstone()) {
            if (records[index].getId() == id) {
                return index;
            }
            index += h2;
            index %= records.length;
        }
        return -1;*/
    }


    /**
     * Removes items from the hash table by id.
     * 
     * @param id
     *            id of the item to be removed.
     * @return a reference to the data in the memory manager; returns null if
     *         not found or is a tombstone
     */
    public SemRecord remove(int id) {
        int index = hashSearch(id);
        if (index == -1) {
        	System.out.printf("Delete FAILED -- There is no record with ID %d\n", id);
            return null;
        }
        SemRecord temp = records[index];
        records[index].makeTombstone();
        count--;
        return temp;
    }


    /**
     * Finds an item in the hash table by id.
     * 
     * @param id
     *            id of the item to be found.
     * @return memory manager handle or null if it could not be found
     */
    public SemRecord find(int id) {
        int index = hashSearch(id);
        if (index > -1) {
            return records[index];
        }
        return null;
    }


    /**
     * Gets the underlying array. Used in test cases.
     * 
     * @return The underlying array
     */
    public SemRecord[] getArray() {
        return records;
    }


    /**
     * Gets the current count. Used in test cases
     * 
     * @return Current count
     */
    public int getCount() {
        return count;
    }


    /**
     * Prints out either the free blocks of space in the memory or the contents
     * of the hash table by id and index.
     * 
     * @return true if successful, false if not
     */
    public boolean printout() {
        for (int i = 0; i < records.length; i++) {
            if (records[i] != null) {
                if (records[i].isTombstone()) {
                    System.out.printf("%d: TOMBSTONE\n", i);
                }
                else {
                    System.out.printf("%d: %d\n", i, records[i].getId());
                }
            }
        }
        System.out.printf("total records: %d\n", count);
        return true;
    }

}
