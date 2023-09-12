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
}
