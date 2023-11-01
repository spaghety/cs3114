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
            bp = new BufferPool(wraf, 2);
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
        assertEquals(8259, bp.readBlock(0).getRecord(64)[0]);
    }


    /**
     * Tests writing and flushing
     * 
     * @throws IOException
     */
    public void testWriteToFile() throws IOException {
        bp.setRecord(1100, new short[] { 65, 34 });
        bp.flush();
        assertEquals(65, bp.getRecord(1100)[0]);
        assertEquals(34, bp.getRecord(1100)[1]);
        bp.setRecord(1100, new short[] { 8269, 8224 });
        assertEquals(8269, bp.getRecord(1100)[0]);
        assertEquals(8224, bp.getRecord(1100)[1]);
        bp.flush();
        // Test natural flush process
        bp.setRecord(80, new short[] { 65, 34 });
        bp.getRecord(1100);
        bp.getRecord(2050);
        assertEquals(65, bp.readBlock(0).getRecord(80)[0]);
        bp.setRecord(80, new short[] { 8259, 8224 });
        bp.flush();
        bp.setRecord(1189, new short[] { 72, 34 });
        bp.getRecord(2500);
        bp.getRecord(0);
        assertEquals(72, bp.readBlock(1).getRecord(165)[0]);
        bp.setRecord(1189, new short[] { 8264, 8224 });
        bp.flush();
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
