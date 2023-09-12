/**
 * This class contains the main byte array and freeblock array of linked lists
 */

/**
 * @author Phillip Jordan (alexj14)
 *
 */
public class MemManager {
    private byte[] memory;
    private FreeBlock[] freespace;
    public MemManager(int memsize) {
        memory = new byte[memsize];
        freespace = new FreeBlock[((int) Math.ceil(Math.log(memsize)/Math.log(2)))]; //create array of linked lists with the length of the highest power of two. Rounded up to be safe.
        freespace[freespace.length-1] = new FreeBlock(0);
    }
    /**
     * This function finds a free space and copies the data of the serialized object into it.
     * @param sem serialized seminar object
     * @return the index of the object in the byte array
     */
    public int insert(byte[] sem) {
        int headIndex = (int) (Math.ceil(Math.log(sem.length)/Math.log(2)));
        int slot = -1;
        // check if an exact match for the slot size is available
        FreeBlock curr = freespace[headIndex];
        if (curr != null) {
            slot = curr.getIndex();
            freespace[headIndex] = curr.getNext();
        }
        //If not, loop through greater sizes and to find an appropriate free space
        while (slot == -1) {
            if (curr != null) {
                slot = curr.getIndex();
                freespace[headIndex] = curr.getNext();
            }
        }
        return 0;
    }
}
