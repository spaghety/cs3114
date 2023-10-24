import java.nio.ByteBuffer;
import student.TestCase;
/**
 * Tests Block class functionality
 */

public class BlockTest extends TestCase {
    Block block;
    public void setUp() {
        byte[] testData;
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.asShortBuffer().put(new short[] {1, 2});
        block = new Block(0, buffer.array());
    }
    public void testGetRecord() {
        short[] testRecord = block.getRecord(0);
        assertEquals(1, testRecord[0]);
        assertEquals(2, testRecord[1]);
    }
}
