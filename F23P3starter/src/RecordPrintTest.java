import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import student.TestCase;

/**
 * Prints out file as pairs of shorts
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.31
 */
public class RecordPrintTest extends TestCase {
    private RandomAccessFile raf;

    /**
     * Sets up the tests following
     * 
     * @throws FileNotFoundException
     */
    public void setUp() throws FileNotFoundException {
        raf = new RandomAccessFile("input.txt", "r");
    }


    /**
     * Tests print out
     */
    public void testPrintOut() {
        int i = 0;
        while (true) {
            try {
                System.out.print(i + ". ");
                System.out.print("(" + raf.readShort() + ", ");
                System.out.println(raf.readShort() + ")");
                i++;
            }
            catch (EOFException e) {
                break;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
