/**
 * Defines the Hash Table and graph contained within
 * 
 * @author Phillip Jordan (alexj14)
 *
 */
public class HashTable {
    private Vertex[] songs, artists;
    private int size;
    private int artCount;
    private int songCount;

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
     * Gets the number of distinct artists in the database
     * 
     * @return artist count
     */
    public int artistCount() {
        return artCount;
    }


    /**
     * Gets the number of distinct songs in the database
     * 
     * @return song count
     */
    public int songCount() {
        return songCount;
    }


    /**
     * Checks if array size needs to be doubled to make more room
     */
    private void checkExtend() {
        if (artCount * 2 >= size || songCount * 2 >= size) {
            size *= 2;
            Vertex[] temp = artists;
            artists = new Vertex[size];
            System.arraycopy(temp, 0, artists, 0, artCount);
            temp = songs;
            songs = new Vertex[size];
            System.arraycopy(temp, 0, songs, 0, songCount);
        }
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
    private int hashNProbe(Vertex[] arr, String v) {
        int init = Hash.h(v, arr.length);
        Vertex probe = songs[init];
        int prober = 0;
        while (probe != null) {
            if (probe.val == v) {
                return (int)(init + Math.pow((double)prober, 2.0)) % size;
            }
            prober++;
            probe = songs[(int)(init + Math.pow(prober, 2.0)) % size];
        }
        return (init + (int)Math.pow(prober, 2.0)) % size;
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
        if (artists[ind] != null) {
            Edge curr = artists[ind].head;
            while (curr != null) {
                if (curr.art == a && curr.song == s) {
                    return curr;
                }
                curr = curr.next;
            }
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
            songCount++;
        }
        else {
            Edge temp = songs[songInd].head;
            songs[songInd].head = new Edge(song, artist);
            temp.prev = songs[songInd].head;
            songs[songInd].head.next = temp;
            status += 1;
        }
        int artInd = hashNProbe(artists, artist);
        if (artists[artInd] == null) {
            Vertex temp = new Vertex(artist);
            temp.head = new Edge(song, artist);
            artists[artInd] = temp;
            artCount++;
        }
        else {
            Edge temp = artists[artInd].head;
            artists[artInd].head = new Edge(song, artist);
            temp.prev = artists[artInd].head;
            artists[artInd].head.next = temp;
            status += 2;
        }
        if (status > 0)
            checkExtend();
        return status;
    }


    /**
     * Helper method removes specific edge by artist and song from a specific
     * list
     * 
     * @param isSong
     *            true if removing from song list false if removing from artist
     *            list
     * @param a
     *            artist name
     * @param s
     *            song name
     */
    private void removeHelper(boolean isSong, String a, String s) {
        Vertex[] list;
        String key;
        if (isSong) {
            list = songs;
            key = s;
        }
        else {
            list = artists;
            key = a;
        }
        int index = hashNProbe(list, key);
        Edge prev = null;
        Edge curr = list[index].head;
        while (curr != null) {
            if (curr.art == a && curr.song == s) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        if (prev == null) {
            list[index].head = null;
        }
        else {
            curr.next.prev = prev;
            prev.next = curr.next;
        }
        if (isSong) {
            songs = list;
        }
        else {
            artists = list;
        }
    }


    /**
     * Remove edges based on either artist name or song name
     * 
     * @param isSong
     *            search through song list or artist list
     * @param val
     *            song name or artist name
     * @return true if successful, false if not found
     */
    public boolean remove(boolean isSong, String val) {
        Vertex[] list;
        if (isSong) {
            list = songs;
        }
        else {
            list = artists;
        }
        int index = hashNProbe(list, val);
        if (list[index] == null)
            return false;
        Edge curr = list[index].head;
        while (curr != null) {
            removeHelper(!isSong, curr.art, curr.song);
        }
        list[index] = null;
        if (isSong) {
            songs = list;
        }
        else {
            artists = list;
        }
        return true;
    }
}
