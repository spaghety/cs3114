
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
    public static byte TOMBSTONE = (byte)0xff;
    private byte[] bArray;
    // private FreeBlock[] freespace;
    private int size;
    // private int fbindex;

    /**
     * Constructor
     * 
     * @param memsize
     *            the total size of memory to allocate with the byte array. Will
     *            always be a power of 2.
     * @param hashsize
     *            the number of slots to start with initially (may increase with
     *            time) will always be a power of 2.
     */
    public HashTable(int memsize, int hashsize) {
        bArray = new byte[memsize];
        size = hashsize;
        // fbindex = 0;
        // Each element of this array will represent the 2^(index) memory
        // blocks. e.g. 0 keeps track of all size 1 blocks, 1 keeps track of 2,
        // etc.
        /*
         * freespace = new FreeBlock[(int)(Math.log(memsize) / Math.log(2))];
         * int initindex = (int)(Math.log(memsize / hashsize) / Math.log(2));
         * freespace[initindex] = new FreeBlock(hashsize);
         * for (int i = hashsize; i >= 0; i--) {
         * FreeBlock temp = new FreeBlock(i);
         * temp.setNext(freespace[initindex]);
         * freespace[initindex] = temp;
         * }
         */
    }


    /**
     * Doubles the capacity in case maximum capacity is reached.
     * 
     * @return true if successful, false if not.
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
     * 
     * @param id
     *            id of the object being entered in.
     * @return the hashed id to be used as an index.
     */
    private int hash(int id, int objsize) {
    	//Rounds the object size up to the next power of two.
    	objsize = Integer.highestOneBit(objsize-1) << 1;
        int index = id % size;
        int h2 = (((id / size) % (size / 2)) * 2) + 1;
        while (bArray[index] != 0x0 && bArray[index] != TOMBSTONE) {
            index += h2;
            index %= size;
        }
        // System.out.println("Length = " + size);
        // System.out.println("Index = " + index);
        return index;
    }


    /**
     * Inserts new item into the hash table.
     * 
     * @param id
     *            id of the item to be inserted.
     * @param sem
     *            Seminar object to be inserted.
     * @return true if successful, false if not.
     * @throws Exception 
     */
    public boolean insert(int id, Seminar sem) throws Exception {
    	byte[] ser = sem.serialize();
        int index = hash(id, ser.length);
        // if (bArray[M] == 0) {
        // bArray[M] = (byte)id;
        // return true;
        // }
        // return false;
        for (int i = 0; i<ser.length; i++) {
        	bArray[index+i] = ser[i];
        }
        return true;
    }


    /**
     * Removes items from the hash table by id.
     * 
     * @param id
     *            id of the item to be removed.
     * @return true if item is found and removed, false if not.
     */
    public boolean remove(int id) {
        for (int i = 0; i < size; i++) {
            if (bArray[i] == id) {
                bArray[i] = TOMBSTONE;
                return true;
            }
        }
        return false;
    }


    /**
     * Finds an item in the hash table by id.
     * 
     * @param id
     *            id of the item to be removed.
     * @return Array index if item is found, -1 if not.
     */
    public int find(int id) {
        for (int i = 0; i < size; i++) {
            if (bArray[i] == id) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Gets the underlying array.
     * 
     * @return The underlying array
     */
    public byte[] getArray() {
        return bArray;
    }


    /**
     * Prints out either the free blocks of space in the memory or the contents
     * of the hash table by id and index.
     * 
     * @param type
     *            true if printing out the hash table, false if printing out
     *            free memory blocks.
     * @return the string for the parser to print.
     */
    public String printout(boolean type) {
        StringBuilder result = new StringBuilder();
        if (type) {
            for (int i = 0; i < bArray.length; i++) {
                if (bArray[i] != 0) {
                    result.append("\n" + i);
                }
            }
        }
        else {
            /*
             * for (int i = 0; i < freespace.length; i++) {
             * FreeBlock curr = freespace[i];
             * while(curr != null) {
             * System.out.println(Math.pow(2, i)+" : "+curr.getIndex());
             * curr = curr.getNext();
             * }
             * } COMMENTED OUT UNTIL WE START IMPLEMENTING THE FREEBLOCK
             * FUNCTIONALITY
             */
        }
        return result.toString();
    }

}
