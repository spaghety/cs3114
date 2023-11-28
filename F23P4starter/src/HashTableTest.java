/**
 * Test class for HashTable class
 * 
 * @author Phillip Jordan (alexj14)
 * @version 2023.11.23
 *
 */
public class HashTableTest extends student.TestCase {
    HashTable ht;

    /**
     * Sets up tests
     */
    public void setUp() {
        ht = new HashTable(10);
    }


    /**
     * Tests simple insert cases
     */
    public void testInsertSimple() {
        assertEquals(0, ht.insert("song1", "artist1"));
        assertEquals(1, ht.artistCount());
        assertEquals(1, ht.songCount());
        assertEquals(0, ht.insert("song2", "artist2"));
        assertEquals(2, ht.artistCount());
        assertEquals(2, ht.songCount());
        assertEquals(4, ht.insert("song2", "artist2"));
    }


    /**
     * Tests more complex insert operations
     */
    public void testInsertGraph() {
        assertEquals(0, ht.insert("song1", "artist1"));
        assertEquals(1, ht.insert("song1", "artist2"));
        assertEquals(2, ht.artistCount());
        assertEquals(1, ht.songCount());
        assertEquals(2, ht.insert("song2", "artist1"));
        assertEquals(2, ht.songCount());
        assertEquals(0, ht.insert("song3", "artist3"));
        assertEquals(3, ht.songCount());
        assertEquals(3, ht.artistCount());
        assertEquals(3, ht.insert("song2", "artist3"));
        assertEquals(3, ht.songCount());
        assertEquals(3, ht.artistCount());
    }


    /**
     * Tests simple remove operations
     */
    public void testRemoveSimple() {
        assertEquals(0, ht.insert("song1", "artist1"));
        assertEquals(1, ht.artistCount());
        assertTrue(ht.remove(false, "artist1"));
        assertFalse(ht.remove(false, "asdf"));
        assertEquals(0, ht.artistCount());
        assertEquals(0, ht.songCount());
        assertEquals(0, ht.insert("song1", "artist1"));
        assertEquals(1, ht.insert("song1", "artist2"));
        assertTrue(ht.remove(true, "song1"));
        assertEquals(0, ht.artistCount());
        assertEquals(0, ht.songCount());
    }


    /**
     * Tests more complex remove operations
     */
    public void testRemoveComplex() {
        ht.insert("song1", "artist1");
        ht.insert("song2", "artist2");
        ht.insert("song3", "artist2");
        ht.insert("song4", "artist2");
        assertTrue(ht.remove(false, "artist2"));
        assertEquals(1, ht.artistCount());
        assertEquals(1, ht.songCount());
        ht.insert("song2", "artist2");
        ht.insert("song3", "artist2");
        ht.insert("song4", "artist2");
        assertTrue(ht.remove(true, "song3"));
        assertTrue(ht.remove(true, "song2"));
        assertEquals(2, ht.artistCount());
        assertEquals(2, ht.songCount());
    }


    /**
     * Tests the ability for the hash table to extend
     */
    public void testExpand() {
        ht.insert("song1", "artist1");
        ht.insert("song1", "artist2");
        ht.insert("song1", "artist3");
        ht.insert("song1", "artist4");
        assertEquals(11, ht.insert("song1", "artist5"));
    }


    /**
     * Tests print methods
     */
    public void testPrint() {
        ht.insert("song1", "artist1");
        ht.insert("song2", "artist2");
        ht.insert("song3", "artist3");
        ht.insert("song4", "artist4");
        assertFuzzyEquals(
            "0: |artist4|\n2: |artist1|\n4: |artist3|\n8: |artist2|\ntotal artists: 4",
            ht.printArtists());
        assertFuzzyEquals(
            "0: |song3|\n1: |song4|\n8: |song1|\n9: |song2|\ntotal songs: 4", ht
                .printSongs());
        ht.remove(false, "artist1");
        assertFuzzyEquals(
            "0: |artist4|\n2: |TOMBSTONE|\n4: |artist3|\n8: |artist2|\ntotal artists: 3",
            ht.printArtists());
    }

}
