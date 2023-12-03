/**
 * @author Phillip Jordan (alexj14)
 *         This class holds all the functions called by the parser in main() and
 *         implements them in the hash table and graph
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
     * @return
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
        graph.addEdge(aind, sind, 1);
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
}
