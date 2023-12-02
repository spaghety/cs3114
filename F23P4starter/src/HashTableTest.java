/**
 * Test class for HashTable class
 * 
 * @author Phillip Jordan (alexj14)
 * @author David (Ta-Jung) Lin (davidsmile)
 * @version 2023.11.28
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
        assertEquals(0, ht.insert("song1", 1));
        assertEquals(1, ht.count());
        assertEquals(0, ht.insert("song2", 1));
        assertEquals(2, ht.count());
        assertEquals(-1, ht.insert("song2", 1));
    }


    /**
     * Tests simple remove operations
     */
    public void testRemoveSimple() {
        assertEquals(0, ht.insert("song1", 1));
        assertTrue(ht.remove("song1"));
        assertFalse(ht.remove("asdf"));
        assertEquals(0, ht.count());
        assertEquals(0, ht.insert("song1", 1));
        assertEquals(-1, ht.insert("song1", 1));
        assertTrue(ht.remove("song1"));
        assertEquals(0, ht.count());
    }


    /**
     * Tests the ability for the hash table to extend
     */
    public void testExpand() {
        ht.insert("song1", 1);
        ht.insert("song2", 1);
        ht.insert("song3", 1);
        ht.insert("song4", 1);
        assertEquals(0, ht.insert("song5", 1));
        assertEquals(1, ht.insert("song6", 1));
    }


    /**
     * Tests print methods
     */
    public void testPrint() {
        ht.insert("song1", 1);
        ht.insert("song2", 1);
        ht.insert("song3", 1);
        ht.insert("song4", 1);
        assertFuzzyEquals("0: |song3|\n1: |song4|\n8: |song1|\n9: |song2|\n", ht
            .print());
        ht.remove("song1");
        assertFuzzyEquals("0: |song3|\r\n" + "1: |song4|\r\n"
            + "8: |TOMBSTONE|\r\n" + "9: |song2|\r\n" + "", ht.print());
    }

}
