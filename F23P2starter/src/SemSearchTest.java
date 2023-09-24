import java.io.FileNotFoundException;
import student.TestCase;

/**
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.24
 */
public class SemSearchTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     * @throws FileNotFoundException 
     */
    public void testMInitx() throws FileNotFoundException
    {
        SemSearch sem = new SemSearch();
        assertNotNull(sem);
        SemSearch.main(null);
    }
}

