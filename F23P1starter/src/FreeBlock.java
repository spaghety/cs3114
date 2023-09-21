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
 * This class represents unallocated free space in the memory block in the form
 * of a linked list.
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.10
 */
public class FreeBlock {
    private FreeBlock next;
    private int index;

    /**
     * constructor
     * 
     * @param i
     *            Index value passed in
     */
    public FreeBlock(int i) {
        next = null;
        index = i;
    }
    
    /**
     * empty constructor (for head nodes)
     */
    public FreeBlock() {
    	next = null;
    	index = -1;
    }


    /**
     * Getter method for index value
     * 
     * @return The index value
     */
    public int getIndex() {
        return index;
    }


    /**
     * Sets the next FreeBlock reference. Returns false if param is null
     * 
     * @param n
     *            The next FreeBlock
     * @return true if successfully set, false if not (param is null)
     */
    public boolean setNext(FreeBlock n) {
        if (n == null) {
            return false;
        }
        next = n;
        return true;
    }
    
    public void clean(int size) {
    	if (next == null) {
    		return;
    	}else if (next.getNext() == null) {
    		return;
    	}else if (next.getIndex()+size == next.getNext().getIndex()) {
    		next = next.getNext().getNext();
    	}
    	next.clean(size);
    	return;
    }


    /**
     * Getter method for the next FreeBlock
     * 
     * @return The next FreeBlock
     */
    public FreeBlock getNext() {
        return next;
    }

}
