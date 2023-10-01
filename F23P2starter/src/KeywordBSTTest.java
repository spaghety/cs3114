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
     * Tests getSize method
     */
    public void testGetSize() {
        assertEquals(1, root.getSize());
    }


    /**
     * Tests getKeyword method
     */
    public void testGetKeyword() {
        assertEquals("tag1", root.getKeyword());
        assertEquals("tag4", second.getKeyword());
    }


    /**
     * Tests printSems method
     */
    public void testPrintSems() {
        assertFuzzyEquals(sem1.toString() + "\n", root.printSems());
        root.add(sem2);
        assertFuzzyEquals(sem1.toString() + "\n" + sem2.toString() + "\n", root
            .printSems());

    }


    /**
     * Tests add method
     */
    public void testAdd() {
        assertTrue(root.add(sem1));
        assertEquals(2, root.getSize());
        assertTrue(root.add(sem2));
        assertEquals(3, root.getSize());
        assertTrue(root.add(sem1));
        assertEquals(4, root.getSize());
        assertTrue(root.add(sem1));
        assertEquals(5, root.getSize());
        assertTrue(root.add(sem1));
        assertEquals(6, root.getSize());
        assertFalse(second.add(sem1));
        assertEquals(1, second.getSize());
        assertTrue(second.add(sem2));
        assertEquals(2, second.getSize());
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
