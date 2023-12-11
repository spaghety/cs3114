/**
 * This class holds all the functions called by the parser in main() and
 * implements them in the hash table and graph
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.12.05
 * 
 */
public class Controller {
    private GraphL graph;
    private HashTable artists;
    private HashTable songs;
    private int newInd;

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
            if (artists.insert(a, newInd) == 1) {
                result += 10;
            }
            aind = newInd;
            newInd++;
            result += 1;
        }
        if (sind == -1) {
            if (songs.insert(s, newInd) == 1) {
                result += 20;
            }
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
        if (graphind == -1) {
            return false;
        }
        songs.remove(name);
        int[] nbrs = graph.neighbors(graphind);
        for (int i = 0; i < nbrs.length; i++) {
            graph.removeEdge(graphind, nbrs[i]);
            graph.removeEdge(nbrs[i], graphind);
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
        if (graphind == -1) {
            return false;
        }
        artists.remove(name);
        int[] nbrs = graph.neighbors(graphind);
        for (int i = 0; i < nbrs.length; i++) {
            graph.removeEdge(graphind, nbrs[i]);
            graph.removeEdge(nbrs[i], graphind);
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
     * @param g
     *            graph object
     */
    private int[][] floyd(GraphL g) {
        int[][] d = new int[g.nodeCount()][g.nodeCount()];
        for (int i = 0; i < g.nodeCount(); i++) { // Initialize D with weights
            for (int j = 0; j < g.nodeCount(); j++) {
                if (g.weight(i, j) != 0) {
                    d[i][j] = g.weight(i, j);
                }
                else {
                    d[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int k = 0; k < g.nodeCount(); k++) { // Compute all k paths
            for (int i = 0; i < g.nodeCount(); i++) {
                for (int j = 0; j < g.nodeCount(); j++) {
                    if ((d[i][k] != Integer.MAX_VALUE)
                        && (d[k][j] != Integer.MAX_VALUE) && (d[i][j] > (d[i][k]
                            + d[k][j]))) {
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }
        return d;
    }


    /**
     * Gets the printout of graph statistics
     * 
     * @return string to print
     */
    public String printGraph() {
        int[][] d = floyd(graph);
        int vertexCount = songs.count() + artists.count();
        int[] array = graph.compConnect(graph.nodeCount());
        int rootCount = 0;
        int childCount = 0;
        int mostFreq = 0; // Most frequent element
        int maxSize = -1; // Count of mostFreq
        for (int a = 0; a < vertexCount; a++) {
            if (array[a] == -1) {
                continue; // Don't find how many -1 there are
            }
            childCount++;
            int count = 0;
            for (int b = 0; b < vertexCount; b++) {
                if (array[a] == array[b]) {
                    count++;
                }
            }
            if (count > maxSize) {
                maxSize = count;
                mostFreq = array[a];
            }
        }
        maxSize++; // Because maxSize only counted children nodes
        rootCount = vertexCount - childCount; // Real rootCount
        int maxDi = 0;
        for (int i = 0; i < graph.nodeCount(); i++) {
            for (int j = 0; j < graph.nodeCount(); j++) {
                if (d[i][j] != Integer.MAX_VALUE) {
                    if (i == mostFreq || j == mostFreq || array[i] == mostFreq
                        || array[j] == mostFreq) {
                        if (i != j) {
                            maxDi = Math.max(maxDi, d[i][j]);
                        }
                        else {
                            maxDi = Math.max(maxDi, 1);
                        }
                    }
                }
            }
        }
// int i = 0;
// int j = 0;
// while (i < graph.nodeCount()) {
// while (d[i][j] != Integer.MAX_VALUE) {
// if (i != j) {
// maxDi = Math.max(maxDi, d[i][j] + 1);
// }
// else {
// maxDi = Math.max(maxDi, 1);
// }
// j++;
// }
//// if (j - i > 0) {
//// compCount++;
//// maxSize = Math.max(maxSize, j - i);
//// }
// j++;
// i = j;
// }

        return "There are " + rootCount
            + " connected components\nThe largest connected component has "
            + maxSize + " elements\nThe diameter of the largest component is "
            + maxDi;
    }

}