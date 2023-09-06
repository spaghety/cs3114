/**
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.05
 */

/**
 * This class sets up the hash table storing all the seminars.
 */
public class HashTable {
    private byte[] bArray;
    private int size;
    private int[] freeBlock;
    private int fbindex;

    /**
     * Constructor
     * 
     * @param memsize
     * @param hashsize
     */
    public HashTable(int memsize, int hashsize) {
        bArray = new byte[memsize];
        freeBlock = new int[memsize];
        size = memsize;
        fbindex = 0;
    }


    /**
     * This function doubles the size of the byte array in case it reaches max
     * capacity.
     * 
     * @return true if success
     */
    private boolean doubleCap() {
        byte[] tempArr = new byte[bArray.length * 2];
        for (int i = 0; i < bArray.length; i++) {
            if (bArray[i] != 0) {
                tempArr[bArray[i] % tempArr.length] = bArray[i];
            }
        }
        return true;
    }


    /**
     * @return hash slot
     */
    private int hash(int id) {
        return id % bArray.length;
    }


    /**
     * This function inserts new entries into the hash table.
     * 
     * @param id
     *            ID of the item being inserted
     * @return true if inserted, false if not
     */
    public boolean insert(int id) {
        int M = hash(id);
        if (bArray[M] == 0) {
            bArray[M] = (byte)id;
            return true;
        }
        return false;
    }


    /**
     * This function removes entries from the hash table and will free space
     * once fully implemented.
     * 
     * Shouldn't this give a tombstone there??
     * 
     * @param id
     *            ID of the item being removed.
     * @return true if removed, false if not
     */
    public boolean remove(int id) {
        int M = hash(id);
        if (bArray[M] != 0) {
            freeBlock[fbindex] = id;
            fbindex++;
            return true;
        }
        return false;

    }
}
