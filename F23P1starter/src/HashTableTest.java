import static org.junit.Assert.assertArrayEquals;
import java.util.Arrays;
import student.TestCase;

/**
 * 
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


    public void testInsertNoCollision() {
        // Stuff is already inserted so no need to do more!
        // System.out.println(Arrays.toString(compare));
        assertArrayEquals(compare, hashTable.getArray());
    }
    
    public void testFind() {
        assertEquals(1, hashTable.find(0x1));
        assertEquals(5, hashTable.find(0x5));
        assertEquals(-1, hashTable.find(0x6));
    }

//    public void testInsertWithCollision() {
//        byte c = 0x10;
//        hashTable.insert(c);
//        int h2 = (((c / size) % (size / 2)) * 2) + 1;
//        compare[(c % size + h2) % size] = c;
//        assertArrayEquals(hashTable.getArray(), compare);
//    }
    
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
        System.out.println(Arrays.toString(compare));
        assertArrayEquals(compare, hashTable.getArray());
    }
}
