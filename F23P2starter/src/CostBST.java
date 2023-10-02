/**
 * This class defines a Binary Search Tree for seminar costs
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.25
 */
public class CostBST {
    private Seminar sem;
    private CostBST leftChild;
    private CostBST rightChild;
    private boolean leaf;

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
        checkLeaf();
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
     * Changes the node's Seminar object
     * 
     * @param newSem
     *            The new object to be set
     */
    public void setSem(Seminar newSem) {
        sem = newSem;
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
        checkLeaf();
    }


    /**
     * sets the right child BST object
     * 
     * @param right
     *            the right BST
     */
    public void setRight(CostBST right) {
        rightChild = right;
        checkLeaf();
    }


    /**
     * Checks if this node is a leaf
     */
    private void checkLeaf() {
        if (leftChild == null && rightChild == null) {
            leaf = true;
        }
        else {
            leaf = false;
        }
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


    /**
     * Gets if this node is a leaf or not
     * 
     * @return true if this is a leaf, false otherwise
     */
    public boolean isLeaf() {
        return leaf;
    }
}
