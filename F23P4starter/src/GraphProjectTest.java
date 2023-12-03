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
        assertEquals(
            "|Blind Lemon Jefferson| is added to the Artist database.\r\n"
                + "|Long Lonesome Blues| is added to the Song database.\r\n"
                + "|Blind Lemon Jefferson<SEP>Long Lonesome Blues| duplicates a record already in the database.\r\n"
                + "|Long   Lonesome Blues| is added to the Song database.\r\n"
                + "|long Lonesome Blues| is added to the Song database.\r\n"
                + "|Ma Rainey| is added to the Artist database.\r\n"
                + "|Ma Rainey's Black Bottom| is added to the Song database.\r\n"
                + "|Ma Rainey| is removed from the Artist database.\r\n"
                + "|Ma Rainey| is added to the Artist database.\r\n"
                + "|Mississippi Boweavil Blues| is added to the Song database.\r\n"
                + "|Fixin' To Die Blues| is added to the Song database.\r\n"
                + "Song hash table size doubled.\r\n"
                + "|Kid Rock| is added to the Artist database.\r\n"
                + "|Cowboy| is added to the Song database.\r\n"
                + "|Avenged Sevenfold| is added to the Artist database.\r\n"
                + "|Gunslinger| is added to the Song database.\r\n"
                + "|Metallica| is added to the Artist database.\r\n"
                + "|Nothing Else Matters| is added to the Song database.\r\n"
                + "Artist hash table size doubled.\r\n"
                + "|Tejon Street Corner Thieves| is added to the Artist database.\r\n"
                + "|Whiskey| is added to the Song database.\r\n"
                + "|Polyphia| is added to the Artist database.\r\n"
                + "|Playing God| is added to the Song database.\r\n"
                + "Song hash table size doubled.\r\n"
                + "|MF DOOM| is added to the Artist database.\r\n"
                + "|Coffin Nails| is added to the Song database.\r\n"
                + "|Unholy Confessions| is added to the Song database.\r\n"
                + "0: |Nothing Else Matters|\r\n" + "2: |Cowboy|\r\n"
                + "4: TOMBSTONE\r\n" + "8: |Playing God|\r\n"
                + "10: |Whiskey|\r\n" + "21: |Fixin' To Die Blues|\r\n"
                + "22: |Mississippi Boweavil Blues|\r\n"
                + "24: |Gunslinger|\r\n" + "27: |long Lonesome Blues|\r\n"
                + "33: |Unholy Confessions|\r\n"
                + "35: |Long Lonesome Blues|\r\n" + "37: |Coffin Nails|\r\n"
                + "39: |Long   Lonesome Blues|\r\n" + "total songs: 12\r\n"
                + "0: |Blind Lemon Jefferson|\r\n" + "1: |Kid Rock|\r\n"
                + "4: |Tejon Street Corner Thieves|\r\n" + "5: TOMBSTONE\r\n"
                + "7: |Ma Rainey|\r\n" + "8: |Polyphia|\r\n"
                + "9: |Avenged Sevenfold|\r\n" + "12: |MF DOOM|\r\n"
                + "18: |Metallica|\r\n" + "total artists: 8\r\n"
                + "There are 8 connected components\r\n"
                + "The largest connected component has 4 elements\r\n"
                + "The diameter of the largest component is 3\r\n" + "", baos
                    .toString());
    }
}
