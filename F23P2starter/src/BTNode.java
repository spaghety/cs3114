/**
 * The node class for Bintree
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.05
 */
public class BTNode {
    private int spanRadius;
    private int discX;
    private int discY;
    private int storedX;
    private int storedY;
    private boolean isX;
    private BTNode leftChild;
    private BTNode rightChild;
    private boolean isLeaf;
    private IdBST semList; // Used as a linked list
    private int count;

    /**
     * Initializer changes node from a flywheel to a functional leaf node
     * 
     * @param rad
     *            half the size of the span of the node
     * @param dscrX
     *            x coordinate discriminator for this node
     * @param dscrY
     *            y coordinate discriminator for this node
     * @param xAxis
     *            true if discriminator is on x-axis, false if not
     */
    public void initialize(int rad, int dscrX, int dscrY, boolean xAxis) {
        spanRadius = rad;
        discX = dscrX;
        discY = dscrY;
        isX = xAxis;
        semList = null;
        leftChild = null;
        rightChild = null;
        isLeaf = true;
        storedX = -1;
        storedY = -1;
        count = 0;
    }


    /**
     * Constructor initializes node with values
     * 
     * @param rad
     *            half the size of the span of the node
     * @param dscrX
     *            x coordinate discriminator for this node
     * @param dscrY
     *            y coordinate discriminator for this node
     * @param xAxis
     *            true if discriminator is on x-axis, false if not
     */
    public BTNode(int rad, int dscrX, int dscrY, boolean xAxis) {
        initialize(rad, dscrX, dscrY, xAxis);
    }


    /**
     * Constructor initializes the node object as a flywheel
     */
    public BTNode() {
        spanRadius = -1;
        discX = -1;
        discY = -1;
        isX = false;
        semList = null;
        leftChild = null;
        rightChild = null;
        isLeaf = true;
        storedX = -1;
        storedY = -1;
        count = 0;
    }


    /**
     * Sets the seminar object
     * 
     * @param newSem
     *            new Seminar object
     */
    public void add(Seminar newSem) {
        storedX = newSem.x();
        storedY = newSem.y();
        count++;
        if (semList == null)
            semList = new IdBST(newSem);
        else {
            if (semList.getId() >= newSem.id()) {
                IdBST temp = semList;
                semList = new IdBST(newSem);
                semList.setLeft(temp);
                return;
            }
            IdBST curr = semList;
            while (curr.getLeft() != null) {
                if (curr.getLeft().getId() >= newSem.id()) {
                    IdBST temp = curr.getLeft();
                    curr.setLeft(new IdBST(newSem));
                    curr.getLeft().setLeft(temp);
                    return;
                }
                curr = curr.getLeft();
            }
            curr.setLeft(new IdBST(newSem));
        }
    }


    /**
     * Gets the seminar object
     * 
     * @return the seminar
     */
    public boolean isEmpty() {
        return (semList == null);
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
     * Gets the X-axis discriminator value
     * 
     * @return the value on the x axis to divide subsequent nodes by
     */
    public int dscrX() {
        return discX;
    }


    /**
     * Gets the Y-axis discriminator
     * 
     * @return y discriminator
     */
    public int dscrY() {
        return discY;
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


    /**
     * Gets the list of seminars stored in this node
     * 
     * @return seminar list
     */
    public IdBST getList() {
        return semList;
    }


    /**
     * Gets the number of seminar objects stored in this node
     * 
     * @return count of the list
     */
    public int getCount() {
        return count;
    }


    /**
     * Gets the x value of the seminars already stored in this node
     * 
     * @return x coordinate
     */
    public int getX() {
        return storedX;
    }


    /**
     * Gets the y value of the seminars already stored in this node
     * 
     * @return y coordinate
     */
    public int getY() {
        return storedY;
    }
}
