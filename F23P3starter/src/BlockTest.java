import java.nio.ByteBuffer;
import student.TestCase;

/**
 * Tests Block class functionality
 */

public class BlockTest extends TestCase {
    Block block;

    public void setUp() {
        byte[] testData;
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.asShortBuffer().put(new short[] { 1, 2, 3, 4 });
        block = new Block(0, buffer.array());
    }


    public void testGetRecord() {
        short[] testRecord = block.getRecord(0);
        assertEquals(1, testRecord[0]);
        assertEquals(2, testRecord[1]);
    }


    /**
     * Tests setRecord and isDirty methods
     */
    public void testSetRecord() {
        assertFalse(block.isDirty());
        block.setRecord(0, new short[] { 5, 6 });
        short[] testRecord = block.getRecord(0);
        assertEquals(5, testRecord[0]);
        assertEquals(6, testRecord[1]);
        assertTrue(block.isDirty());
        block.setRecord(1, new short[]{8, 9});
        testRecord = block.getRecord(1);
        assertEquals(8, testRecord[0]);
        assertEquals(9, testRecord[1]);
    }


    /**
     * Tests get left bound method
     */
    public void testGetData() {
        byte[] testArray = block.getData();
        assertEquals(1, ByteBuffer.wrap(testArray).getShort(0));
        assertEquals(2, ByteBuffer.wrap(testArray).getShort(2));
    }


    public void testGetLeftBound() {
        assertEquals(0, block.getLeftBound());
    }
}
