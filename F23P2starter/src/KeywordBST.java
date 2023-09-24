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
    private KeywordBST leftChild;
    private KeywordBST rightChild;

    /**
     * Constructor takes only the seminar object, leaving the children to be set
     * later
     * 
     * @param newSem
     *            new Seminar object
     */
    public KeywordBST(Seminar newSem) {
        sem = newSem;
        leftChild = null;
        rightChild = null;
    }


    /**
     * Gets the keywords of the stored seminar
     * 
     * @return the keywords of the seminar
     */
    public String[] getKeyword() {
        return sem.keywords();
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
}
