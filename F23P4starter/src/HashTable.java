/**
 * 
 */

/**
 * 
 */
public class HashTable {
    private Edge[] songs, artists;
    private class Edge {
        int weight;
        String val;
        Edge prev, next;
        Edge(String v, int w, Edge p, Edge n) {
            val = v;
            weight = w;
            prev = p;
            next = n;
        }
    }

    public HashTable(int initSize) {
        songs = new Edge[initSize];
        artists = new Edge[initSize];
    }
}
