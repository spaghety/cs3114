/**
 * This class defines the BinTree data structure used to find x and y
 * coordinates in ranges
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 */
public class CoordBTree {
    private CoordBTree leftChild;
    private CoordBTree rightChild;
    private Seminar sem;
    private int wSize;
    private boolean isLeaf;

    /**
     * Constructor
     * 
     * @param worldSize
     *            The size of the world
     */
    public CoordBTree(int worldSize) {
        leftChild = null;
        rightChild = null;
        sem = null;
        wSize = worldSize;
        isLeaf = true;
    }


    /**
     * Insert X
     * 
     * @param newSem
     *            New Seminar object
     * @param owSize
     *            Some size
     */
    public void insertX(Seminar newSem, int owSize) {
        if (newSem == null) {
            return;
        }
        if (sem == null && isLeaf == true) {
            sem = newSem;
            leftChild = new CoordBTree(owSize / 2);
            rightChild = new CoordBTree(owSize / 2);
            return;
        }
        else {
            if (newSem.x() % (owSize * 2) < owSize) {
                leftChild.insertY(newSem, wSize);
            }
            else {
                rightChild.insertY(newSem, wSize);
            }
        }
    }


    /**
     * Insert Y
     * 
     * @param newSem
     *            New Seminar object
     * @param owSize
     *            Some size
     */
    public void insertY(Seminar newSem, int owSize) {
        if (newSem == null) {
            return;
        }
        if (sem == null && isLeaf == true) {
            sem = newSem;
        }
        else {
            if (newSem.x() % (owSize * 2) < owSize) {
                leftChild.insertX(newSem, wSize);
            }
            else {
                rightChild.insertX(newSem, wSize);
            }
        }
    }
}