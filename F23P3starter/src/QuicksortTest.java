import student.TestCase;

/**
 * Tests the Quicksort class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.16
 */
public class QuicksortTest extends TestCase {
    private CheckFile fileChecker;

    /**
     * Sets up the tests that follow. In general, used for initialization.
     */
    public void setUp() {
        fileChecker = new CheckFile();
    }


    /**
     * This method is a demonstration of the file generator and file checker
     * functionality. It calles generateFile to create a small "ascii" file.
     * It then calls the file checker to see if it is sorted (presumably not
     * since we don't call a sort method in this test, so we assertFalse).
     *
     * @throws Exception
     *             either a IOException or FileNotFoundException
     */
    public void testFileGenerator() throws Exception {
        String[] args = new String[4];
        args[0] = "";
        args[1] = "input.txt";
        args[2] = "2";
        args[3] = "statFile.txt";
        Quicksort.generateFile("input.txt", "3", 'a');
        // In a real test we would call the sort
        Quicksort.main(args);
        // In a real test, the following would be assertTrue()
        assertTrue(fileChecker.checkFile("input.txt"));
    }

    /**
     * Get code coverage of the class declaration.
     * 
     */
    /*
     * public void testQInit() {
     * Quicksort tree = new Quicksort();
     * assertNotNull(tree);
     * Quicksort.main(new String[] { "", "input.txt", "3", "stat.txt" });
     * }
     */
}
