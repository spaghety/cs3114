/**
 * 
 */

/**
 * This class defines a Binary Search Tree for seminar dates
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.24
 */
public class DateBST {
    private Seminar sem;
    private DateBST leftChild;
    private DateBST rightChild;

    /**
     * Constructor takes only the seminar object, leaving the children to be set
     * later
     * 
     * @param newSem
     *            new Seminar object
     */
    public DateBST(Seminar newSem) {
        sem = newSem;
        leftChild = null;
        rightChild = null;
    }


    /**
     * Gets the date of the stored seminar
     * 
     * @return the date of the seminar
     */
    public String getDate() {
        return sem.date();
    }


    /**
     * Gets the stored seminar object
     * 
     * @return the stored seminar object
     */
    public Seminar getSem() {
        return sem;
    }


    /**
     * sets the left child BST object
     * 
     * @param left
     *            the left BST
     */
    public void setLeft(DateBST left) {
        leftChild = left;
    }


    /**
     * sets the right child BST object
     * 
     * @param right
     *            the right BST
     */
    public void setRight(DateBST right) {
        rightChild = right;
    }


    /**
     * Gets the left child BST object
     * 
     * @return the left BST
     */
    public DateBST getLeft() {
        return leftChild;
    }


    /**
     * Gets the right child bST object
     * 
     * @return the right BST
     */
    public DateBST getRight() {
        return rightChild;
    }
}
