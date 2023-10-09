import student.TestCase;

/**
 * Tests the BTInternalNode class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.05
 */
public class BTInternalNodeTest extends TestCase {
    private BTInternalNode node;

    /**
     * Sets up the tests
     */
    public void setUp() {
        node = new BTInternalNode();
    }
    
    public void testSetChildren() {
        BinTreeNode testNode = new BTInternalNode();
        node.setLeft(testNode);
        testNode = new BTLeafNode();
        node.setRight(testNode);
    }
    
    public void testGetChildren() {
        BinTreeNode testInNode = new BTInternalNode();
        BinTreeNode testLeafNode = new BTLeafNode();
        node.setLeft(testInNode);
        node.setRight(testLeafNode);
        assertEquals(testInNode, node.left());
        assertEquals(testLeafNode, node.right());
    }
}
