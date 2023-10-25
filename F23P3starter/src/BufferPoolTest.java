import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
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
        short[] record = bp.getRecord(0);
    }
    
    /*public void testReadFile() {
        try {
            RandomAccessFile file = new RandomAccessFile("input.txt", "r");
            byte[] tempArr = new byte[2];
            System.out.println(tempArr[0]+", "+tempArr[1]);
            file.read(tempArr);
            System.out.println(tempArr[0]+", "+tempArr[1]);
            file.skipBytes(142);
            file.read(tempArr);
            System.out.println(tempArr[0]+", "+tempArr[1]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
