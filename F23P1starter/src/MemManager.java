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
<<<<<<< HEAD
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
=======

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
        int slot = -1;
        if (curr != null) {
            freespace[headIndex] = curr.getNext();
            slot = curr.getIndex();
        }
        // If not, loop through greater sizes and to find an appropriate free
        // space and break it down
        int newIndex = headIndex + 1;
        while (slot == -1 && newIndex < freespace.length) {
            if (curr != null) {
                slot = curr.getIndex();
                freespace[newIndex] = curr.getNext();
                FreeBlock temp = new FreeBlock(slot);
                // designate second "buddy" free block to be halfway through the
                // old block
                int secondSlot = slot + ((int)Math.pow(2, newIndex) / 2);
                // combine the two temp slots into a linked list
                temp.setNext(new FreeBlock(secondSlot));
                // set the size category 1 lower to the temporary linked list
                // (assumes the lower array element is null)
                freespace[newIndex - 1] = temp;
                // delete larger free block
                freespace[headIndex] = curr.getNext();
                slot = insert(sem); // recursive command
            }
            newIndex++;
        }
        if (slot == -1) {
            // Could not insert -- Out of room -- Expand byte array
        }
        //arraycopy the seminar into the memory
        if (slot != -1) {
            System.arraycopy(sem, 0, memory, slot, sem.length);
            return slot;
>>>>>>> branch 'main' of https://github.com/spaghety/cs3114
        }
        return 0;
    }
}
