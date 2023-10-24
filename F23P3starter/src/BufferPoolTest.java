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
    public void testBP() {
        bp.readBlock(0);
    }
}
