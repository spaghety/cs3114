import student.TestCase;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class was designed to test the GraphProject
 *
 * @author <Put something here>
 * @version <Put something here>
 */
public class GraphProjectTest extends TestCase {
    GraphProject it = new GraphProject();

    // ----------------------------------------------------------
    /**
     * Read contents of a file into a string
     * 
     * @param path
     *            File name
     * @return the string
     * @throws IOException
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }


    /**
     * Set up the tests that follow.
     */
    public void setUp() { // Nothing needed yet
        it = new GraphProject();
    }


    /**
     * This method is simply to get code coverage of the class declaration.
     */
    public void testQInit() {
        // it.main(new String[] { "10", "testinput.txt" });
    }


    /**
     * Tests one input file to output
     */
    public void testCaseA() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(newOut);
        it.main(new String[] { "10", "unitTestCaseA.txt" });
        System.out.flush();
        System.setOut(oldOut);
        assertEquals("|song1| is added to the Artist database.\r\n"
            + "|artist1| is added to the Song database.\r\n"
            + "|song2| is added to the Artist database.\r\n"
            + "|artist2| is added to the Song database.\r\n"
            + "|song3| is added to the Artist database.\r\n"
            + "|artist3| is added to the Song database.\r\n"
            + "|song3| does not exist in the Song database.\r\n"
            + "|song5| is added to the Artist database.\r\n"
            + "|artist5| is added to the Song database.\r\n"
            + "|song6| is added to the Artist database.\r\n"
            + "Artist hash table size doubled.\r\n"
            + "|artist6| is added to the Song database.\r\n"
            + "Song hash table size doubled.\r\n"
            + "|song7| is added to the Artist database.\n", baos.toString());
    }
}
