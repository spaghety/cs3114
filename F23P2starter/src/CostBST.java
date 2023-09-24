/**
 * 
 */

/**
 * This class defines a Binary Search Tree for seminar costs
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.24
 */
public class CostBST {
    private Seminar sem;
    private CostBST leftChild;
    private CostBST rightChild;

    /**
     * Constructor takes only the seminar object, leaving the children to be set
     * later
     * 
     * @param newSem
     *            new Seminar object
     */
    public CostBST(Seminar newSem) {
        sem = newSem;
        leftChild = null;
        rightChild = null;
    }


    /**
     * Gets the cost of the stored seminar
     * 
     * @return the cost of the seminar
     */
    public int getCost() {
        return sem.cost();
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
    public void setLeft(CostBST left) {
        leftChild = left;
    }


    /**
     * sets the right child BST object
     * 
     * @param right
     *            the right BST
     */
    public void setRight(CostBST right) {
        rightChild = right;
    }


    /**
     * Gets the left child BST object
     * 
     * @return the left BST
     */
    public CostBST getLeft() {
        return leftChild;
    }


    /**
     * Gets the right child bST object
     * 
     * @return the right BST
     */
    public CostBST getRight() {
        return rightChild;
    }
}
