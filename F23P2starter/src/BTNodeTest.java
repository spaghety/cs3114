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

    /**
     * Sets up the tests
     */
    public void setUp() {
        node = new BTNode(64, 0, 0, false);
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
        assertNull(node.sem());

        BTNode left = new BTNode(32, 1, 1, true);
        node.setLeft(left);
        assertEquals(left, node.left());
        BTNode right = new BTNode(32, 6, 6, true);
        node.setRight(right);
        assertEquals(right, node.right());

        assertTrue(left.x());
        assertEquals(32, left.rad());
        assertEquals(1, left.dscrX());
        
        assertTrue(right.x());
        assertEquals(32, right.rad());
        assertEquals(6, right.dscrX());
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
        assertNull(node.sem());
        Seminar sem = new Seminar();
        node.setSem(sem);
        assertEquals(sem, node.sem());
    }
}
