
import student.TestCase;

/**
 * This class tests the methods and functionality of the MemManager class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 *
 */
public class MemManagerTest extends TestCase {

    private MemManager memManager;

    /**
     * Sets up the tests that follow.
     */
    public void setUp() {
        memManager = new MemManager(256);
    }


    /**
     * Tests the insert method
     */
    public void testInsert() {
        String[] tags = new String[] { "tag 1" };
        Seminar semToInsert = new Seminar(11, "test seminar", "9/10/23", 90,
            (short)1, (short)2, 12, tags, "desc");
        SemRecord newRec = null;
        memManager.printFreeBlock();
        try {
            newRec = memManager.insert(semToInsert.serialize(), 11);
        }
        catch (Exception e) {
            fail("error with insertion");
        }
        assertNotNull(newRec);
        assertEquals(0, newRec.getIndex());
        // Try inserting a second record, should take an index in the buddy slot
        // of the previous record
        SemRecord newNewRec = null;
        try {
            newNewRec = memManager.insert(semToInsert.serialize(), 11);
        }
        catch (Exception e) {
            fail("error with inserting second seminar");
        }
        assertNotNull(newNewRec);
        assertEquals(64, newNewRec.getIndex());
        memManager.printFreeBlock();
    }


    /**
     * Checks if the find command can retrieve an identical seminar to the one
     * just inserted just from the handle
     */
    public void testFind() {
        assertNull(memManager.find(null));
        String[] tags = new String[] { "tag 1" };
        Seminar semToInsert = new Seminar(11, "test seminar", "9/10/23", 90,
            (short)1, (short)2, 12, tags, "desc");
        SemRecord record = null;
        try {
            record = memManager.insert(semToInsert.serialize(), 11);
        }
        catch (Exception e) {
            System.out.println("ERROR: Seminar failed to serialize");
            e.printStackTrace();
        }
        assertTrue(semToInsert.toString().equals(memManager.find(record)
            .toString()));
        assertNull(memManager.find(new SemRecord(15, 64, 12)));
    }
    
    /**
     * Tests remove method
     */
    public void testRemove() {
        assertFalse(memManager.remove(null));
        String[] tags = new String[] { "tag 1" };
        Seminar semToInsert = new Seminar(11, "test seminar", "9/10/23", 90,
            (short)1, (short)2, 12, tags, "desc");
        SemRecord record = null;
        try {
            record = memManager.insert(semToInsert.serialize(), 11);
        } catch (Exception e) {
            fail("ERROR: Seminar failed to insert");
        }
        assertTrue(memManager.remove(record));
        assertFalse(memManager.remove(record));
    }
}
