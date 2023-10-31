import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import student.TestCase;

/**
 * test class for BufferPool class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.25
 */
public class BufferPoolTest extends TestCase {
    private BufferPool bp;

    public void setUp() {
        bp = null;
        RandomAccessFile wraf;
        try {
            wraf = new RandomAccessFile("input.txt", "rw");
            bp = new BufferPool(wraf, 4);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Tests readBlock method
     */
    public void testReadBlock() {
        assertEquals(0, bp.getBuffersize());
        bp.readBlock(0);
        assertEquals(1, bp.getBuffersize());
        bp.readBlock(1);
        assertEquals(2, bp.getBuffersize());
        bp.readBlock(2);
        assertEquals(3, bp.getBuffersize());
        bp.readBlock(3);
        assertEquals(4, bp.getBuffersize());
        bp.readBlock(4); // null Block
        assertEquals(4, bp.getBuffersize());
        bp.readBlock(5000); // null Block
        assertEquals(4, bp.getBuffersize());

// bp.setRecord((long)1, new short[] {65});
// bp.readBlock(0);
// assertEquals(4, bp.getBuffersize());
    }


    /**
     * Tests getRecord and setRecord
     */
    public void testGetSetRecord() {
        short[] record = bp.getRecord(0);
        short[] record2 = bp.getRecord(1);
        bp.swap(0, 1);
        assertEquals(bp.getRecord(1)[0], record[0]);
        assertEquals(bp.getRecord(1)[1], record[1]);
        assertEquals(bp.getRecord(0)[0], record2[0]);
        assertEquals(bp.getRecord(0)[1], record2[1]);
    }
}
