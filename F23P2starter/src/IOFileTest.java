import java.io.FileNotFoundException;
import student.TestCase;

/**
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 */
public class IOFileTest extends TestCase {
    String infile;
    String worldSize;

    public void setUp() {
        worldSize = "128";
        infile = "custom_test.txt";
    }


    public void testRun() {
        try {
            SemSearch.main(new String[] { "128", "custom_test.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
