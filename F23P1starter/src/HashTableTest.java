import student.TestCase;
import static org.junit.Assert.assertArrayEquals;
import java.io.ByteArrayOutputStream;
// import java.util.Arrays;
import java.io.PrintStream;

/**
 * Test class for HashTable
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.12
 */
public class HashTableTest extends TestCase {
    private HashTable hashTable;
    private static int size = 16;
    private SemRecord[] compare;
// private Seminar sem;
    private int a;
    private int b;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Sets up the tests that follow.
     * 
     * @throws Exception
     *             from serialization
     */
    public void setUp() throws Exception {
        System.setOut(new PrintStream(out));
        compare = new SemRecord[size];
        hashTable = new HashTable(size);
        a = 5;
        b = 1;
        hashTable.insert(new SemRecord(a, a, size));
        hashTable.insert(new SemRecord(b, b, size));
        compare[a % size] = new SemRecord(a, a, size);
        compare[b % size] = new SemRecord(b, b, size);
        // sem = new Seminar(11, "test", "9/6/2023", 10, (short) 2, (short) 6,
        // 100,
        // new String[] { "example", "test", "other test string" }, "This is a
        // test seminar for testing.");
    }


    /**
     * Tests insert() without collision The insertion is already done in setUp()
     */
    public void testInsertNoCollision() {
        assertArrayEquals(compare, hashTable.getArray());
        assertEquals(2, hashTable.getCount());
    }


    /**
     * Tests insert null
     */
    public void testInsertNull() {
        assertFalse(hashTable.insert(null));
    }
    
    /**
     * Tests the find method
     */
    public void testFind() {
        assertEquals(new SemRecord(a, a, size), hashTable.find(a));
        assertEquals(new SemRecord(b, b, size), hashTable.find(b));
        assertNull(hashTable.find(6));
        hashTable.remove(a);
        assertNull(hashTable.find(a));
    }


    /**
     * Tests insert() when it's duplicate
     */
    public void testInsertSame() {
        assertFalse(hashTable.insert(new SemRecord(a, a, size)));
        assertArrayEquals(compare, hashTable.getArray());
    }


    /**
     * Tests insert() when there's a collision
     */
    public void testInsertWithCollision() {
        int c = a + size;
        assertTrue(hashTable.insert(new SemRecord(c, c, size)));
        int h2 = (((c / size) % (size / 2)) * 2) + 1;
        int index = c % size;
        while (compare[index] != null) {
            index += h2;
            index %= size;
        }
        compare[index] = new SemRecord(c, c, size);
        assertArrayEquals(compare, hashTable.getArray());
    }


    /**
     * Tests doubleCap
     */
    public void testDoubleCap() {
        HashTable fullHashTable = new HashTable(size);
        compare = new SemRecord[size * 2];
        int i;
        for (i = 0; i < size; i++) {
            assertTrue(fullHashTable.insert(new SemRecord(i, i, size)));
            compare[i] = new SemRecord(i, i, size);
        }
        assertArrayEquals(compare, fullHashTable.getArray());
        assertEquals(size, fullHashTable.getCount());
        for (i = 0; i < size; i += 2) {
            fullHashTable.remove(i);
            compare[i] = null; // prepare for expansion: remove tombstones
        }
        assertEquals(size / 2, fullHashTable.getCount());
        SemRecord[] tempArr = new SemRecord[size * 4];
        for (i = 0; i < size; i++) {
            tempArr[i] = compare[i];
        }
        compare = tempArr;
        for (i = size; i < size * 2; i++) {
            assertTrue(fullHashTable.insert(new SemRecord(i, i, size)));
            compare[i] = new SemRecord(i, i, size);
        }
        assertArrayEquals(compare, fullHashTable.getArray());
        assertEquals(size + size / 2, fullHashTable.getCount());
    }


    /**
     * Tests removing an entry and make sure there is a tombstone.
     * Will not remove a tombstone
     */
    public void testRemove() {
        assertNull(hashTable.remove(6));
        assertEquals(new SemRecord(a, a, size), hashTable.remove(a));
        assertNull(hashTable.remove(a));
        compare[a % size].makeTombstone();
        assertArrayEquals(compare, hashTable.getArray());
        assertEquals(1, hashTable.getCount());
        assertEquals(new SemRecord(b, b, size), hashTable.remove(b));
        assertNull(hashTable.remove(b));
        compare[b % size].makeTombstone();
        assertArrayEquals(compare, hashTable.getArray());
        assertEquals(0, hashTable.getCount());
    }


    /**
     * Tests remove for mutations
     */
    public void testRemove2() {
        compare = new SemRecord[size * 2];
        for (int i = 0; i < size; i++) {
            hashTable.insert(new SemRecord(i, i, size));
            compare[i] = new SemRecord(i, i, size);
        }
        assertEquals(size, hashTable.getCount());
        assertArrayEquals(compare, hashTable.getArray());
        for (int i = 0; i < size; i += 2) {
            compare[i].makeTombstone();
            assertEquals(compare[i], hashTable.remove(i));
            assertNull(hashTable.remove(i));
        }
        assertEquals(size / 2, hashTable.getCount());
        assertArrayEquals(compare, hashTable.getArray());
        assertNull(hashTable.remove(6556));
    }
    
    /**
     * Tests remove and find for empty
     */
    public void testRemoveFindEmpty() {
        HashTable ht = new HashTable(2);
        assertNull(ht.remove(a));
        assertNull(ht.remove(b));
        assertNull(ht.find(a));
        assertNull(ht.find(b));
    }


    /**
     * Tests inserting into where the tombstone is
     */
    public void testInsertAtTombstone() {
        hashTable.remove(b);
        int d = b + size;
        assertTrue(hashTable.insert(new SemRecord(d, d, size)));
        compare[d % size] = new SemRecord(d, d, size);
        assertArrayEquals(compare, hashTable.getArray());
        assertEquals(2, hashTable.getCount());
    }


    /**
     * Tests the printout
     */
    public void testPrintout() {
        hashTable.remove(b);
        compare[b % size].makeTombstone();
        assertTrue(hashTable.printout());
        assertEquals("1: TOMBSTONE\n5: 5\ntotal records: 1\n", out.toString());
        assertArrayEquals(compare, hashTable.getArray());
    }
}
