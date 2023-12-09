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
// System.out.println(ht.print());
    }


    /**
     * Tests simple remove operations
     */
    public void testRemoveSimple() {
        assertEquals(0, ht.insert("song1", 1));
        assertTrue(ht.remove("song1"));
        assertFalse(ht.remove("asdf"));
        assertEquals(0, ht.count());
// System.out.println(ht.print());
        assertEquals(0, ht.insert("song1", 1));
// System.out.println(ht.print());
        assertEquals(-1, ht.insert("song1", 1));
        assertTrue(ht.remove("song1"));
        assertFalse(ht.remove("song1"));
        assertEquals(0, ht.count());
    }


    /**
     * Tests remove and isTombstone
     */
    public void testRemove() {
        ht.insert("A", 1);
        ht.insert("B", 1);
        ht.insert("C", 1);
        ht.insert("D", 1);
        ht.insert("E", 1);
        ht.insert("F", 1);
        ht.insert("G", 1);
        assertFalse(ht.isTombstone(5));
        assertTrue(ht.remove("A"));
        assertEquals(-1, ht.find("A"));
        assertTrue(ht.isTombstone(5));
        assertEquals("5: TOMBSTONE\n6: |B|\n7: |C|\n8: |D|\n9: |E|\n"
            + "10: |F|\n11: |G|\n", ht.print());
        ht.insert("T", 1);
        ht.insert("U", 1);
        assertEquals(0, ht.insert("V", 1));
        assertEquals("4: |T|\n5: |U|\n6: |B|\n7: |C|\n8: |D|\n9: |E|\n"
            + "10: |F|\n11: |G|\n15: |V|\n", ht.print());
        assertTrue(ht.remove("B"));
        assertTrue(ht.isTombstone(6));
        assertEquals("4: |T|\n5: |U|\n6: TOMBSTONE\n7: |C|\n8: |D|\n9: |E|\n"
            + "10: |F|\n11: |G|\n15: |V|\n", ht.print());
        assertEquals(-1, ht.insert("V", 1));
        assertEquals("4: |T|\n5: |U|\n6: TOMBSTONE\n7: |C|\n8: |D|\n9: |E|\n"
            + "10: |F|\n11: |G|\n15: |V|\n", ht.print());
        ht.insert("P", 1);
        ht.remove("P");
        ht.insert("Q", 1);
        ht.remove("Q");
        ht.insert("R", 1);
        ht.remove("R");
        ht.insert("S", 1);
        ht.remove("S");
        System.out.println(ht.print());
        assertEquals(1, ht.find("V"));
        ht.remove("V");
        System.out.println(ht.print());
        assertEquals(-1, ht.find("V"));
        assertFalse(ht.remove("V"));
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
// System.out.println(ht.print());
        assertTrue(ht.remove("R"));
        assertFalse(ht.remove("L"));
// System.out.println(ht.print());
        ht.insert("R", 1);
// System.out.println(ht.print());
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
        assertFuzzyEquals("0: |song3|\n1: |song4|\n8: |TOMBSTONE|\n"
            + "9: |song2|\n", ht.print());
    }

}
