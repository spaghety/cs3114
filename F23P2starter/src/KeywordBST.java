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
    private Seminar[] sems;
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
        sems = new Seminar[4];
        count = 1;
        sems[0] = newSem;
        keyword = kword;
        leftChild = null;
        rightChild = null;
    }


    /**
     * Helper method doubles the size of the seminar array
     */
    private void doubleSize() {
        Seminar[] newArr = new Seminar[sems.length * 2];
        for (int i = 0; i < sems.length; i++) {
            newArr[i] = sems[i];
        }
        sems = newArr;
    }


    /**
     * Adds a new seminar to this BST object
     * 
     * @param newSem
     *            new seminar object to add
     * @return true if successful, false if not
     */
    public boolean add(Seminar newSem) {
        boolean found = false;
        String[] kw = newSem.keywords();
        for (int i = 0; i < kw.length; i++) {
            if (kw[i].equals(keyword)) {
                found = true;
            }
        }
        if (!found) {
            return false;
        }
        else {
            sems[count] = newSem;
            count++;
            if (count == sems.length)
                doubleSize();
            return true;
        }
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
    public String printSems() {
        String result = "";
        for (int i = 0; i < count; i++) {
            result += sems[i].toString() + "\n";
        }
        return result;
    }
}
