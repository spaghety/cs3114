import student.TestCase;

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
        tags2 = new String[] { "tag4", "tag5", "tag6" };

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
     * Tests add method
     */
    public void testAdd() {
        assertTrue(root.add(sem1));
        assertEquals(2, root.getSize());
        assertFalse(root.add(sem2));
        assertEquals(2, root.getSize());
        assertTrue(root.add(sem1));
        assertEquals(3, root.getSize());
        assertTrue(root.add(sem1));
        assertEquals(4, root.getSize());
        assertTrue(root.add(sem1));
        assertEquals(5, root.getSize());
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
