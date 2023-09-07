import static org.junit.Assert.assertArrayEquals;
import student.TestCase;

/**
 * 
 */
public class HashTableTest extends TestCase {
    private HashTable hashTable;
    private static int size = 8;
    private byte[] compare;

    /**
     * Sets up the tests that follow.
     */
    public void setUp() {
        compare = new byte[size * 4];
        hashTable = new HashTable(size * 4, size);
    }


    public void testInsertNoCollision() {
        byte a = 0x0;
        byte b = 0x5;
        hashTable.insert(a);
        hashTable.insert(b);
        compare[a % size] = a;
        compare[b % size] = b;
        assertArrayEquals(hashTable.getArray(), compare);
    }

    public void testInsertWithCollision() {
        byte c = 0x8;
        hashTable.insert(c);
        int h2 = (((c / size) % (size / 2)) * 2) + 1;
        compare[(c % size + h2) % size] = c;
        assertArrayEquals(hashTable.getArray(), compare);
    }
    
    public void testInsertDoubleCollision() {
        byte c = 0xD;
        hashTable.insert(c);
        int h2 = (((c / size) % (size / 2)) * 2) + 1;
        int index = c % size;
        while (compare[index] != 0x0) {
            index += h2;
            index %= size;
        }
        System.out.println(index);
        compare[index] = c;
        assertArrayEquals(hashTable.getArray(), compare);
    }
}
