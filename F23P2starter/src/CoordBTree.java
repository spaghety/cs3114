/**
 * This class defines the BinTree data structure used to find x and y
 * coordinates in ranges
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.24
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
     * @return true if success, false if not
     * 
     */
    public boolean insertX(Seminar newSem, int owSize) {
        if (newSem == null) {
            return false;
        }
        if (sem == null && isLeaf == true) {
            sem = newSem;
            leftChild = new CoordBTree(owSize / 2);
            rightChild = new CoordBTree(owSize / 2);
            return true;
        }
        else {
            if (newSem.x() % (owSize * 2) < owSize) {
                leftChild.insertY(newSem, wSize);
            }
            else {
                rightChild.insertY(newSem, wSize);
            }
            return true;
        }
    }


    /**
     * Insert Y
     * 
     * @param newSem
     *            New Seminar object
     * @param owSize
     *            Some size
     * @return true if success, false if not
     */
    public boolean insertY(Seminar newSem, int owSize) {
        if (newSem == null) {
            return false;
        }
        if (sem == null && isLeaf == true) {
            sem = newSem;
            return true;
        }
        else {
            if (newSem.x() % (owSize * 2) < owSize) {
                leftChild.insertX(newSem, wSize);
            }
            else {
                rightChild.insertX(newSem, wSize);
            }
            return true;
        }
    }
}
