import java.io.FileNotFoundException;
import student.TestCase;
import org.junit.Test;

/**
 * This class is almost identical to SemManagerTest but uses a specific file for testing all of the parsable commands
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.05
 */
public class SemParserTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     * @throws Exception 
     */
    public void testMInitx() throws Exception {
        SemManager sem = new SemManager();
        assertNotNull(sem);
        try {
            SemManager.main(new String[] { "64", "4", "parseTester.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
