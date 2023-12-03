/**
 * @author Phillip Jordan (alexj14)
 * @version 2023.12.02
 *          This class holds all the functions called by the parser in main()
 *          and
 *          implements them in the hash table and graph
 */
public class Controller {
    GraphL graph;
    HashTable artists;
    HashTable songs;
    int newInd;

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
        newInd = 0;
    }


    /**
     * Insert a new edge and insert new hash table entries where necessary
     * 
     * @param a
     *            artist name
     * @param s
     *            song name
     * @return exit case
     */
    public int insert(String a, String s) {
        int aind = artists.find(a);
        int sind = songs.find(s);
        if (aind != -1 && sind != -1 && graph.hasEdge(aind, sind)) {
            return 4;
        }
        int result = 0;
        if (aind == -1) {
            if (artists.insert(a, newInd) == 1)
                result += 10;
            aind = newInd;
            newInd++;
            result += 1;
        }
        if (sind == -1) {
            if (songs.insert(s, newInd) == 1)
                result += 20;
            sind = newInd;
            newInd++;
            result += 2;
        }
        if (newInd >= graph.nodeCount()) {
            GraphL temp = new GraphL();
            temp.init(graph.nodeCount() * 2);
            graph.copy(temp);
            graph = temp;
        }
        graph.addEdge(aind, sind, 1);
        graph.addEdge(sind, aind, 1);
        return result;
    }


    /**
     * Removes song from the song database
     * 
     * @param name
     *            name of the song
     * @return true if successful, false if song not found
     */
    public boolean removeSong(String name) {
        int graphind = songs.find(name);
        if (graphind == -1)
            return false;
        songs.remove(name);
        int[] nbrs = graph.neighbors(graphind);
        for (int i = 0; i < nbrs.length; i++) {
            graph.removeEdge(graphind, nbrs[i]);
            graph.removeEdge(nbrs[i], graphind);
            if (graph.neighbors(nbrs[i]).length == 0) {
                artists.remove(nbrs[i]);
            }
        }
        return true;
    }


    /**
     * Removes an artist from the artist hash table and all corresponding edges
     * 
     * @param name
     *            artist name
     * @return true if successful, false if it does not exist
     */
    public boolean removeArtist(String name) {
        int graphind = artists.find(name);
        if (graphind == -1)
            return false;
        artists.remove(name);
        int[] nbrs = graph.neighbors(graphind);
        for (int i = 0; i < nbrs.length; i++) {
            graph.removeEdge(graphind, nbrs[i]);
            graph.removeEdge(nbrs[i], graphind);
            if (graph.neighbors(nbrs[i]).length == 0) {
                songs.remove(nbrs[i]);
            }
        }
        return true;
    }


    /**
     * Gets the printout of all songs
     * 
     * @return String to print
     */
    public String printSongs() {
        return songs.print() + "total songs: " + songs.count();
    }


    /**
     * Gets the printout of all artists
     * 
     * @return String to print
     */
    public String printArtists() {
        return artists.print() + "total artists: " + artists.count();
    }


    /**
     * Floyd's algorithm
     * 
     * @param G
     *            graph object
     * @param D
     *            array to put results in
     */
    private int[][] floyd(GraphL G) {
        int[][] D = new int[G.nodeCount()][G.nodeCount()];
        for (int i = 0; i < G.nodeCount(); i++) { // Initialize D with weights
            for (int j = 0; j < G.nodeCount(); j++) {
                if (G.weight(i, j) != 0) {
                    D[i][j] = G.weight(i, j);
                }
                else {
                    D[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int k = 0; k < G.nodeCount(); k++) { // Compute all k paths
            for (int i = 0; i < G.nodeCount(); i++) {
                for (int j = 0; j < G.nodeCount(); j++) {
                    if ((D[i][k] != Integer.MAX_VALUE)
                        && (D[k][j] != Integer.MAX_VALUE) && (D[i][j] > (D[i][k]
                            + D[k][j]))) {
                        D[i][j] = D[i][k] + D[k][j];
                    }
                }
            }
        }
        return D;
    }


    /**
     * Gets the printout of graph statistics
     * 
     * @return string to print
     */
    public void printGraph() {
        int[][] D = floyd(graph);
        int compCount = 0;
        int maxDi = 0;
        int maxSize = 0;
        int i = 0;
        int j = 0;
        while (i < graph.nodeCount()) {
            while (D[i][j] != Integer.MAX_VALUE) {
                maxDi = Math.max(maxDi, D[i][j] + 1);
                j++;
            }
            if (j - i > 0) {
                compCount++;
                maxSize = Math.max(maxSize, j - i);
            }
            j++;
            i = j;
        }
        System.out.println("There are " + compCount
            + " connected components\nThe largest connected component has "
            + maxSize + " elements\nThe diameter of the largest component is "
            + maxDi);
    }
}
