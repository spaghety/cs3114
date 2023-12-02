/**
 * @author Phillip Jordan (alexj14)
 *         This class holds all the functions called by the parser in main() and
 *         implements them in the hash table and graph
 */
public class Controller {
    GraphL graph;
    HashTable artists;
    HashTable songs;

    /**
     * Constructor initializes variables and structures
     * 
     * @param initsize
     *            initial size of the hash table and graph
     */
    public Controller(int initsize) {
        graph = new GraphL();
        artists = new HashTable(initsize);
        songs = new HashTable(initsize);
        graph.init(initsize * 2);
    }


    public int insert(String v, String w) {
        if (graph.hasEdge(1, 2)) return 4;
        return 0;
    }
}
