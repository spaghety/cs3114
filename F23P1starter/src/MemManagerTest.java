import student.TestCase;

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
        String[] tags = new String[] {"tag 1"};
        Seminar semToInsert = new Seminar(11, "test seminar", "9/10/23", 90, (short) 1, (short) 2, 12, tags, "desc");
        SemRecord newRec = null;
        memManager.printFreeBlock();
        try {
            System.out.printf("\nthe serialized object is %d long", semToInsert.serialize().length);
        } catch(Exception e) {
            fail("Could not serialize object");
        }
        try {
            newRec = memManager.insert(semToInsert.serialize(), 11);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("error with insertion");
        }
        assertNotNull(newRec);
        memManager.printFreeBlock();
    }
}
