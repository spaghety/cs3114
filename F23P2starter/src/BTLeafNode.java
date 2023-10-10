/**
 * The node class for Bintree leaf nodes
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.05
 */
public class BTLeafNode implements BinTreeNode {
    private int storedX;
    private int storedY;
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
        semList = null;
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
    public BTLeafNode(int rad, int dscrX, int dscrY, boolean xAxis) {
        initialize(rad, dscrX, dscrY, xAxis);
    }


    /**
     * Constructor initializes the node object as a flywheel
     */
    public BTLeafNode() {
        semList = null;
        storedX = -1;
        storedY = -1;
        count = 0;
    }


    /**
     * Sets a node as internal node
     */
    public void setInternal() {
        storedX = -1;
        storedY = -1;
        semList = null;
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
     * Changes the linked list of seminars
     * 
     * @param newList
     *            new linked list
     */
    public void setList(IdBST newList) {
        semList = newList;
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
