import student.TestCase;

/**
 * Tests the BTInternalNode class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.09
 */
public class BTInternalNodeTest extends TestCase {
    private BTInternalNode node;

    /**
     * Sets up the tests
     */
    public void setUp() {
        node = new BTInternalNode();
    }

    /**
     * Tests setLeft and setRight and getLeft and getRight
     */
    public void testGetChildren() {
        assertNull(node.left());
        assertNull(node.right());
        BinTreeNode testInNode = new BTInternalNode();
        BinTreeNode testLeafNode = new BTLeafNode();
        node.setLeft(testInNode);
        node.setRight(testLeafNode);
        assertEquals(testInNode, node.left());
        assertEquals(testLeafNode, node.right());
    }
}
