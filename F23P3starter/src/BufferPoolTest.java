import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import student.TestCase;

/**
 * Test class for BufferPool class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.25
 */
public class BufferPoolTest extends TestCase {
    private BufferPool bp;

    /**
     * Sets up the tests following
     */
    public void setUp() {
        bp = null;
        RandomAccessFile wraf;
        try {
            wraf = new RandomAccessFile("bufferTest.txt", "rw");
            bp = new BufferPool(wraf, 4);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Tests readBlock method
     * 
     * @throws IOException
     */
    public void testReadBlock() throws IOException {
        assertNotNull(bp.readBlock(0));
    }


    /**
     * Tests writing and flushing
     * 
     * @throws IOException
     */
    public void testWriteToFile() throws IOException {
        bp.setRecord(1050, new short[] { 65, 34 });
// bp.flush();
        assertEquals(65, bp.getRecord(1050)[0]);
        assertEquals(34, bp.getRecord(1050)[1]);
    }


    /**
     * Tests getRecord and setRecord
     * 
     * @throws IOException
     */
    public void testGetSetRecord() throws IOException {
        short[] record = bp.getRecord(0);
        short[] record2 = bp.getRecord(1);
        assertEquals(bp.getRecord(0)[0], record[0]);
        assertEquals(bp.getRecord(0)[1], record[1]);
        assertEquals(bp.getRecord(1)[0], record2[0]);
        assertEquals(bp.getRecord(1)[1], record2[1]);
        bp.swap(0, 1);
        assertEquals(bp.getRecord(1)[0], record[0]);
        assertEquals(bp.getRecord(1)[1], record[1]);
        assertEquals(bp.getRecord(0)[0], record2[0]);
        assertEquals(bp.getRecord(0)[1], record2[1]);
    }
}
