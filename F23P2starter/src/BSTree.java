/**
 * This class defines a generic Binary Search Tree
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.09
 */
public class BSTree {
    private BSTree leftChild;
    private BSTree rightChild;
    private Seminar sem;
    private boolean leaf;
    private int number;
    private String string;

    /**
     * Basic constructor, takes only the Seminar object since left and right
     * children will be set later
     * 
     * @param newSem
     *            The new Seminar object
     * @param n
     *            The element to be inserted, type int
     * @param s
     *            The element to be inserted, type String
     *            If null, it indicates n is the actual element
     */
    public BSTree(Seminar newSem, int n, String s) {
        leftChild = null;
        rightChild = null;
        setSem(newSem);
        number = n;
        string = s;
        checkLeaf();
    }


    /**
     * Gets the number value
     * 
     * @return the number value
     */
    public int value() {
        return number;
    }


    /**
     * Gets the string value
     * 
     * @return the string value
     */
    public String getString() {
        return string;
    }


    /**
     * Changes the seminar object
     * 
     * @param newSem
     *            new Seminar object
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
     * Sets the left child of the BST object
     * 
     * @param left
     *            New leftChild BST object
     */
    public void setLeft(BSTree left) {
        leftChild = left;
        checkLeaf();
    }


    /**
     * Sets the right child of the BST object
     * 
     * @param right
     *            New rightChild BST object
     */
    public void setRight(BSTree right) {
        rightChild = right;
        checkLeaf();
    }


    /**
     * Gets the left child BST object
     * 
     * @return left child BST object
     */
    public BSTree getLeft() {
        return leftChild;
    }


    /**
     * Gets the right child BST object
     * 
     * @return right child BST object
     */
    public BSTree getRight() {
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


    /**
     * Checks if this node is a leaf
     */
    private void checkLeaf() {
        leaf = (leftChild == null && rightChild == null);
    }
}
