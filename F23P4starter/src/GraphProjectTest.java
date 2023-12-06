import student.TestCase;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class was designed to test the GraphProject
 *
 * @author Phillip Jordan (alexj14)
 * @author David (Ta-Jung) Lin (davidsmile)
 * @version 2023.12.05
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
        GraphProject.main(new String[] { "10", "unitTestCaseA.txt" });
        System.out.flush();
        System.setOut(oldOut);
        assertEquals(
            "|Blind Lemon Jefferson| is added to the Artist database.\n"
                + "|Long Lonesome Blues| is added to the Song database.\n"
                + "|Blind Lemon Jefferson<SEP>Long Lonesome Blues| "
                + "duplicates a record already in the database.\n"
                + "|Long   Lonesome Blues| is added to the Song database.\n"
                + "|long Lonesome Blues| is added to the Song database.\n"
                + "|Ma Rainey| is added to the Artist database.\n"
                + "|Ma Rainey's Black Bottom| is added to the Song database.\n"
                + "|Ma Rainey| is removed from the Artist database.\n"
                + "|Ma Rainey| is added to the Artist database.\n"
                + "|Mississippi Boweavil Blues| "
                + "is added to the Song database.\n"
                + "|Fixin' To Die Blues| is added to the Song database.\n"
                + "Song hash table size doubled.\n"
                + "|Kid Rock| is added to the Artist database.\n"
                + "|Cowboy| is added to the Song database.\n"
                + "|Avenged Sevenfold| is added to the Artist database.\n"
                + "|Gunslinger| is added to the Song database.\n"
                + "|Metallica| is added to the Artist database.\n"
                + "|Nothing Else Matters| is added to the Song database.\n"
                + "Artist hash table size doubled.\n"
                + "|Tejon Street Corner Thieves| "
                + "is added to the Artist database.\n"
                + "|Whiskey| is added to the Song database.\n"
                + "|Polyphia| is added to the Artist database.\n"
                + "|Playing God| is added to the Song database.\n"
                + "Song hash table size doubled.\n"
                + "|MF DOOM| is added to the Artist database.\n"
                + "|Coffin Nails| is added to the Song database.\n"
                + "|Unholy Confessions| is added to the Song database.\n"
                + "0: |Nothing Else Matters|\n" + "2: |Cowboy|\n"
                + "4: TOMBSTONE\n" + "8: |Playing God|\n" + "10: |Whiskey|\n"
                + "21: |Fixin' To Die Blues|\n"
                + "22: |Mississippi Boweavil Blues|\n" + "24: |Gunslinger|\n"
                + "27: |long Lonesome Blues|\n" + "33: |Unholy Confessions|\n"
                + "35: |Long Lonesome Blues|\n" + "37: |Coffin Nails|\n"
                + "39: |Long   Lonesome Blues|\n" + "total songs: 12\n"
                + "0: |Blind Lemon Jefferson|\n" + "1: |Kid Rock|\n"
                + "4: |Tejon Street Corner Thieves|\n" + "5: TOMBSTONE\n"
                + "7: |Ma Rainey|\n" + "8: |Polyphia|\n"
                + "9: |Avenged Sevenfold|\n" + "12: |MF DOOM|\n"
                + "18: |Metallica|\n" + "total artists: 8\n"
                + "There are 8 connected components\n"
                + "The largest connected component has 4 elements\n"
                + "The diameter of the largest component is 3\n" + "", baos
                    .toString());
    }
}
