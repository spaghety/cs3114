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
    private int capTrigger;
    private Vertex TOMBSTONE = new Vertex("TOMBSTONE");

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
        capTrigger = 0;
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
     * Checks if array size needs to be doubled to make more room and doubles if
     * necessary, rehashing old vertices into new array
     */
    private void checkExtend() {
        if (capTrigger * 2 >= size) {
            size *= 2;
            Vertex[] temp = artists;
            artists = new Vertex[size];
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] != null) {
                    int newInd = hashNProbe(artists, temp[i].val);
                    artists[newInd] = temp[i];
                }
            }
            temp = songs;
            songs = new Vertex[size];
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] != null) {
                    int newInd = hashNProbe(songs, temp[i].val);
                    songs[newInd] = temp[i];
                }
            }
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
        Vertex probe = arr[init];
        int prober = 0;
        while (probe != null) {
            if (probe.val.equals(v)) {
                return (int)((init + Math.pow(prober, 2.0)) % size);
            }
            prober++;
            probe = arr[(int)((init + Math.pow(prober, 2.0)) % size)];
        }
        return (int)((init + Math.pow(prober, 2.0)) % size);
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
                if (curr.song.equals(s)) {
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
        if (find(artist, song) != null)
            return 4;
        int songInd = hashNProbe(songs, song);
        int status = 0;
        if (songs[songInd] == null) {
            Vertex temp = new Vertex(song);
            temp.head = new Edge(song, artist);
            songs[songInd] = temp;
            songCount++;
            capTrigger = Math.max(capTrigger, songCount);
        }
        else {
            Edge temp = songs[songInd].head;
            songs[songInd].head = new Edge(song, artist);
            songs[songInd].head.next = temp;
            status += 1;
        }
        int artInd = hashNProbe(artists, artist);
        if (artists[artInd] == null) {
            Vertex temp = new Vertex(artist);
            temp.head = new Edge(song, artist);
            artists[artInd] = temp;
            artCount++;
            capTrigger = Math.max(capTrigger, artCount);
        }
        else {
            Edge temp = artists[artInd].head;
            artists[artInd].head = new Edge(song, artist);
            artists[artInd].head.next = temp;
            status += 2;
        }
        if (status < 4)
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
            if (curr.art.equals(a) && curr.song.equals(s)) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        if (prev == null) {
            list[index] = TOMBSTONE;
            if (isSong)
                songCount--;
            else
                artCount--;
        }
        else {
            if (curr.next != null)
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
            curr = curr.next;
        }
        list[index] = TOMBSTONE;
        if (isSong) {
            songCount--;
            songs = list;
        }
        else {
            artCount--;
            artists = list;
        }
        return true;
    }


    /**
     * Returns printout for list of artists
     * 
     * @return string to print
     */
    public String printArtists() {
        String result = "";
        for (int i = 0; i < artists.length; i++) {
            if (artists[i] != null) {
                result += i + ": |" + artists[i].val + "|\n";
            }
        }
        result += "total artists: " + artCount + "\n";
        return result;
    }


    /**
     * Returns printout for list of songs
     * 
     * @return string to print
     */
    public String printSongs() {
        String result = "";
        for (int i = 0; i < songs.length; i++) {
            if (songs[i] != null) {
                result += i + ": |" + songs[i].val + "|\n";
            }
        }
        result += "total songs: " + songCount + "\n";
        return result;
    }


    /**
     * Returns printout for graph statistics
     * 
     * @return string to print
     */
    public String printGraph() {
        return "";
    }
}
