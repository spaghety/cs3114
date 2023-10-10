import student.TestCase;

/**
 * Tests the BTLeafNode class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.05
 */
public class BTLeafNodeTest extends TestCase {
    private BTLeafNode node;
    private BTLeafNode flyWeight;
    private BTLeafNode node2;

    /**
     * Sets up the tests
     */
    public void setUp() {
        node = new BTLeafNode();
        node2 = new BTLeafNode();
        node2.add(new Seminar(2, "test", "0309221600", 13, (short) 4, (short) 9, 25, new String[] { "tag1", "tag4" },
                "test description"));
        flyWeight = new BTLeafNode();
    }

    /**
     * Tests flyweight constructor
     */
    public void testFlyWeight() {
        assertNull(flyWeight.getList());
        assertEquals(-1, flyWeight.getX());
        assertEquals(-1, flyWeight.getY());
        assertEquals(0, flyWeight.getCount());
    }

    /**
     * Tests seminar-related methods
     */
    public void testSem() {
        assertTrue(node.isEmpty());

        Seminar s1 = new Seminar(5, "test", "0309251600", 13, (short) 4, (short) 9, 15, new String[] { "tag1" },
                "test description");
        Seminar s2 = new Seminar(6, "test", "0309261600", 13, (short) 4, (short) 9, 20, new String[] { "tag2" },
                "test description");
        Seminar s3 = new Seminar(7, "test", "0309271600", 13, (short) 4, (short) 9, 10, new String[] { "tag3" },
                "test description");
        Seminar s4 = new Seminar(0, "test", "0309201600", 13, (short) 4, (short) 9, 0, new String[] { "biscuit" },
                "test description");
        Seminar s5 = new Seminar(2, "test", "0309221600", 13, (short) 4, (short) 9, 25, new String[] { "tag1", "tag4" },
                "test description");

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
        assertEquals(s2, node.getList().getNext().getSem());
        assertEquals(s3, node.getList().getNext().getNext().getSem());

        node.add(s4);
        assertEquals(4, node.getCount());
        assertEquals(s4, node.getList().getSem());
        assertEquals(s1, node.getList().getNext().getSem());

        node.add(s5);
        assertEquals(5, node.getCount());
        assertEquals(s4, node.getList().getSem());
        assertEquals(s5, node.getList().getNext().getSem());
        assertEquals(s1, node.getList().getNext().getNext().getSem());

        node.remove(2);
        assertEquals(4, node.getCount());
        assertEquals(s4, node.getList().getSem());
        assertEquals(s1, node.getList().getNext().getSem());
        assertEquals(s2, node.getList().getNext().getNext().getSem());

        node.remove(0);
        assertEquals(s1, node.getList().getSem());
        assertEquals(s2, node.getList().getNext().getSem());
    }

    /**
     * Tests the setList method
     */
    public void testSetList() {
        assertFalse(node2.isEmpty());
        node2.setList(null);
        assertTrue(node2.isEmpty());
    }

}
