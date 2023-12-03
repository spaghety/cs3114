import student.TestCase;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class was designed to test the GraphProject
 *
 * @author Phillip Jordan (alexj14)
 * @author David (Ta-Jung) Lin (davidsmile)
 * @version 2023.11.28
 */
public class GraphProjectTest extends TestCase {
    private GraphProject it = new GraphProject();

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
        it.main(new String[] { "10", "testinput.txt" });
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
        assertEquals("|artist1| is added to the Artist database.\r\n"
            + "|song1| is added to the Song database.\r\n"
            + "|artist2| is added to the Artist database.\r\n"
            + "|song2| is added to the Song database.\r\n"
            + "|artist3| is added to the Artist database.\r\n"
            + "|song3| is added to the Song database.\r\n"
            + "|song3| is removed from the Song database.\r\n"
            + "|artist5| is added to the Artist database.\r\n"
            + "|song5| is added to the Song database.\r\n"
            + "|song6| is added to the Song database.\r\n"
            + "|artist6| is added to the Artist database.\r\n"
            + "|song7| is added to the Song database.\r\n" + "0: TOMBSTONE\r\n"
            + "2: |song5|\r\n" + "3: |song6|\r\n" + "4: |song7|\r\n"
            + "8: |song1|\r\n" + "9: |song2|\r\n" + "total songs: 5\r\n"
            + "2: |artist1|\r\n" + "3: |artist6|\r\n" + "4: TOMBSTONE\r\n"
            + "6: |artist5|\r\n" + "8: |artist2|\r\n" + "total artists: 4\r\n",
            baos.toString());
    }
}
