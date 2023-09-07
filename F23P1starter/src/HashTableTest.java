import static org.junit.Assert.assertArrayEquals;
// import java.util.Arrays;
import student.TestCase;

/**
 * Test class for HashTable
 */
public class HashTableTest extends TestCase {
    private HashTable hashTable;
    private static int size = 16;
    private byte[] compare;

    /**
     * Sets up the tests that follow.
     */
    public void setUp() {
        compare = new byte[size];
        hashTable = new HashTable(size, size);
        byte a = 0x1;
        byte b = 0x5;
        hashTable.insert(a);
        hashTable.insert(b);
        compare[a % size] = a;
        compare[b % size] = b;
    }


    /**
     * Tests insert() without collision
     * The insertion is already done in setUp()
     */
    public void testInsertNoCollision() {
        assertArrayEquals(compare, hashTable.getArray());
    }

    /**
     * Tests the find method
     */
    public void testFind() {
        assertEquals(1, hashTable.find(0x1));
        assertEquals(5, hashTable.find(0x5));
        assertEquals(-1, hashTable.find(0x6));
    }

// public void testInsertWithCollision() {
// byte c = 0x10;
// hashTable.insert(c);
// int h2 = (((c / size) % (size / 2)) * 2) + 1;
// compare[(c % size + h2) % size] = c;
// assertArrayEquals(hashTable.getArray(), compare);
// }

    /**
     * Tests insert() when there's a collision
     */
    public void testInsertWithCollision() {
        byte c = 0x11;
        hashTable.insert(c);
        int h2 = (((c / size) % (size / 2)) * 2) + 1;
        int index = c % size;
        while (compare[index] != 0x0) {
            index += h2;
            index %= size;
        }
        compare[index] = c;
        // System.out.println(h2);
        // System.out.println(Arrays.toString(compare));
        assertArrayEquals(compare, hashTable.getArray());
    }


    /**
     * Tests removing an entry and make sure there is a tombstone
     */
    public void testRemove() {
        assertFalse(hashTable.remove(0x6));
        assertTrue(hashTable.remove(0x5));
        assertFalse(hashTable.remove(0x5));
        compare[5] = HashTable.TOMBSTONE;
        assertArrayEquals(compare, hashTable.getArray());
        assertTrue(hashTable.remove(0x1));
        assertFalse(hashTable.remove(0x1));
        compare[1] = HashTable.TOMBSTONE;
        assertArrayEquals(compare, hashTable.getArray());
    }


    /**
     * Tests inserting into where the tombstone is
     */
    public void testInsertAtTombstone() {
        hashTable.remove(0x5);
        byte b = 0x15;
        hashTable.insert(b);
        compare[b % size] = b;
        assertArrayEquals(compare, hashTable.getArray());
    }
}
