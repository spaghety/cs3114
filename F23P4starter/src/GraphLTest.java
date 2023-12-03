/**
 * @author Phillip Jordan (alexj14)
 * @version 2023.12.03
 *          Tests the Graph class
 */
public class GraphLTest extends student.TestCase {
    GraphL graph;

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
     * Tests the weight and find methods
     */
    public void testWeightFind() {
        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 2, 4);
        graph.addEdge(2, 3, 1);
        assertEquals(4, graph.weight(0, 2));
        assertEquals(0, graph.weight(5, 6));
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
