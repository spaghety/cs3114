import student.TestCase;

/**
 * Tests the DateBST class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.24
 */
public class DateBSTTest extends TestCase {
    Seminar testSem;
    Seminar testSems;
    DateBST root;
    DateBST second;

    /**
     * Sets up the objects used throughout the test cases
     */
    public void setUp() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        testSem = new Seminar(0, "test", "Jan132002", 13, (short)4, (short)9,
            15, tags, "test description");
        root = new DateBST(testSem);
        testSems = new Seminar(1, "second test name", "Jun222010", 90, (short)2,
            (short)13, 42, tags, "test description two");
        second = new DateBST(testSems);
    }


    /**
     * Tests the getDate method
     */
    public void testGetDate() {
        assertTrue(root.getDate().equals("Jan132002"));
    }


    /**
     * Tests the getSem method
     */
    public void testGetSem() {
        assertTrue(root.getSem().equals(testSem));
    }


    /**
     * Tests the getLeft and setLeft methods
     */
    public void testGetSetLeft() {
        root.setLeft(second);
        assertTrue(testSems.equals(root.getLeft().getSem()));
        assertFalse(root.isLeaf());
    }


    /**
     * Tests the getRight and setRight methods
     */
    public void testGetSetRight() {
        root.setRight(second);
        assertTrue(testSems.equals(root.getRight().getSem()));
        assertFalse(root.isLeaf());
    }


    /**
     * Tests isLeaf
     */
    public void testIsLeaf() {
        assertTrue(root.isLeaf());
    }
}
