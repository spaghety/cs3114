/**
 * @author Phillip Jordan (alexj14)
 *
 */
public class BTNode {
    private int spanRadius;
    private int disc;
    private boolean isX;
    private Seminar sem;
    private BTNode leftChild;
    private BTNode rightChild;
    private boolean isLeaf;

    /**
     * Constructor creates new
     * 
     * @param rad
     *            half the size of the span of the node
     * @param dscr
     *            coordinate discriminator for this node
     * @param X
     *            true if discriminator is on x-axis, false if not
     */
    public BTNode(int rad, int dscr, boolean X) {
        spanRadius = rad;
        isX = X;
        sem = null;
        leftChild = null;
        rightChild = null;
        isLeaf = true;
    }


    /**
     * Sets the seminar object
     * 
     * @param newSem
     *            new Seminar object
     */
    public void setSem(Seminar newSem) {
        sem = newSem;
    }


    /**
     * Gets the seminar object
     * 
     * @return the seminar
     */
    public Seminar sem() {
        return sem;
    }


    /**
     * Sets the left child
     * 
     * @param newNode
     *            new BTNode object
     */
    public void setLeft(BTNode newNode) {
        leftChild = newNode;
    }


    /**
     * Gets the left child
     * 
     * @return the left child node
     */
    public BTNode left() {
        return leftChild;
    }


    /**
     * Sets the right child node
     * 
     * @param newNode
     *            new BTNode object
     */
    public void setRight(BTNode newNode) {
        rightChild = newNode;
    }


    /**
     * Gets the right child
     * 
     * @return the right child node
     */
    public BTNode right() {
        return rightChild;
    }


    /**
     * Gets the radius of the node's span
     * 
     * @return half the span of the node
     */
    public int rad() {
        return spanRadius;
    }


    /**
     * Gets the discriminator value
     * 
     * @return the value on the x or y axis to divide subsequent nodes by
     */
    public int dscr() {
        return disc;
    }


    /**
     * Gets the axis of the node
     * 
     * @return true if x-axis, false if y-axis
     */
    public boolean x() {
        return isX;
    }


    /**
     * Toggles whether the node is a leaf or not
     */
    public void toggleLeaf() {
        isLeaf = !isLeaf;
    }


    /**
     * gets whether the node is a leaf or not
     * 
     * @return true if it is a leaf node, false if not
     */
    public boolean leaf() {
        return isLeaf;
    }
}