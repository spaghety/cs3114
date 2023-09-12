import student.TestCase;
import static org.junit.Assert.assertArrayEquals;
// import java.util.Arrays;

/**
 * Test class for HashTable
 * 
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.11
 */
public class HashTableTest extends TestCase {
	private HashTable hashTable;
	private static int size = 16;
	private static int memsize = 64;
	private SemRecord[] compare;
	private Seminar sem;
	private int a;
	private int b;

	/**
	 * Sets up the tests that follow.
	 * 
	 * @throws Exception
	 */
	public void setUp() throws Exception {
		compare = new SemRecord[size];
		hashTable = new HashTable(size, new MemManager(memsize));
		a = 5;
		b = 1;
		hashTable.insert(new SemRecord(a, a, size));
		hashTable.insert(new SemRecord(b, b, size));
		compare[a % size] = new SemRecord(a, a, size);
		compare[b % size] = new SemRecord(b, b, size);
		sem = new Seminar(11, "test", "9/6/2023", 10, (short) 2, (short) 6, 100,
				new String[] { "example", "test", "other test string" }, "This is a test seminar for testing.");
	}

	/**
	 * Tests insert() without collision The insertion is already done in setUp()
	 */
	public void testInsertNoCollision() {
		assertArrayEquals(compare, hashTable.getArray());
	}

	/**
	 * Tests the find method
	 */
	public void testFind() {
		assertEquals(a % size, hashTable.find(a));
		assertEquals(b % size, hashTable.find(b));
		assertEquals(-1, hashTable.find(0x6));
	}

	/**
	 * Tests insert() when it's duplicate
	 */
	public void testInsertSame() {
		assertFalse(hashTable.insert(new SemRecord(a, a, size)));
	}

	/**
	 * Tests insert() when there's a collision
	 */
	public void testInsertWithCollision() {
		int c = a + size;
		assertTrue(hashTable.insert(new SemRecord(c, c, size)));
		int h2 = (((c / size) % (size / 2)) * 2) + 1;
		int index = c % size;
		while (compare[index] != null && !compare[index].isTombstone()) {
			index += h2;
			index %= size;
		}
		compare[index] = new SemRecord(c, c, size);
		assertArrayEquals(compare, hashTable.getArray());
	}

	public void testDoubleCap() {
		HashTable fullHashTable = new HashTable(size, new MemManager(memsize));
		int i;
		for (i = 0; i < size; i++) {
			fullHashTable.insert(new SemRecord(i, i, size));
		}
		assertTrue(fullHashTable.insert(new SemRecord(i, i, size)));
	}

	/**
	 * Tests removing an entry and make sure there is a tombstone. Will not remove a
	 * tombstone
	 */
	public void testRemove() {
		assertEquals(-1, hashTable.remove(6));
		assertEquals(a % size, hashTable.remove(a));
		assertEquals(-1, hashTable.remove(a));
		compare[a % size].makeTombstone();
		assertArrayEquals(compare, hashTable.getArray());
		assertEquals(b % size, hashTable.remove(b));
		assertEquals(-1, hashTable.remove(b));
		compare[b % size].makeTombstone();
		assertArrayEquals(compare, hashTable.getArray());
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
	}

	public void testPrintout() {
		assertEquals("\n1\n5", hashTable.printout(true));
		assertEquals("", hashTable.printout(false));
		hashTable.remove(a);
		assertEquals("\n1", hashTable.printout(true));
		assertEquals("", hashTable.printout(false));
	}
}
