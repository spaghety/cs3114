/**
 * The node class for Bintree internal nodes
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.08
 */

public class BTInternalNode implements BinTreeNode {
    private BinTreeNode leftChild;
    private BinTreeNode rightChild;

    /**
     * Empty constructor
     */
    public BTInternalNode() {
        leftChild = null;
        rightChild = null;
    }

    /**
     * Gets the left child
     * 
     * @return left child node
     */
    public BinTreeNode left() {
        return leftChild;
    }

    /**
     * Gets the right child
     * 
     * @return right child node
     */
    public BinTreeNode right() {
        return rightChild;
    }

    /**
     * Sets the left node
     * 
     * @param newNode new node object
     */
    public void setLeft(BinTreeNode newNode) {
        leftChild = newNode;
    }

    /**
     * Sets the right child node
     * 
     * @param newNode new node object
     */
    public void setRight(BinTreeNode newNode) {
        rightChild = newNode;
    }

}
