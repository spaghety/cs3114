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
    private int visit;
    private int worldSize;

    /**
     * Flyweight for the empty leaf node
     */
    public static final BTNode FLYWEIGHT = new BTNode();

    /**
     * Constructor
     * 
     * @param size
     *            world size
     */
    public CoordBTree(int size) {
        worldSize = size;
        int worldRad = (int)size / 2;
// root = new BTNode(worldRad, worldRad, worldRad, true);
        root = FLYWEIGHT;
        count = 0;
        visit = 0;
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
     * New recursive insert helper method to insert new seminars by coordinates
     * 
     * @param rt
     *            root node
     * @param sem
     *            new Seminar
     * @param sizeX
     *            size of x
     * @param sizeY
     *            size of y
     * @param l
     *            left bound
     * @param u
     *            upper bound
     * @return modified root node
     * 
     */
    private BTNode insertHelper2(
        BTNode rt,
        Seminar sem,
        int sizeX,
        int sizeY,
        int l,
        int u) {

        int r = l + sizeX;
        int d = u + sizeY;
        int dcx = (r + l) / 2;
        int dcy = (u + d) / 2;

        if (rt == FLYWEIGHT) {
            rt = new BTNode();
            rt.add(sem);
            return rt;
        }

        if (rt.leaf()) {
            if (rt.getX() == sem.x() && rt.getY() == sem.y()) {
                rt.add(sem);
            }
            else {
                IdBST curr = rt.getList();
                rt.setInternal();
                rt.toggleLeaf();
                rt.setLeft(FLYWEIGHT);
                rt.setRight(FLYWEIGHT);
                while (curr != null) {
                    rt = insertHelper2(rt, curr.getSem(), sizeX, sizeY, l, u);
                    curr = curr.getLeft();
                }
                rt = insertHelper2(rt, sem, sizeX, sizeY, l, u);
            }
        }
        else {
            if (sizeX == sizeY) {
                if (sem.x() < dcx)
                    rt.setLeft(insertHelper2(rt.left(), sem, sizeX / 2, sizeY,
                        l, u));
                else
                    rt.setRight(insertHelper2(rt.right(), sem, sizeX / 2, sizeY,
                        dcx, u));
            }
            else {
                if (sem.y() < dcy)
                    rt.setLeft(insertHelper2(rt.left(), sem, sizeX, sizeY / 2,
                        l, u));
                else
                    rt.setRight(insertHelper2(rt.right(), sem, sizeX, sizeY / 2,
                        l, dcy));
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
        root = insertHelper2(root, sem, worldSize, worldSize, 0, 0);
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
            if (rt.isEmpty())
                result += indent + "E\n";
            else {
                result += indent + "Leaf with 1 objects:";
                IdBST curr = rt.getList();
                while (curr != null) {
                    result += " " + curr.getId();
                    curr = curr.getLeft();
                }
                result += "\n";
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
     * Calculates the minimum distance between a point and a box, squared.
     * If a point is within the box, the value is 0.
     * Public method for direct testing.
     * 
     * @param px
     *            x coordinate of search point
     * @param py
     *            y coordinate of search point
     * @param l
     *            left bound
     * @param r
     *            right bound
     * @param u
     *            upper bound
     * @param d
     *            lower "down" bound
     * @return The result
     */
    public int minDistToBox2(int px, int py, int l, int r, int u, int d) {
        int dx = 0;
        int dy = 0;
        if (px <= l)
            dx = l - px;
        else if (px > r)
            dx = px - r;
        if (py <= u)
            dy = u - py;
        else if (py > d)
            dy = py - d;
        return dx * dx + dy * dy;
    }


    /**
     * Euclidean distance of two points, squared
     * 
     * @param x1
     *            x1
     * @param x2
     *            x2
     * @param y1
     *            y1
     * @param y2
     *            y2
     * @return result
     */
    private int dist2(int x1, int x2, int y1, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }


    /**
     * Helper method for search
     * 
     * @param rt
     *            root node
     * @param sx
     *            search x-coordinate
     * @param sy
     *            search y-coordinate
     * @param rad
     *            search radius
     * @param sizeX
     *            size of x
     * @param sizeY
     *            size of y
     * @param l
     *            left bound
     * @param u
     *            upper bound
     * @return A string of search results
     */
    private String searchHelp(
        BTNode rt,
        int sx,
        int sy,
        int rad,
        int sizeX,
        int sizeY,
        int l,
        int u) {
        visit++;
        String result = "";
        if (rt.leaf()) {
            if (rt.isEmpty())
                return result += "";
            int distance2 = dist2(rt.getX(), sx, rt.getY(), sy);
            if (distance2 <= rad * rad) {
                IdBST curr = rt.getList();
                while (curr != null) {
                    result += ("Found a record with key value " + curr.getId()
                        + " at " + curr.getSem().x() + ", " + curr.getSem().y()
                        + "\n");
                    curr = curr.getLeft();
                }
            }
        }
        else {
            int r = l + sizeX;
            int d = u + sizeY;
            int dcx = (r + l) / 2;
            int dcy = (u + d) / 2;
            int minDist = Integer.MAX_VALUE;
            if (sizeX == sizeY) {
                minDist = minDistToBox2(sx, sy, l, dcx, u, d);
                if (minDist <= rad * rad)
                    result += searchHelp(rt.left(), sx, sy, rad, sizeX / 2,
                        sizeY, l, u);
                minDist = minDistToBox2(sx, sy, dcx, r, u, d);
                if (minDist <= rad * rad)
                    result += searchHelp(rt.right(), sx, sy, rad, sizeX / 2,
                        sizeY, dcx, u);
            }
            else {
                minDist = minDistToBox2(sx, sy, l, r, u, dcy);
                if (minDist <= rad * rad)
                    result += searchHelp(rt.left(), sx, sy, rad, sizeX, sizeY
                        / 2, l, u);
                minDist = minDistToBox2(sx, sy, l, r, dcy, d);
                if (minDist <= rad * rad)
                    result += searchHelp(rt.right(), sx, sy, rad, sizeX, sizeY
                        / 2, l, dcy);
            }
        }
        return result;
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
// System.out.println(worldSize);
        String result = searchHelp(root, x, y, r, worldSize, worldSize, 0, 0);
        System.out.print(result);
        System.out.printf("%d nodes visited in this search\n", visit);
        visit = 0;
    }
}
