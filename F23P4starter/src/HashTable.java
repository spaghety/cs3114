/**
 * Defines the Hash Table and graph contained within
 * 
 * @author Phillip Jordan (alexj14)
 * @author David (Ta-Jung) Lin (davidsmile)
 * @version 2023.11.28
 *
 */
public class HashTable {
    private Vertex[] songs, artists;
    private int artCount;
    private int songCount;
    private int capTriggerArt;
    private int capTriggerSong;
    private Vertex TOMBSTONE = new Vertex("TOMBSTONE");

    /**
     * Defines graph edges
     * 
     * @author Phillip Jordan (alexj14)
     *
     */
    private class Edge {
        int weight;
        int song;
        int art;
        Edge prev, next;

        /**
         * Edge constructor just takes node value
         * 
         * @param v
         *            artist or song name
         */
        Edge(String s, String a) {
            song = hashNProbe(songs, s);
            art = hashNProbe(artists, a);
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
        artCount = 0;
        songCount = 0;
        capTriggerArt = 0;
        capTriggerSong = 0;
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
     * Checks if the hash tables need to be extended and then does so if
     * necessary
     * 
     * @return 0 if they aren't extended, 1 if only artists, 2 if only songs, 3
     *         if both
     */
    private int checkExtend() {
        int result = 0;
        if (capTriggerArt * 2 > artists.length) {
            Vertex[] temp = artists;
            artists = new Vertex[artists.length * 2];
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] != null) {
                    int newInd = hashNProbe(artists, temp[i].val);
                    artists[newInd] = temp[i];
                }
            }
            result += 1;
        }
        if (capTriggerSong * 2 > songs.length) {
            Vertex[] temp = songs;
            songs = new Vertex[songs.length * 2];
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] != null) {
                    int newInd = hashNProbe(songs, temp[i].val);
                    songs[newInd] = temp[i];
                }
            }
            result += 2;
        }
        return result;
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
                return (int)((init + Math.pow(prober, 2.0)) % arr.length);
            }
            prober++;
            probe = arr[(int)((init + Math.pow(prober, 2.0)) % arr.length)];
        }
        return (int)((init + Math.pow(prober, 2.0)) % arr.length);
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
                if (curr.song == hashNProbe(songs, s)) {
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
            capTriggerSong = Math.max(capTriggerSong, songCount);
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
            capTriggerArt = Math.max(capTriggerArt, artCount);
        }
        else {
            Edge temp = artists[artInd].head;
            artists[artInd].head = new Edge(song, artist);
            artists[artInd].head.next = temp;
            status += 2;
        }
        if (status < 4)
            status += 10 * checkExtend();
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
     *            artist index
     * @param s
     *            song index
     */
    private void removeHelper(boolean isSong, int a, int s) {
        Vertex[] list;
        int index;
        if (isSong) {
            list = songs;
            index = s;
        }
        else {
            list = artists;
            index = a;
        }
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


    private int[] neighbors(boolean isSong, int v) {
        int cnt = 0;
        Edge curr;
        Vertex vx;
        if (isSong)
            vx = songs[v];
        else
            vx = artists[v];
        for (curr = vx.head; curr != null; curr = curr.next) {
            cnt++;
        }
        int[] temp = new int[cnt];
        cnt = 0;
        for (curr = vx.head; curr != null; curr = curr.next) {
            if (isSong) {
                temp[cnt++] = curr.art;
            }
            else {
                temp[cnt++] = curr.song;
            }
        }
        return temp;
    }


    /**
     * Returns printout for graph statistics
     * 
     * @return string to print
     */
    public String printGraph() {
        int maxSize = 0;
        return "There are _ connected components\nThe largest connected component has _ elements\nThe diameter of the largest component is _\n";
    }
}
