///**
// * This class defines a generic Binary Search Tree
// * 
// * @author Phillip Jordan (alexj14)
// * @author Ta-Jung (David) Lin (davidsmile)
// * @version 2023.10.08
// */
//public class BSTree {
//    private BSTree leftChild;
//    private BSTree rightChild;
//    private Seminar sem;
//    private boolean leaf;
//    private Comparable element;
//
//    /**
//     * Basic constructor, takes only the Seminar object since left and right
//     * children will be set later
//     * 
//     * @param newSem
//     *            The new Seminar object
//     * @param ele
//     *            The element to be inserted
//     */
//    public BSTree(Seminar newSem, Comparable ele) {
//        leftChild = null;
//        rightChild = null;
//        sem = newSem;
//        element = ele;
//        checkLeaf();
//    }
//
//
//    /**
//     * Gets the id of the seminar object stored
//     * 
//     * @return the ID of the Seminar object
//     */
//    public Comparable value() {
//        return element;
//    }
//
//
//    /**
//     * Changes the seminar object
//     * 
//     * @param newSem
//     *            new Seminar object
//     */
//    public void setSem(Seminar newSem) {
//        sem = newSem;
//    }
//
//
//    /**
//     * gets the seminar object itself
//     * 
//     * @return the Seminar object
//     */
//    public Seminar getSem() {
//        return sem;
//    }
//
//
//    /**
//     * Sets the left child of the BST object
//     * 
//     * @param left
//     *            New leftChild BST object
//     */
//    public void setLeft(BSTree left) {
//        leftChild = left;
//        checkLeaf();
//    }
//
//
//    /**
//     * Sets the right child of the BST object
//     * 
//     * @param right
//     *            New rightChild BST object
//     */
//    public void setRight(BSTree right) {
//        rightChild = right;
//        checkLeaf();
//    }
//
//
//    /**
//     * Gets the left child BST object
//     * 
//     * @return left child BST object
//     */
//    public BSTree getLeft() {
//        return leftChild;
//    }
//
//
//    /**
//     * Gets the right child BST object
//     * 
//     * @return right child BST object
//     */
//    public BSTree getRight() {
//        return rightChild;
//    }
//
//
//    /**
//     * Gets if this node is a leaf or not
//     * 
//     * @return true if this is a leaf, false otherwise
//     */
//    public boolean isLeaf() {
//        return leaf;
//    }
//
//
//    /**
//     * Checks if this node is a leaf
//     */
//    private void checkLeaf() {
//        leaf = (leftChild == null && rightChild == null);
//    }
//}