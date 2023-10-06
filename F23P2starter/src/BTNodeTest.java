import student.TestCase;

/**
 * Tests the BTNode class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.05
 */
public class BTNodeTest extends TestCase {
    private BTNode node;
    private BTNode flyWeight;

    /**
     * Sets up the tests
     */
    public void setUp() {
        node = new BTNode(64, 0, 0, false);
        flyWeight = new BTNode();
    }


    /**
     * Tests flyweight constructor
     */
    public void testFlyWeight() {
        assertEquals(-1, flyWeight.rad());
        assertEquals(-1, flyWeight.dscrX());
        assertEquals(-1, flyWeight.dscrY());
        assertFalse(flyWeight.x());
        assertNull(flyWeight.getList());
        assertNull(flyWeight.left());
        assertNull(flyWeight.right());
        assertTrue(flyWeight.leaf());
        assertEquals(-1, flyWeight.getX());
        assertEquals(-1, flyWeight.getY());
    }


    /**
     * Tests (most) setters and getters
     */
    public void testSetterGetter() {
        assertEquals(64, node.rad());
        assertEquals(0, node.dscrX());
        assertFalse(node.x());
        assertNull(node.left());
        assertNull(node.right());
        assertNull(node.getList());

        BTNode left = new BTNode(32, 1, 2, true);
        node.setLeft(left);
        assertEquals(left, node.left());
        BTNode right = new BTNode(32, 6, 3, true);
        node.setRight(right);
        assertEquals(right, node.right());

        assertTrue(left.x());
        assertEquals(32, left.rad());
        assertEquals(1, left.dscrX());
        assertEquals(2, left.dscrY());
        assertTrue(right.x());
        assertEquals(32, right.rad());
        assertEquals(6, right.dscrX());
        assertEquals(3, right.dscrY());
    }


    /**
     * Tests leaf-related methods
     */
    public void testLeaf() {
        assertTrue(node.leaf());
        node.toggleLeaf();
        assertFalse(node.leaf());
        node.toggleLeaf();
        assertTrue(node.leaf());
    }


    /**
     * Tests seminar-related methods
     */
    public void testSem() {
        assertTrue(node.isEmpty());

        Seminar s1 = new Seminar(5, "test", "0309251600", 13, (short)4,
            (short)9, 15, new String[] { "tag1" }, "test description");
        Seminar s2 = new Seminar(6, "test", "0309261600", 13, (short)4,
            (short)9, 20, new String[] { "tag2" }, "test description");
        Seminar s3 = new Seminar(7, "test", "0309271600", 13, (short)4,
            (short)9, 10, new String[] { "tag3" }, "test description");
        Seminar s4 = new Seminar(0, "test", "0309201600", 13, (short)4,
            (short)9, 0, new String[] { "biscuit" }, "test description");
        Seminar s5 = new Seminar(2, "test", "0309221600", 13, (short)4,
            (short)9, 25, new String[] { "tag1", "tag4" }, "test description");

        node.add(s1);
        assertFalse(node.isEmpty());
        assertEquals(4, node.getX());
        assertEquals(9, node.getY());
        assertEquals(1, node.getCount());
        assertEquals(s1, node.getList().getSem());

        node.add(s2);
        assertEquals(2, node.getCount());
        node.add(s3);
        assertEquals(3, node.getCount());
        assertEquals(s2, node.getList().getLeft().getSem());
        assertEquals(s3, node.getList().getLeft().getLeft().getSem());

        node.add(s4);
        assertEquals(4, node.getCount());
        assertEquals(s4, node.getList().getSem());
        assertEquals(s1, node.getList().getLeft().getSem());

        node.add(s5);
        assertEquals(5, node.getCount());
        assertEquals(s4, node.getList().getSem());
        assertEquals(s5, node.getList().getLeft().getSem());
        assertEquals(s1, node.getList().getLeft().getLeft().getSem());

    }
}
