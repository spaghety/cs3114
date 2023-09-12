import junit.framework.AssertionFailedError;
import student.TestCase;

/**
 * Unit tests for the SemRecord class
 */

public class SemRecordTest extends TestCase {
    private SemRecord semRecord;
    private int id;
    private int index;
    private int size;

    /**
     * Sets up the tests that follow.
     */
    public void setUp() {
        id = 0;
        index = 1;
        size = 16;
        semRecord = new SemRecord(id, index, size);
    }


    /**
     * Tests all getters
     */
    public void testGetters() {
        assertEquals(id, semRecord.getId());
        assertEquals(index, semRecord.getIndex());
        assertEquals(size, semRecord.getSize());
    }


    /**
     * Tests tombstone feature
     */
    public void testTombstone() {
        assertFalse(semRecord.isTombstone());
        semRecord.makeTombstone();
        assertTrue(semRecord.isTombstone());
    }


    /**
     * Tests equals()
     */
    @SuppressWarnings("unlikely-arg-type")
    public void testEquals() {
        // Wrong object class
        assertFalse(semRecord.equals("Hello"));
        // Sets up other SemRecord objects
        SemRecord semRecord2 = new SemRecord(id + 1, index, size);
        SemRecord semRecord3 = new SemRecord(id + 2, index + 1, size);
        // Regular cases, no tombstone
        assertEquals(semRecord, semRecord2);
        assertNotSame(semRecord, semRecord3);
        // Tombstone cases
        semRecord2.makeTombstone();
        assertNotSame(semRecord, semRecord2);
        assertNotSame(semRecord2, semRecord);
   }
}
