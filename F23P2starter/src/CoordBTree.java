/**
 * This class defines the BinTree data structure used to find x and y
 * coordinates in ranges
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.03
 */
public class CoordBTree {
    private BTNode root;
    private int count;
    private int worldSize;

    public CoordBTree(int size) {
        worldSize = size;
        int worldRad = (int)size / 2;
        root = new BTNode(worldRad, worldRad, worldRad, true);
        count = 0;
    }


    /**
     * Gets the root node
     * 
     * @return root
     */
    public BTNode getRoot() {
        return root;
    }


    /**
     * recursive insert helper method to insert new seminars by coordinates
     * 
     * @param rt
     *            root node
     * @param sem
     *            new Seminar
     * @return modified root node
     */
    private BTNode insertHelper(BTNode rt, Seminar sem) {
        if (rt.leaf()) {
            if (rt.sem() == null) {
                rt.setSem(sem);
            }
            else {
                rt.toggleLeaf();
                int newRad = (int)rt.rad() / 2;
                if (rt.x()) {
                    rt.setLeft(new BTNode(rt.rad(), rt.dscrX() - newRad, rt
                        .dscrY(), false));
                    rt.setRight(new BTNode(rt.rad(), rt.dscrX() + newRad, rt
                        .dscrY(), false));
                }
                else {
                    rt.setLeft(new BTNode(newRad, rt.dscrX(), rt.dscrY()
                        - newRad, true));
                    rt.setRight(new BTNode(newRad, rt.dscrX(), rt.dscrY()
                        + newRad, true));
                }
                rt = insertHelper(rt, sem);
                rt = insertHelper(rt, rt.sem());
            }
        }
        else {
            if (rt.x()) {
                if (sem.x() <= rt.dscrX())
                    rt.setLeft(insertHelper(rt.left(), sem));
                else
                    rt.setRight(insertHelper(rt.right(), sem));
            }
            else {
                if (sem.y() <= rt.dscrY())
                    rt.setLeft(insertHelper(rt.left(), sem));
                else
                    rt.setRight(insertHelper(rt.right(), sem));
            }
        }
        return rt;
    }


    /**
     * Public insert method used by the parser
     * 
     * @param sem
     *            new Seminar object to insert
     * @return true if successful, false if not
     */
    public boolean insert(Seminar sem) {
        int x = sem.x();
        int y = sem.y();
        if (x >= worldSize || y >= worldSize || x < 0 || y < 0)
            return false;
        root = insertHelper(root, sem);
        return true;
    }


    /**
     * Helps print out the bintree recursively
     * 
     * @param rt
     *            root node
     * @param indent
     *            indent size
     * @return the print out
     */
    private String toStringHelper(BTNode rt, String indent) {
        if (rt == null)
            return "";
        String result = "";
        String newIndent = indent += "  ";
        result += toStringHelper(rt.right(), newIndent);
        if (rt.leaf()) {
            if (rt.sem() == null)
                result += indent + "E\n";
            else {
                result += indent + "Leaf with 1 objects: " + rt.sem().id()
                    + "\n";
            }
        }
        else
            result += indent + "I\n";
        result += toStringHelper(rt.left(), newIndent);
        return result;

    }


    /**
     * Prints out the bintree
     * 
     * @return the print output
     */
    public String toString() {
        return toStringHelper(root, "");
    }


    /**
     * Search method
     * 
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     * @param r
     *            radius
     */
    public void search(int x, int y, int r) {

    }
}
