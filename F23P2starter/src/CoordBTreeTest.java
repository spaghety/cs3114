import student.TestCase;

/**
 * Tests the CoordBTree class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.24
 */
public class CoordBTreeTest extends TestCase {
    private CoordBTree coordBTree;
    private Seminar sem;
    private int wSize;

    /**
     * Sets up the tests following
     */
    public void setUp() {
        wSize = 2;
        coordBTree = new CoordBTree(wSize);

        String[] tags1 = new String[] { "tag1", "tag2", "tag3" };
        sem = new Seminar(1, "example", "Oct30", 90, (short)2, (short)14, 23,
            tags1, "Test description");
    }


    /**
     * Tests insertX
     */
    public void testInsertX() {
        coordBTree.insertX(null, wSize);
        coordBTree.insertX(sem, wSize);
        coordBTree.insertX(sem, wSize);
    }


    /**
     * Tests insertY
     */
    public void testInsertY() {
        coordBTree.insertY(null, wSize);
        coordBTree.insertY(sem, wSize * 8);
    }
}
