/**
 * 
 */

/**
 * This class defines a Binary Search Tree for seminar keywords
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.08
 */
public class KeywordBST {
    private Seminar sem;
    private KeywordBST leftChild;
    private KeywordBST rightChild;
    private String keyword;
    private boolean leaf;

    /**
     * Constructor takes only the seminar object, leaving the children to be set
     * later
     * 
     * @param kword
     *            keyword
     * 
     * @param newSem
     *            new Seminar object
     */
    public KeywordBST(String kword, Seminar newSem) {
        setSem(newSem);
        keyword = kword;
        leftChild = null;
        rightChild = null;
        checkLeaf();
    }


    /**
     * Changes the seminar and keyword of the node
     * 
     * @param newSem
     *            new seminar object to add
     * @param kword
     *            new keyword to be changed
     */
    public void change(Seminar newSem, String kword) {
        setSem(newSem);
        keyword = kword;
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
     * Gets the keyword of the node
     * 
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }


    /**
     * Returns the Seminar object
     * 
     * @return Seminar object stored in the node
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
    public void setLeft(KeywordBST left) {
        leftChild = left;
        checkLeaf();
    }


    /**
     * sets the right child BST object
     * 
     * @param right
     *            the right BST
     */
    public void setRight(KeywordBST right) {
        rightChild = right;
        checkLeaf();
    }


    /**
     * Gets the left child BST object
     * 
     * @return the left BST
     */
    public KeywordBST getLeft() {
        return leftChild;
    }


    /**
     * Gets the right child bST object
     * 
     * @return the right BST
     */
    public KeywordBST getRight() {
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
     * Prints Seminar
     * 
     * @return String result
     */
    public String printSem() {
        return sem.toString();
    }
}
