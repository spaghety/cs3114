import student.TestCase;
/**
 * test class for BufferPool class
 */
public class BufferPoolTest extends TestCase {
    private BufferPool bp;
    public void setUp() {
        bp = new BufferPool("input.txt", 4);
    }
    
    /**
     * Tests readBlock method
     */
    public void testReadBlock() {
        bp.readBlock(0);
        bp.readBlock(1);
        bp.readBlock(2);
        bp.readBlock(3);
        //bp.readBlock(4);
    }
    
    public void testGetRecord() {
        System.out.println(bp.getRecord(0)[0]);
        bp.getRecord(0);
    }
}
