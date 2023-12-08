/**
 * Test class for HashTable class
 * 
 * @author Phillip Jordan (alexj14)
 * @author David (Ta-Jung) Lin (davidsmile)
 * @version 2023.11.28
 *
 */
public class HashTableTest extends student.TestCase {
    private HashTable ht;

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
        System.out.println(ht.print());
        assertEquals(0, ht.insert("song1", 1));
        System.out.println(ht.print());
        assertEquals(-1, ht.insert("song1", 1));
        assertTrue(ht.remove("song1"));
        assertFalse(ht.remove("song1"));
        assertEquals(0, ht.count());
    }


    /**
     * Tests remove(int) and find(String) operations
     */
    public void testRemoveFind() {
        assertEquals(-1, ht.find("Q"));
        assertEquals(0, ht.insert("Q", 1));
        assertEquals(0, ht.insert("W", 2));
        assertEquals(0, ht.insert("E", 3));
        assertEquals(0, ht.insert("R", 4));
        assertEquals(0, ht.insert("T", 5));
        assertTrue(ht.remove(3));
        assertFalse(ht.remove(3));
        assertFalse(ht.remove(9));
        assertEquals(4, ht.count());
        assertEquals(-1, ht.find("E"));
        assertEquals(5, ht.find("T"));
    }


    /**
     * Tests the ability for the hash table to extend
     */
    public void testExpand() {
        ht.insert("Q", 1);
        ht.insert("W", 1);
        ht.insert("E", 1);
        ht.insert("R", 1);
        assertEquals(0, ht.insert("T", 1));
        assertEquals(1, ht.insert("Y", 1));
        ht.insert("U", 1);
        ht.insert("I", 1);
        ht.insert("O", 1);
        System.out.println(ht.print());
        assertTrue(ht.remove("R"));
        assertFalse(ht.remove("L"));
        System.out.println(ht.print());
        ht.insert("R", 1);
        System.out.println(ht.print());
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
