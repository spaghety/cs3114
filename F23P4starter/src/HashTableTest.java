/**
 * Test class for HashTable class
 * 
 * @author Phillip Jordan (alexj14)
 * @author David (Ta-Jung) Lin (davidsmile)
 * @version 2023.12.11
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
     * Tests simple remove operations
     */
    public void testRemoveSimple2() {
        assertEquals(0, ht.insert("A", 1));
        assertEquals(0, ht.insert("B", 1));
        assertEquals(0, ht.insert("C", 1));
        assertEquals(0, ht.insert("D", 1));
        assertEquals(0, ht.insert("E", 1));
// System.out.println(ht.print());

        assertTrue(ht.remove("A"));
        assertFalse(ht.remove("A"));
        assertFalse(ht.remove("asdf"));
        assertEquals(4, ht.count());
// System.out.println(ht.print());
        assertTrue(ht.remove("C"));
        assertTrue(ht.remove("E"));
        assertTrue(ht.remove("D"));
        assertTrue(ht.remove("B"));
// System.out.println(ht.print());
        assertEquals(0, ht.count());
        assertEquals(0, ht.insert("F", 1));
        assertEquals(0, ht.insert("G", 1));
        assertEquals(0, ht.insert("H", 1));
        assertEquals(0, ht.insert("I", 1));
        assertEquals(0, ht.insert("J", 1));
        assertTrue(ht.remove("F"));
        assertTrue(ht.remove("G"));
        assertTrue(ht.remove("H"));
        assertTrue(ht.remove("I"));
        assertTrue(ht.remove("J"));
// System.out.println(ht.print());

        assertEquals(0, ht.insert("K", 1));
        assertEquals(0, ht.insert("L", 1));
        assertEquals(0, ht.insert("M", 1));
        assertEquals(0, ht.insert("N", 1));
        assertEquals(0, ht.insert("O", 1));
// System.out.println(ht.print());
        assertFalse(ht.remove("A"));
        assertFalse(ht.remove("B"));
        assertFalse(ht.remove("C"));
        assertFalse(ht.remove("D"));
        assertFalse(ht.remove("E"));
        assertTrue(ht.remove("K"));
        assertTrue(ht.remove("L"));
        assertTrue(ht.remove("M"));
        assertTrue(ht.remove("N"));
        assertTrue(ht.remove("O"));
// System.out.println(ht.print());

        assertEquals(0, ht.insert("P", 1));
        assertEquals(0, ht.insert("Q", 1));
        assertEquals(0, ht.insert("R", 1));
        assertEquals(0, ht.insert("S", 1));
        assertEquals(0, ht.insert("T", 1));
// System.out.println(ht.print());
        assertFalse(ht.remove("F"));
        assertFalse(ht.remove("G"));
        assertFalse(ht.remove("H"));
        assertFalse(ht.remove("I"));
        assertFalse(ht.remove("J"));
        assertEquals(5, ht.count());
    }


    /**
     * Tests remove
     */
    public void testRemoveCollision() {
        assertEquals(0, ht.insert("F", 1));
        assertEquals(0, ht.insert("P", 1));
        assertEquals(0, ht.insert("Z", 1));
        assertEquals(0, ht.insert("d", 1));
        assertEquals(0, ht.insert("n", 1));
// System.out.println(ht.print());
        assertTrue(ht.remove("F"));
        assertTrue(ht.remove("n"));
// System.out.println(ht.print());
        assertEquals(0, ht.insert("n", 1));
        assertEquals(0, ht.insert("F", 1));
// System.out.println(ht.print());
        assertTrue(ht.remove("F"));
        assertTrue(ht.remove("n"));
// System.out.println(ht.print());
        assertEquals(0, ht.insert("x", 1));
        assertEquals(0, ht.insert("n", 1));
// System.out.println(ht.print());
        assertTrue(ht.remove("P"));
        assertTrue(ht.remove("Z"));
        assertTrue(ht.remove("d"));
// System.out.println(ht.print());
        assertEquals(0, ht.insert("B", 1));
        assertEquals(0, ht.insert("L", 1));
        assertEquals(0, ht.insert("V", 1));
// System.out.println(ht.print());
        assertTrue(ht.remove("x"));
        ht.insert("A", 1);
// System.out.println(ht.print());
        assertTrue(ht.remove("V"));
        assertTrue(ht.remove("L"));
        assertTrue(ht.remove("B"));
// System.out.println(ht.print());
        assertTrue(ht.remove("n"));
// System.out.println(ht.print());
        ht.insert("n", 1);
// System.out.println(ht.print());
        assertTrue(ht.remove("n"));
        ht.insert("K", 1);
        ht.insert("U", 1);
        ht.insert("_", 1);
        ht.insert("@", 1);
// System.out.println(ht.print());
        assertTrue(ht.remove("_"));
        assertTrue(ht.remove("K"));
        assertTrue(ht.remove("U"));
        assertTrue(ht.remove("@"));
        assertTrue(ht.remove("A"));
// System.out.println(ht.print());
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
// System.out.println(ht.print());
        assertEquals(1, ht.find("V"));
        ht.remove("V");
// System.out.println(ht.print());
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
        assertTrue(ht.remove("R"));
        assertFalse(ht.remove("L"));
        ht.insert("R", 1);
    }


    /**
     * Tests the ability for the hash table to extend
     */
    public void testRemoveExpand() {
        ht.insert("F", 1);
        ht.insert("G", 1);
        ht.insert("H", 1);
        ht.insert("I", 1);
        ht.insert("J", 1);
        assertTrue(ht.remove("F"));
        assertTrue(ht.remove("G"));
        assertTrue(ht.remove("H"));
        assertTrue(ht.remove("I"));
        assertEquals(
            "0: TOMBSTONE\n1: TOMBSTONE\n2: TOMBSTONE\n3: TOMBSTONE\n4: |J|\n",
            ht.print());
        System.out.println(ht.print());
        ht.insert("K", 1);
        ht.insert("L", 1);
        ht.insert("M", 1);
        assertEquals(0, ht.insert("N", 1));
        assertEquals(1, ht.insert("O", 1));
        assertEquals("14: |J|\n15: |K|\n16: |L|\n17: |M|\n18: |N|\n19: |O|\n",
            ht.print());
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
        assertFuzzyEquals("0: |song3|\n1: |song4|\n8: |TOMBSTONE|\n"
            + "9: |song2|\n", ht.print());
    }

}
