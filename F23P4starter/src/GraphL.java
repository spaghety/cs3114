/**
 * @author Phillip Jordan (alexj14)
 *         Defines the graph data structure
 */
public class GraphL {

    private class Edge { // Doubly linked list node
        int vertex, weight;
        Edge prev, next;

        Edge(int v, int w, Edge p, Edge n) {
            vertex = v;
            weight = w;
            prev = p;
            next = n;
        }
    }

    private Edge[] nodeArray;
    private int numEdge;

    // No real constructor needed
    GraphL() {
    }


    /**
     * Initialize graph with n vertices
     * 
     * @param n
     *            number of vertices
     */
    public void init(int n) {
        nodeArray = new Edge[n];
        // List headers;
        for (int i = 0; i < n; i++) {
            nodeArray[i] = new Edge(-1, -1, null, null);
        }
        numEdge = 0;
    }


    /**
     * Return num of vertices
     * 
     * @return number of vertices
     */
    public int nodeCount() {
        return nodeArray.length;
    }


    /**
     * Gets current num of edges
     * 
     * @return number of edges
     */
    public int edgeCount() {
        return numEdge;
    }


    /**
     * Return the link in v's neighbor list the preceds the one with w (or where
     * it would be)
     * 
     * @param v
     *            index of vertex one
     * @param w
     *            index of vertex two
     * @return Edge preceding the edge connecting them
     */
    private Edge find(int v, int w) {
        Edge curr = nodeArray[v];
        while ((curr.next != null) && (curr.next.vertex < w)) {
            curr = curr.next;
        }
        return curr;
    }


    /**
     * Adds a new edge from node v to node w with weight wgt
     * 
     * @param v
     *            node one
     * @param w
     *            node two
     * @param wgt
     *            weight
     */
    public void addEdge(int v, int w, int wgt) {
        if (wgt == 0) {
            return;
        } // Can't store weight of 0
        Edge curr = find(v, w);
        if ((curr.next != null) && (curr.next.vertex == w)) {
            curr.next.weight = wgt;
        }
        else {
            curr.next = new Edge(w, wgt, curr, curr.next);
            numEdge++;
            if (curr.next.next != null) {
                curr.next.next.prev = curr.next;
            }
        }
    }


    /**
     * Get the weight of a specific edge
     * 
     * @param v
     *            node one
     * @param w
     *            node two
     * @return weight value
     */
    public int weight(int v, int w) {
        Edge curr = find(v, w);
        if ((curr.next == null) || (curr.next.vertex != w)) {
            return 0;
        }
        else {
            return curr.next.weight;
        }
    }


    /**
     * Removes an edge from the graph
     * 
     * @param v
     *            node one
     * @param w
     *            node two
     */
    public void removeEdge(int v, int w) {
        Edge curr = find(v, w);
        if ((curr.next == null) || curr.next.vertex != w) {
            return;
        }
        else {
            curr.next = curr.next.next;
            if (curr.next != null) {
                curr.next.prev = curr;
            }
        }
        numEdge--;
    }


    /**
     * Returns true if the graph has a specified edge
     * 
     * @param v
     *            node one
     * @param w
     *            node two
     * @return true if exists, false if not
     */
    public boolean hasEdge(int v, int w) {
        return weight(v, w) != 0;
    }


    // Returns an array containing the indicies of the neighbors of v
    public int[] neighbors(int v) {
        int cnt = 0;
        Edge curr;
        for (curr = nodeArray[v].next; curr != null; curr = curr.next) {
            cnt++;
        }
        int[] temp = new int[cnt];
        cnt = 0;
        for (curr = nodeArray[v].next; curr != null; curr = curr.next) {
            temp[cnt++] = curr.vertex;
        }
        return temp;
    }
    
    public void copy(GraphL newGraph) {
        for (int i=0;i<nodeArray.length;i++) {
            Edge curr = nodeArray[i].next;
            while (curr != null) {
                newGraph.addEdge(i, curr.vertex, 1);
                curr = curr.next;
            }
        }
    }
}
