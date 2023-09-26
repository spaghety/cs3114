/**
 * This class defines the Binary Search Tree structure for Seminar IDs
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.25
 */
public class IdBST {
    private IdBST leftChild;
    private IdBST rightChild;
    private Seminar sem;
    private boolean leaf;

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
        checkLeaf();
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
        checkLeaf();
    }


    /**
     * Sets the right child of the BST object
     * 
     * @param right
     *            New rightChild BST object
     */
    public void setRight(IdBST right) {
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


    /**
     * Gets if this node is a leaf or not
     * 
     * @return true if this is a leaf, false otherwise
     */
    public boolean isLeaf() {
        return leaf;
    }


    /**
     * Inserts a new Seminar object into this BST
     * 
     * @param newSem
     *            The new Seminar object to be inserted
     */
    public void insert(Seminar newSem) {
        if (newSem.id() > getId()) {
            if (rightChild == null) {
                setRight(new IdBST(newSem));
                return;
            }
            rightChild.insert(newSem);
        }
        else {
            if (leftChild == null) {
                setLeft(new IdBST(newSem));
                return;
            }
            leftChild.insert(newSem);
        }
    }
}
