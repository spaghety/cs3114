import student.TestCase;

/**
 * Tests the BSTree class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.09
 */
public class BSTreeTest extends TestCase {
    private Seminar testSem;
    private Seminar testSems;
    private BSTree root;
    private BSTree second;

    /**
     * Sets up the objects used throughout the test cases
     */
    public void setUp() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        testSem = new Seminar(0, "test", "132002", 13, (short)4, (short)9, 15,
            tags, "test description");
        root = new BSTree(testSem, testSem.id(), testSem.date());
        testSems = new Seminar(1, "second test name", "222010", 90, (short)2,
            (short)13, 42, tags, "test description two");
        second = new BSTree(testSems, testSems.id(), testSems.date());
    }


    /**
     * Tests value() and getString()
     */
    public void testValue() {
        assertEquals(0, root.value());
        assertEquals(1, second.value());
        assertEquals("132002", root.getString());
        assertEquals("222010", second.getString());
        root.setValue(1000);
        assertEquals(1000, root.value());
        root.setString("Hello");
        assertEquals("Hello", root.getString());


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
