import student.TestCase;

/**
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.05
 */
public class SemManagerTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     */
    public void testMInitx() {
        SemManager sem = new SemManager();
        assertNotNull(sem);
        SemManager.main(new String[] { "64", "4", "P1Sample_input.txt" });
    }
}
