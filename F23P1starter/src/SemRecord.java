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
 * This class stores the handle and metadata of an object stored in the memory
 * manager.
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.11
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
        this.id = id;
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
        if (other == null) {
            return false;
        }
        if (!other.getClass().equals(this.getClass())) {
            return false;
        }
        if (other == this) {
            return true;
        }
        SemRecord that = (SemRecord)other;
        return this.getId() == that.getId();
    }
}
