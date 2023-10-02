/**
 * 
 */

/**
 * This class defines a Binary Search Tree for seminar keywords
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.24
 */
public class KeywordBST {
    private Seminar sem;
    private int count;
    private KeywordBST leftChild;
    private KeywordBST rightChild;
    private String keyword;

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
        sem = newSem;
        keyword = kword;
        leftChild = null;
        rightChild = null;
    }


    /**
     * Changes the seminar and keyword of the node
     * 
     * @param newSem
     *            new seminar object to add
     * @return true if successful, false if not
     */
    public void change(Seminar newSem, String kword) {
        sem = newSem;
        keyword = kword;
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
    }


    /**
     * sets the right child BST object
     * 
     * @param right
     *            the right BST
     */
    public void setRight(KeywordBST right) {
        rightChild = right;
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
     * Gets the size
     * 
     * @return size
     */
    public int getSize() {
        return count;
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
     * Prints Seminar
     * 
     * @return String result
     */
    public String printSem() {
        return sem.toString();
    }
}
