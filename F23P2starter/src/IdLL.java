/**
 * This class defines the Linked List structure for Seminar IDs
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.25
 */
public class IdLL {
    private IdLL leftChild;
    private Seminar sem;

    /**
     * Basic constructor, takes only the Seminar object
     * 
     * @param newSem The new Seminar object
     */
    public IdLL(Seminar newSem) {
        leftChild = null;
        sem = newSem;
    }

    /**
     * Gets the id of the seminar object stored
     * 
     * @return the ID of the Seminar object
     */
    public int getId() {
        return sem.id();
    }

    /**
     * Changes the seminar object
     * 
     * @param newSem new Seminar object
     */
    public void setSem(Seminar newSem) {
        sem = newSem;
    }

    /**
     * gets the seminar object itself
     * 
     * @return the Seminar object
     */
    public Seminar getSem() {
        return sem;
    }

    /**
     * Sets the next LL node
     * 
     * @param left New LL node
     */
    public void setNext(IdLL left) {
        leftChild = left;
    }

    /**
     * Gets the next object
     * 
     * @return next object
     */
    public IdLL getNext() {
        return leftChild;
    }
}
