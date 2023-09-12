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
        freespace = new FreeBlock[((int)Math.ceil(Math.log(memsize) / Math.log(
            2)))]; // create array of linked lists with the length of the
                   // highest power of two. Rounded up to be safe.
        freespace[freespace.length - 1] = new FreeBlock(0);
    }


    /**
     * This function finds a free space and copies the data of the serialized
     * object into it.
     * 
     * @param sem
     *            serialized seminar object
     * @return the index of the object in the byte array
     */
    public int insert(byte[] sem) {
        int headIndex = (int)(Math.ceil(Math.log(sem.length) / Math.log(2)));
        // check if an exact match for the slot size is available
        FreeBlock curr = freespace[headIndex];
        if (curr != null) {
            freespace[headIndex] = curr.getNext();
            return curr.getIndex();
        }
        // If not, loop through greater sizes and to find an appropriate free
        // space and break it down
        int newIndex = headIndex + 1;
        int slot = -1;
        while (newIndex < freespace.length) {
            if (curr != null) {
                slot = curr.getIndex();
                freespace[newIndex] = curr.getNext();
                FreeBlock temp = new FreeBlock(slot);
                int secondSlot = slot + ((int)Math.pow(2, newIndex) / 2); //designate second "buddy" free block to be halfway through the old block
                temp.setNext(new FreeBlock(secondSlot)); //combine the two temp slots into a linked list
                freespace[newIndex - 1] = temp; //set the size category 1 lower to the temporary linked list (assumes the lower array element is null)
                freespace[headIndex] = curr.getNext(); //delete larger free block
                return insert(sem); //recursive command
            }
            else if (newIndex == freespace.length - 1) {
                // Cannot insert -- Out of room -- Expand byte array
            }
            newIndex++;
        }
        return 0;
    }
}