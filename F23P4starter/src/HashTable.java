/**
 * Defines the Hash Table and graph contained within
 * 
 * @author Phillip Jordan (alexj14)
 * @author David (Ta-Jung) Lin (davidsmile)
 * @version 2023.11.28
 *
 */
public class HashTable {
    private Node[] table;
    private int count;
    private int capTrigger;
    private final Node tombstone = new Node("TOMBSTONE", -1);

    /**
     * This class defines a song or artist node pointing to a vertex in the
     * graph
     * 
     * @author Phillip Jordan (alexj14)
     * @author David (Ta-Jung) Lin (davidsmile)
     * @version 2023.11.28
     */
    private class Node {
        private int head;
        private String val;

        /**
         * Constructor initializes the node
         * 
         * @param v
         *            value - either song name or artist name
         * @param h
         *            index of the corresponding vertex in the graph
         */
        public Node(String v, int h) {
            val = v;
            head = h;
        }
    }

    /**
     * Simple constructor
     * 
     * @param initSize
     *            initial hash table size
     */
    public HashTable(int initSize) {
        table = new Node[initSize];
        count = 0;
        capTrigger = 0;
    }


    /**
     * Gets the number of distinct table entries
     * 
     * @return number of valid table entries
     */
    public int count() {
        return count;
    }


    /**
     * Checks if the hash table need to be extended and then does so if
     * necessary
     * 
     * @return true if extended, false if not
     */
    private boolean checkExtend() {
        if (capTrigger * 2 > table.length) {
            Node[] temp = table;
            table = new Node[table.length * 2];
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] != null) {
                    int newInd = hashNProbe(temp[i].val);
                    table[newInd] = temp[i];
                }
            }
            return true;
        }
        return false;
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
    private int hashNProbe(String v) {
        int init = Hash.h(v, table.length);
        Node probe = table[init];
        int prober = 0;
        while (probe != null && probe != tombstone) {
            if (probe.val.equals(v)) {
                return (int)((init + Math.pow(prober, 2.0)) % table.length);
            }
            prober++;
            probe = table[(int)((init + Math.pow(prober, 2.0)) % table.length)];
        }
        return (int)((init + Math.pow(prober, 2.0)) % table.length);
    }


    /**
     * Insert new entry
     * 
     * @param val
     *            string value of the node being inserted
     * 
     * @param h
     *            index of the head of the corresponding graph vertex
     * 
     * @return exit status: 0=insert new no extension, 1=insert new with
     *         extension, -1=entry exists already
     */
    public int insert(String val, int h) {
//        if (find(val) > -1) {
//            return -1;
//        }
        int ind = hashNProbe(val);
        int status = 0;
        if (table[ind] == null || table[ind] == tombstone) {
            table[ind] = new Node(val, h);
            count++;
            capTrigger = Math.max(capTrigger, count);
            if (checkExtend()) {
                return 1;
            }
            else {
                return 0;
            }
        }
        else {
            return -1;
        }
    }


    /**
     * Find an entry based on the string value and return the corresponding
     * graph index
     * 
     * @param key
     *            string to search for
     * @return index in the graph
     */
    public int find(String key) {
        int ind = hashNProbe(key);
        if (table[ind] == null || table[ind] == tombstone) {
            return -1;
        }
        else {
            return table[ind].head;
        }
    }


    /**
     * Remove a node based on value
     * 
     * @param val
     *            song name or artist name
     * @return true if successful, false if not found
     */
    public boolean remove(String val) {
        int index = hashNProbe(val);
        if (table[index] == null || table[index] == tombstone) {
            return false;
        }
        table[index] = tombstone;
        count--;
        return true;
    }


    /**
     * Remove a node based on the graph vertex it references
     * 
     * @param h
     *            graph vertex index
     * @return true if successful, false if not
     */
    public boolean remove(int h) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i].head == h) {
                table[i] = tombstone;
                count--;
                return true;
            }
        }
        return false;
    }


    /**
     * Returns printout for list of entries
     * 
     * @return string to print
     */
    public String print() {
        String result = "";
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                if (table[i].head == -1) {
                    result += i + ": TOMBSTONE\n";
                }
                else {
                    result += i + ": |" + table[i].val + "|\n";
                }
            }
        }
        return result;
    }
}
