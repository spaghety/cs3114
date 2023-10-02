import java.io.FileNotFoundException;
import student.TestCase;

/**
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.02
 */
public class IOFileTest extends TestCase {
    private String infile;
    private String worldSize;

    /**
     * Sets up the tests
     */
    public void setUp() {
        worldSize = "128";
        infile = "custom_test.txt";
    }

    /**
     * Tests running the file
     */
    public void testRun() {
        try {
            SemSearch.main(new String[] { worldSize, infile });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
