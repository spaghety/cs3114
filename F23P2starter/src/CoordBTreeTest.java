import student.TestCase;

/**
 * Tests the CoordBTree class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.05
 */
public class CoordBTreeTest extends TestCase {
    private CoordBTree bt;
    private Seminar sem;
    private int wSize;

    /**
     * Sets up the tests following
     */
    public void setUp() {
        wSize = 8;
        bt = new CoordBTree(wSize);

        String[] tags1 = new String[] { "tag1", "tag2", "tag3" };
        sem = new Seminar(0, "example", "Oct30", 90, (short)2, (short)6, 23,
            tags1, "Test description");
    }


    public void testInsert() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        Seminar falseSem = new Seminar(1, "test", "0", 10, (short)-1, (short)4,
            12, tags, "desc");
        Seminar falseSem2 = new Seminar(1, "test", "0", 10, (short)128,
            (short)6, 12, tags, "desc");
        Seminar falseSem3 = new Seminar(1, "test", "0", 10, (short)2, (short)-1,
            12, tags, "desc");
        Seminar falseSem4 = new Seminar(1, "test", "0", 10, (short)2,
            (short)128, 12, tags, "desc");
        assertFalse(bt.insert(falseSem));
        assertFalse(bt.insert(falseSem2));
        assertFalse(bt.insert(falseSem3));
        assertFalse(bt.insert(falseSem4));
        Seminar trueSem = new Seminar(1, "example", "Oct30", 90, (short)2,
            (short)2, 2, tags, "Test description");
        Seminar trueSem2 = new Seminar(2, "example", "Oct30", 90, (short)2,
            (short)1, 2, tags, "Test description");
        Seminar trueSem3 = new Seminar(3, "example", "Oct30", 90, (short)6,
            (short)1, 2, tags, "Test description");
        Seminar trueSem4 = new Seminar(4, "example", "Oct30", 90, (short)6,
            (short)1, 7, tags, "Test description");
        Seminar trueSem5 = new Seminar(4, "example", "Oct30", 90, (short)6,
            (short)7, 7, tags, "Test description");
        assertTrue(bt.insert(sem));
        assertTrue(bt.insert(trueSem));
        assertTrue(bt.insert(trueSem2));
        assertTrue(bt.insert(trueSem3));
        assertTrue(bt.insert(trueSem4));
        assertTrue(bt.insert(trueSem5));
    }
}
