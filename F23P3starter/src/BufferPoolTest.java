import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
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
        bp = new BufferPool("input.txt", 4);
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
        
//        bp.setRecord((long)1, new short[] {65});
//        bp.readBlock(0);
//        assertEquals(4, bp.getBuffersize());
    }


    /**
     * Tests getRecord and setRecord
     */
    public void testGetRecord() {
        short[] record = bp.getRecord(0);
    }


    public void testReadFile() {
        try {
            RandomAccessFile file = new RandomAccessFile("input.txt", "r");
            byte[] tempArr = new byte[2];
            System.out.println(tempArr[0] + ", " + tempArr[1]);
            file.read(tempArr);
            System.out.println(tempArr[0] + ", " + tempArr[1]);
            file.skipBytes(142);
            file.read(tempArr);
            System.out.println(tempArr[0] + ", " + tempArr[1]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
