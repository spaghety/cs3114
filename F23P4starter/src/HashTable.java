/**
 * Defines the Hash Table and graph contained within
 * @author Phillip Jordan (alexj14)
 *
 */
public class HashTable {
    private Vertex[] songs, artists;
    private int size;

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
     * @author Phillip Jordan (alexj14)
     *
     */
    private class Vertex {
        Edge head;
        String val;
        int artCount;
        int songCount;
        
        /**
         * Constructor initializes the vertex
         * @param v value - either song name or artist name
         */
        public Vertex(String v) {
            val = v;
            head = null;
            artCount = 0;
            songCount = 0;
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
    }

    public  int hashNProbe(Vertex[] arr, String v) {
        int init = Hash.h(v, arr.length);
        Vertex probe = songs[init];
        int prober = 0;
        while (probe != null) {
            if (probe.val == v) {
                return (int) (init+Math.pow((double) prober, 2.0));
            }
            prober++;
            probe = songs[(int)(init+Math.pow(prober, 2.0))];
        }
    }

    /**
     * Insert new record
     * 
     * @param song
     *            song title
     * @param artist
     *            artist name
     * @return exit status: 0=successful insert new artist, 1=already exists,
     *         2=successful insert existing artist
     */
    public int insert(String song, String artist) {
        int songInd = Hash.h(song, size);
        int artInd = Hash.h(artist, size);
        Vertex songProbe = songs[songInd];
        int prober = 0;
        while (songProbe != null) {
            if (songProbe.val == song) {
                songProbe.head ==
                return 1;
            }
            prober++;
            songProbe = songs[(songInd + (int)Math.pow(prober, 2))
                % songs.length];
        }
        songInd = (songInd + (int)Math.pow(prober, 2)) % songs.length;
        songs[songInd] = new Vertex(song);
        Vertex artProbe = artists[artInd];
        prober = 0;
        return 0;
    }
}
