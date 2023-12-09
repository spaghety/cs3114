import static org.junit.Assert.assertArrayEquals;

/**
 * Tests the Graph class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.12.07
 */
public class GraphLTest extends student.TestCase {
    private GraphL graph;

    /**
     * Sets up conditions for future tests
     */
    public void setUp() {
        graph = new GraphL();
        graph.init(10);
    }


    /**
     * Tests the count methods
     */
    public void testCount() {
        assertEquals(10, graph.nodeCount());
        assertEquals(0, graph.edgeCount());
    }


    /**
     * Tests the add edge method
     */
    public void testAddEdge() {
        assertFalse(graph.hasEdge(0, 1));
        graph.addEdge(0, 1, 1);
        assertEquals(1, graph.edgeCount());
        assertTrue(graph.hasEdge(0, 1));
        graph.addEdge(1, 2, 0);
        assertEquals(1, graph.edgeCount());
        assertFalse(graph.hasEdge(1, 2));
    }


    /**
     * Tests the add edge method
     */
    public void testAddEdge2() {
        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 2, 4);
        graph.addEdge(2, 3, 1);
        graph.addEdge(2, 5, 6);
        graph.addEdge(2, 8, 7);
        assertEquals(5, graph.edgeCount());
        graph.addEdge(1, 0, 5);
        assertEquals(6, graph.edgeCount());
        graph.addEdge(2, 7, 2);
        assertEquals(7, graph.edgeCount());
        graph.addEdge(2, 5, 6);
        assertEquals(7, graph.edgeCount());

    }


    /**
     * Tests union
     */
    public void testUnion() {
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 0, 1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 2, 1);
        graph.addEdge(2, 5, 1);
        graph.addEdge(5, 2, 1);
        graph.addEdge(3, 8, 1);
        graph.addEdge(8, 3, 1);
        int[] compare = { 1, -1, 5, 5, -1, -1 };
        assertArrayEquals(compare, graph.compConnect(6));
        graph.addEdge(0, 2, 1);
        graph.addEdge(2, 0, 1);
        int[] compare2 = { 8, 8, 8, 8, -1, 8 };
        assertArrayEquals(compare2, graph.compConnect(6));
    }


    /**
     * Tests the remove edge method
     */
    public void testRemoveEdge() {
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 0, 1);
        assertEquals(2, graph.edgeCount());
        graph.removeEdge(1, 0);
        assertEquals(1, graph.edgeCount());
        assertFalse(graph.hasEdge(1, 0));
    }


    /**
     * Tests the remove edge method
     */
    public void testRemoveEdge2() {
        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 2, 4);
        graph.addEdge(2, 3, 1);
        graph.addEdge(2, 5, 6);
        graph.addEdge(2, 8, 7);
        assertEquals(5, graph.edgeCount());
        graph.removeEdge(1, 0);
        assertEquals(5, graph.edgeCount());
        graph.removeEdge(2, 7);
        assertEquals(5, graph.edgeCount());
        graph.removeEdge(2, 5);
        assertEquals(4, graph.edgeCount());
        assertFalse(graph.hasEdge(2, 5));
    }


    /**
     * Tests the weight and find methods
     */
    public void testWeightFind() {
        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 2, 4);
        graph.addEdge(2, 3, 1);
        assertEquals(4, graph.weight(0, 2));
        assertEquals(0, graph.weight(5, 6));
        assertEquals(3, graph.weight(0, 1));
        graph.addEdge(2, 5, 6);
        graph.addEdge(2, 8, 7);
        assertEquals(0, graph.weight(2, 7));
    }


    /**
     * Tests the neighbor method
     */
    public void testNeighbors() {
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(0, 3, 1);
        graph.addEdge(0, 4, 1);
        assertEquals(1, graph.neighbors(0)[0]);
        assertEquals(2, graph.neighbors(0)[1]);
    }


    /**
     * Tests the copy helper method
     */
    public void testCopy() {
        graph.addEdge(0, 1, 1);
        graph.addEdge(3, 4, 1);
        GraphL otherGraph = new GraphL();
        otherGraph.init(20);
        graph.copy(otherGraph);
        assertEquals(20, otherGraph.nodeCount());
        assertEquals(2, otherGraph.edgeCount());
    }
}
