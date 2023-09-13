// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * This class contains the main byte array and freeblock array of linked lists
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.11
 */
public class MemManager {
    private byte[] memory;
    private FreeBlock[] freespace;

    /**
     * Constructor takes one argument memsize, creates a byte array, and creates
     * a single freeblock array with a slot for all block sizes possible with
     * one active freeblock spanning the entire byte array.
     * 
     * @param memsize
     */
    public MemManager(int memsize) {
        memory = new byte[memsize];
        int freeSpaceSize = (int)Math.ceil(Math.log(memsize) / Math.log(2)) + 1;
        freespace = new FreeBlock[freeSpaceSize]; // create array of linked
                                                  // lists with the length of
                                                  // the
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
    public SemRecord insert(byte[] sem, int id) {
        int headIndex = (int)(Math.ceil(Math.log(sem.length) / Math.log(2)));
        // check if an exact match for the slot size is available
        FreeBlock curr = freespace[headIndex];
        int slot = -1;
        SemRecord handle = null;
        if (curr != null) {
            freespace[headIndex] = curr.getNext();
            slot = curr.getIndex();
            handle = new SemRecord(id, curr.getIndex(), sem.length);
            // arraycopy the seminar into the memory
            System.arraycopy(sem, 0, memory, slot, sem.length);
        }
        else {
            headIndex++;
            // If not, loop through greater sizes and to find an appropriate
            // free
            // space and break it down
            while (headIndex < freespace.length) {
                curr = freespace[headIndex];
                if (curr != null) {
                    // add two blocks to the size category below
                    int ind = curr.getIndex();
                    FreeBlock newBlock = new FreeBlock(curr.getIndex());
                    FreeBlock temp = new FreeBlock(curr.getIndex() + (int)Math
                        .pow(2, headIndex - 1));
                    temp.setNext(freespace[headIndex - 1]);
                    newBlock.setNext(temp);
                    freespace[headIndex - 1] = newBlock;
                    // delete old block
                    freespace[headIndex] = freespace[headIndex].getNext();
                    // recursive call to try inserting again
                    handle = insert(sem, id);
                    break;
                }
                else {
                    headIndex++;
                }
            }
        }
        if (handle == null) {
            doubleSize();
            return insert(sem, id);
        }
        else {
            return handle;
        }
    }


    /**
     * This method is called when the size of the base byte array needs to be
     * doubled
     */
    private void doubleSize() {
        // expands the size of the byte array
        byte[] newBytes = new byte[memory.length * 2];
        System.arraycopy(memory, 0, newBytes, 0, memory.length);
        // Adds new free space
        FreeBlock newblock = new FreeBlock(memory.length);
        freespace[freespace.length - 1] = newblock;
        memory = newBytes;
        System.out.printf("Memory pool expanded to %d bytes\n", memory.length);
    }


    /**
     * This method finds a seminar in the byte array and returns it in original
     * object form.
     * 
     * @param key
     *            the record object referencing the location of the serialized
     *            object
     * @return the deserialized object
     */
    public Seminar find(SemRecord key) {
        if (key == null) {
            System.out.println("ERROR: hash table record null");
            return null;
        }
        byte[] ser = new byte[key.getSize()];
        System.arraycopy(memory, key.getIndex(), ser, 0, key.getSize());
        try {
            return Seminar.deserialize(ser);
        }
        catch (Exception e) {
            System.out.printf(
                "\nSearch FAILED -- There is no record with ID %d", key
                    .getId());
            return null;
        }
    }


    /**
     * Removes memory from the byte array by allocating it as free space in the
     * free block.
     * 
     * @param key
     *            object referencing the location of the serialized object being
     *            removed
     * @return true if successful, false if not
     */
    public boolean remove(SemRecord key) {
        if (key == null) {
            System.out.println("ERROR: hash table record null");
            return false;
        }
        int freeBlockIndex = (int)Math.ceil(Math.log(key.getSize()) / Math.log(
            2));
        FreeBlock curr = freespace[freeBlockIndex];
        while (curr != null) {
            if (curr.getIndex() == key.getIndex()) {
                System.out.println("ERROR: Record has already been removed");
                return false;
            }
            curr = curr.getNext();
        }
        FreeBlock temp = new FreeBlock(key.getIndex());
        temp.setNext(freespace[freeBlockIndex]);
        freespace[freeBlockIndex] = temp;
        return true;
    }


    /**
     * This method prints out the free blocks' indices by size
     */
    public void printFreeBlock() {
        for (int i = 0; i < freespace.length; i++) {
            if (freespace[i] != null) {
                System.out.printf("%d: ", (int)Math.pow(2, i));
                FreeBlock curr = freespace[i];
                while (curr != null) {
                    System.out.print(curr.getIndex());
                    if (curr.getNext() != null) {
                        System.out.print(", ");
                    }
                    else {
                        System.out.print("\n");
                    }
                    curr = curr.getNext();
                }
            }
        }
    }
}