
/**
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.05
 */

// import java.util.Arrays;

/**
 * This class sets up the hash table storing all the seminars.
 */
public class HashTable {
// public static SemRecord TOMBSTONE = new SemRecord(0, 0, 0);
	private SemRecord[] records;
// private FreeBlock[] freespace;
	private int count;
	private MemManager mm;

	/**
	 * Constructor
	 * 
	 * @param hashsize the number of slots to start with initially (may increase
	 *                 with time) will always be a power of 2.
	 * @param memMgr   the memory manager object to call commands from
	 */
	public HashTable(int hashsize, MemManager memMgr) {
		mm = memMgr;
		records = new SemRecord[hashsize];
		count = 0;
	}

	/**
	 * Doubles the capacity in case maximum capacity is reached.
	 */
	private void doubleCap() {
		SemRecord[] tempArr = records;
		records = new SemRecord[records.length * 2];
		for (int i = 0; i < records.length; i++) {
			if (tempArr[i] != null) {
				records[hash(tempArr[i].getId())] = tempArr[i];
			}
		}
		return;
	}

	/**
	 * Returns the hash destination.
	 * 
	 * @param id id of the object being entered in.
	 * @return the hashed id to be used as an index.
	 */
	private int hash(int id) {
		int index = id % records.length;
		int h2 = (((id / records.length) % (records.length / 2)) * 2) + 1;
		while (records[index] != null && !records[index].isTombstone()) {
			index += h2;
			index %= records.length;
		}
		return index;
	}

	/**
	 * Inserts new item into the hash table.
	 * 
	 * @param ref the handle to the location of the object in the memory manager
	 *            returned by the memory manager insert method
	 * @return true if successful, false if not
	 */
	public boolean insert(SemRecord ref) {
		if (this.find(ref.getId()) != null) {
			return false;
		}
		records[hash(ref.getId())] = ref;
		count++;
		if (count * 2 >= records.length) {
			doubleCap();
		}
		return true;
	}

	/**
	 * Removes items from the hash table by id.
	 * 
	 * @param id id of the item to be removed.
	 * @return a reference to the data in the memory manager
	 */
	public int remove(int id) {
		for (int i = 0; i < records.length; i++) {
			if (records[i] instanceof SemRecord) {
				if (records[i].getId() == id) {
					if (records[i].isTombstone()) {
						return -1;
					}
					records[i].makeTombstone();
					count--;
					return records[i].getIndex();
				}
			}
		}
		return -1;
	}

	/**
	 * Finds an item in the hash table by id.
	 * 
	 * @param id id of the item to be found.
	 * @return memory manager handle or null if it could not be found
	 */
	public SemRecord find(int id) {
		for (int i = 0; i < records.length; i++) {
			if (records[i] instanceof SemRecord) {
				if (records[i].getId() == id) {
					return records[i];
				}
			}
		}
		return null;
	}

	/**
	 * Gets the underlying array.
	 * 
	 * @return The underlying array
	 */
	public SemRecord[] getArray() {
		return records;
	}

	/**
	 * Prints out either the free blocks of space in the memory or the contents of
	 * the hash table by id and index.
	 * 
	 * @param type true if printing out the hash table, false if printing out free
	 *             memory blocks.
	 * @return the string for the parser to print.
	 */
	public String printout(boolean type) {
		StringBuilder result = new StringBuilder();
		if (type) {
			for (int i = 0; i < records.length; i++) {
				if (records[i] != null && !records[i].isTombstone()) {
					result.append("\n" + i);
				}
			}
		}
		return result.toString();
	}

}
