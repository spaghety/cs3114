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


    public int insert(String a, String s) {
        int aind = artists.find(a);
        int sind = songs.find(s);
        if (aind == -1) {
        }
        graph.addEdge(aind, sind, 1);
        return 0;
    }
}
