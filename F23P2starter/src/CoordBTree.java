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
        root = new BTNode(worldRad, worldRad, true);
        count = 0;
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
    private BTNode insertHelper(BTNode root, Seminar sem) {
//        System.out.println("ID: " + sem.id());
        BTNode rt = root;
        if (rt.leaf() == true) {
            if (rt.sem() == null) {
                rt.setSem(sem);
            }
            else {
                rt.toggleLeaf();
                int newRad = rt.rad();
                if (!rt.x())
                    newRad /= 2;
//                System.out.println(newRad);
                rt.setLeft(new BTNode(newRad, rt.dscr() - newRad, !rt.x()));
                rt.setRight(new BTNode(newRad, rt.dscr() + newRad, !rt.x()));
                rt = insertHelper(rt, rt.sem());
//                rt = insertHelper(rt, sem);
            }
        }
        else {
            if (rt.x()) {
//                System.out.println("\tX: " + sem.x() + ", " + rt.dscr());
                if (sem.x() <= rt.dscr())
                    rt.setLeft(insertHelper(rt.left(), sem));
                else
                    rt.setRight(insertHelper(rt.right(), sem));
            }
            else {
//                System.out.println("\tY: " + sem.y() + ", " + rt.dscr());
                if (sem.y() <= rt.dscr())
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
}
