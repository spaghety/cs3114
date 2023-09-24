/**
 * 
 */

/**
 * This class defines the Binary Search Tree structure for Seminar Ids
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.24
 */
public class IdBST {
    private IdBST leftChild;
    private IdBST rightChild;
    private Seminar sem;

    /**
     * Basic constructor, takes only the Seminar object since left and right
     * children will be set later
     * 
     * @param newSem
     *            The new Seminar object
     */
    public IdBST(Seminar newSem) {
        leftChild = null;
        rightChild = null;
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
     * gets the seminar object itself
     * 
     * @return the Seminar object
     */
    public Seminar getSem() {
        return sem;
    }


    /**
     * Sets the left child of the BST object
     * 
     * @param left
     *            New leftChild BST object
     */
    public void setLeft(IdBST left) {
        leftChild = left;
    }


    /**
     * Sets the right child of the BST object
     * 
     * @param right
     *            New rightChild BST object
     */
    public void setRight(IdBST right) {
        rightChild = right;
    }


    /**
     * Gets the left child BST object
     * 
     * @return left child BST object
     */
    public IdBST getLeft() {
        return leftChild;
    }


    /**
     * Gets the right child BST object
     * 
     * @return right child BST object
     */
    public IdBST getRight() {
        return rightChild;
    }
}
