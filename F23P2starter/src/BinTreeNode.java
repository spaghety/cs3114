
/**
 * This is the general interface for nodes in the BinTree data structure
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.05
 */
public interface BinTreeNode {

    /**
     * This method gets the left child of the node
     * 
     * @return the node's left child
     */
    public BinTreeNode left();


    /**
     * This method gets the right child of the node
     * 
     * @return the node's right child
     */
    public BinTreeNode right();


    /**
     * This method sets the left child of the node
     * 
     * @param node
     *            the new left child node
     */
    public void setLeft(BinTreeNode node);


    /**
     * This method sets the right child of the node
     * 
     * @param node
     *            the new right child node
     */
    public void setRight(BinTreeNode node);


    /**
     * This method adds a new Seminar to this node in the case of shared
     * coordiantes
     * 
     * @param sem
     *            new Seminar object
     */
    public void add(Seminar sem);


    /**
     * This method checks if the list of seminars is empty
     * 
     * @return true if empty, false if not
     */
    public boolean isEmpty();


    /**
     * This method gets the IdBST object representing the list of seminars
     * sorted by ID
     * 
     * @return Seminar data structure
     */
    public IdBST getList();


    /**
     * Gets size of seminar list
     * 
     * @return how many seminars are stored in this node
     */
    public int getCount();

}
