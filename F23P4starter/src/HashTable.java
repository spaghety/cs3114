/**
 * Defines the Hash Table and graph contained within
 * 
 * @author Phillip Jordan (alexj14)
 *
 */
public class HashTable {
    private Vertex[] songs, artists;
    private int size;
    int artCount;
    int songCount;

    /**
     * Defines graph edges
     * 
     * @author Phillip Jordan (alexj14)
     *
     */
    private class Edge {
        int weight;
        String song;
        String art;
        Edge prev, next;

        /**
         * Edge constructor just takes node value
         * 
         * @param v
         *            artist or song name
         */
        Edge(String s, String a) {
            song = s;
            art = a;
            weight = 0;
            prev = null;
            next = null;
        }
    }


    /**
     * This class defines a song or artist node in the graph
     * 
     * @author Phillip Jordan (alexj14)
     *
     */
    private class Vertex {
        Edge head;
        String val;

        /**
         * Constructor initializes the vertex
         * 
         * @param v
         *            value - either song name or artist name
         */
        public Vertex(String v) {
            val = v;
            head = null;
        }
    }

    /**
     * Simple constructor
     * 
     * @param initSize
     *            initial hash table size
     */
    public HashTable(int initSize) {
        songs = new Vertex[initSize];
        artists = new Vertex[initSize];
        size = initSize;
        artCount = 0;
        songCount = 0;
    }


    /**
     * Combines the hashing and probing functions together and returns the index
     * 
     * @param arr
     *            array input
     * @param v
     *            value input
     * @return index to insert
     */
    public int hashNProbe(Vertex[] arr, String v) {
        int init = Hash.h(v, arr.length);
        Vertex probe = songs[init];
        int prober = 0;
        while (probe != null) {
            if (probe.val == v) {
                return (int)(init + Math.pow((double)prober, 2.0));
            }
            prober++;
            probe = songs[(int)(init + Math.pow(prober, 2.0))];
        }
        return init + (int)Math.pow(prober, 2.0);
    }


    /**
     * Finds a specific artist and song edge and returns null if it doesn't
     * exist
     * 
     * @param a
     *            artist name
     * @param s
     *            song name
     * @return Edge object
     */
    private Edge find(String a, String s) {
        int ind = hashNProbe(artists, a);
        Edge curr = artists[ind].head;
        while (curr != null) {
            if (curr.art == a && curr.song == s) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }


    /**
     * Insert new record
     * 
     * @param song
     *            song title
     * @param artist
     *            artist name
     * @return exit status: 0=new artist and song, 1=new artist, 2=new
     *         song, 3=edge insert, 4=already exists
     */
    public int insert(String song, String artist) {
        if (find(song, artist) != null)
            return 4;
        int songInd = hashNProbe(songs, song);
        int status = 0;
        if (songs[songInd] == null) {
            Vertex temp = new Vertex(song);
            temp.head = new Edge(song, artist);
            songs[songInd] = temp;
            status += 2;
            songCount++;
        }
        else {
            Edge temp = songs[songInd].head;
            songs[songInd].head = new Edge(song, artist);
            temp.prev = songs[songInd].head;
            songs[songInd].head.next = temp;
        }
        int artInd = hashNProbe(artists, artist);
        if (artists[artInd] == null) {
            Vertex temp = new Vertex(artist);
            temp.head = new Edge(song, artist);
            artists[artInd] = temp;
            status += 1;
            artCount++;
        }
        else {
            Edge temp = artists[artInd].head;
            artists[artInd].head = new Edge(song, artist);
            temp.prev = artists[artInd].head;
            artists[artInd].head.next = temp;
        }
        return status;
    }
}
