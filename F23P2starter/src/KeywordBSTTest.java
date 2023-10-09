import student.TestCase;

/**
 * Tests KeywordBST class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.24
 */
public class KeywordBSTTest extends TestCase {
    private KeywordBST root;
    private KeywordBST second;
    private String[] tags1;
    private String[] tags2;
    private Seminar sem1;
    private Seminar sem2;

    /**
     * Set up variables for test cases
     */
    public void setUp() {
        tags1 = new String[] { "tag1", "tag2", "tag3" };
        tags2 = new String[] { "tag4", "tag5", "tag1" };

        sem1 = new Seminar(1, "example", "Oct30", 90, (short)2, (short)14, 23,
            tags1, "Test description");
        root = new KeywordBST("tag1", sem1);
        sem2 = new Seminar(2, "example", "Oct31", 45, (short)4, (short)28, 24,
            tags2, "Test description");
        second = new KeywordBST("tag4", sem2);

    }


    /**
     * Tests getKeyword method
     */
    public void testGetKeyword() {
        assertEquals("tag1", root.getKeyword());
        assertEquals("tag4", second.getKeyword());
    }


    /**
     * Tests change and getSem
     */
    public void testChange() {
        assertEquals(sem1, root.getSem());
        root.change(sem2, "kword");
        assertEquals(sem2, root.getSem());
        assertEquals("kword", root.getKeyword());
    }


    /**
     * Tests printSems method
     */
    public void testPrintSem() {
        assertFuzzyEquals(sem1.toString() + "\n", root.printSem());
    }


    /**
     * Test left child setter and getter
     */
    public void testSetGetLeft() {
        assertNull(root.getLeft());
        root.setLeft(second);
        assertTrue(second.equals(root.getLeft()));
    }


    /**
     * Test right child setter and getter
     */
    public void testSetGetRight() {
        assertNull(root.getRight());
        root.setRight(second);
        assertTrue(second.equals(root.getRight()));
    }
}
