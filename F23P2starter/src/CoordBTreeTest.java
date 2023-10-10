import java.io.*;
import student.TestCase;

/**
 * Tests the CoordBTree class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.09
 */
public class CoordBTreeTest extends TestCase {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PrintStream stdout;

    private CoordBTree bt;
    private Seminar sem;
    private int wSize;
    private String[] tags;

    /**
     * Sets up the tests following
     */
    public void setUp() {
        stdout = System.out;
        wSize = 8;
        bt = new CoordBTree(wSize);
        tags = new String[] { "tag1", "tag2", "tag3" };
        sem = new Seminar(0, "example", "Oct30", 90, (short)2, (short)6, 23,
            tags, "Test description");
    }


    /**
     * Tests getRoot
     */
    public void testGetRoot() {
        assertEquals(CoordBTree.FLYWEIGHT, bt.getRoot());
    }


    /**
     * Tests insert false conditions
     */
    public void testInsertFalse() {
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
    }


    /**
     * Tests insert and then search
     */
    public void testInsert() {
        PrintStream stream = new PrintStream(out);
// System.setOut(stream);
        String content = "";
        String result = "";
        Seminar trueSem = new Seminar(1, "example", "Oct30", 90, (short)2,
            (short)2, 2, tags, "Test description");
        Seminar trueSem2 = new Seminar(2, "example", "Oct30", 90, (short)2,
            (short)1, 2, tags, "Test description");
        Seminar trueSem2Same = new Seminar(12, "example", "Oct30", 90, (short)2,
            (short)1, 2, tags, "Test description");
        Seminar trueSem3 = new Seminar(3, "example", "Oct30", 90, (short)6,
            (short)1, 7, tags, "Test description");
        Seminar trueSem4 = new Seminar(4, "example", "Oct30", 90, (short)6,
            (short)7, 7, tags, "Test description");
        Seminar trueSem5 = new Seminar(5, "example", "Oct30", 90, (short)6,
            (short)0, 7, tags, "Test description");
        Seminar trueSem6 = new Seminar(6, "example", "Oct30", 90, (short)7,
            (short)0, 7, tags, "Test description");
        assertTrue(bt.insert(sem));
        assertTrue(bt.insert(trueSem));
        assertTrue(bt.insert(trueSem2));
        assertTrue(bt.insert(trueSem2Same));
        assertTrue(bt.insert(trueSem2));
        assertTrue(bt.insert(trueSem6));
        assertTrue(bt.insert(trueSem4));
        assertTrue(bt.insert(trueSem5));
        assertTrue(bt.insert(trueSem3));
// System.out.println(bt.toString());

        result = bt.search(0, 0, 20);
        content = "Found a record with key value 2 at 2, 1\n"
            + "Found a record with key value 2 at 2, 1\n"
            + "Found a record with key value 12 at 2, 1\n"
            + "Found a record with key value 1 at 2, 2\n"
            + "Found a record with key value 0 at 2, 6\n"
            + "Found a record with key value 5 at 6, 0\n"
            + "Found a record with key value 3 at 6, 1\n"
            + "Found a record with key value 6 at 7, 0\n"
            + "Found a record with key value 4 at 6, 7\n"
            + "19 nodes visited in this search\n";
        assertFuzzyEquals(content, result);

        result = bt.search(2, 2, 0);
        content = "Found a record with key value 1 at 2, 2\n"
            + "7 nodes visited in this search\n";
        assertFuzzyEquals(content, result);

        result = bt.search(6, 6, 1);
        content = "Found a record with key value 4 at 6, 7\n"
            + "3 nodes visited in this search\n";
        assertFuzzyEquals(content, result);
        System.setOut(stdout);
    }


    /**
     * Tests minDistToBox2;
     */
    public void testMinDistToBox2() {
        assertEquals(2, bt.minDistToBox2(1, 1, 2, 4, 2, 4));
        assertEquals(1, bt.minDistToBox2(3, 1, 2, 4, 2, 4));
        assertEquals(2, bt.minDistToBox2(5, 1, 2, 4, 2, 4));
        assertEquals(1, bt.minDistToBox2(1, 3, 2, 4, 2, 4));
        assertEquals(0, bt.minDistToBox2(3, 3, 2, 4, 2, 4));
        assertEquals(1, bt.minDistToBox2(5, 3, 2, 4, 2, 4));
        assertEquals(2, bt.minDistToBox2(1, 5, 2, 4, 2, 4));
        assertEquals(1, bt.minDistToBox2(3, 5, 2, 4, 2, 4));
        assertEquals(2, bt.minDistToBox2(5, 5, 2, 4, 2, 4));
    }


    /**
     * Tests remove method
     */
    public void testRemove() {
        Seminar sem1 = new Seminar(1, "example", "Oct30", 90, (short)2,
            (short)2, 2, tags, "Test description");
        Seminar sem2 = new Seminar(3, "example", "Oct30", 90, (short)2,
            (short)6, 6, tags, "Test description");
        Seminar sem3 = new Seminar(4, "example", "Oct30", 90, (short)4,
            (short)6, 6, tags, "Test description");
        bt.insert(sem);
        assertFalse(bt.remove(1, 4, 1));
        assertTrue(bt.remove(2, 6, 0));
        assertTrue(bt.getRoot() instanceof BTLeafNode);
        bt.insert(sem);
        bt.insert(sem1);
        System.out.println(bt.toString());
        bt.remove(2, 2, 1);
        //assertTrue(bt.getRoot() instanceof BTLeafNode);
    }

}
