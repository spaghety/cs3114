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
//    private GraphProject it = new GraphProject();

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
    public void setUp() {
        new GraphProject();
    }


    /**
     * This method tests the sample input file
     */
    public void testSampleInupt() {
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(baos2);
        PrintStream oldOut = System.out;
        System.setOut(newOut);
        GraphProject.main(new String[] { "10", "P4sampleInput.txt" });
        System.out.flush();
        System.setOut(oldOut);
        assertEquals(
            "|When Summer's Through| does not exist in the Song database.\n"
                + "total songs: 0\n" + "total artists: 0\n"
                + "There are 0 connected components\n"
                + "The largest connected component has 0 elements\n"
                + "The diameter of the largest component is 0\n"
                + "|Blind Lemon Jefferson| is added to the Artist database.\n"
                + "|Long Lonesome Blues| is added to the Song database.\n"
                + "|Blind Lemon Jefferson<SEP>Long Lonesome Blues| duplicates "
                + "a record already in the database.\n"
                + "|Long   Lonesome Blues| is added to the Song database.\n"
                + "|long Lonesome Blues| is added to the Song database.\n"
                + "|Ma Rainey| is added to the Artist database.\n"
                + "|Ma Rainey's Black Bottom| is added to the Song database.\n"
                + "|Mississippi Boweavil Blues| is added to the Song "
                + "database.\n" + "Song hash table size doubled.\n"
                + "|Fixin' To Die Blues| is added to the Song database.\n"
                + "0: |Blind Lemon Jefferson|\n" + "7: |Ma Rainey|\n"
                + "total artists: 2\n" + "1: |Fixin' To Die Blues|\n"
                + "2: |Mississippi Boweavil Blues|\n"
                + "7: |long Lonesome Blues|\n" + "15: |Long Lonesome Blues|\n"
                + "16: |Ma Rainey's Black Bottom|\n"
                + "19: |Long   Lonesome Blues|\n" + "total songs: 6\n"
                + "There are 1 connected components\n"
                + "The largest connected component has 8 elements\n"
                + "The diameter of the largest component is 4\n"
                + "|Sleepy| does not exist in the Song database.\n"
                + "|ma rainey| does not exist in the Artist database.\n"
                + "|Ma Rainey| is removed from the Artist database.\n"
                + "0: |Blind Lemon Jefferson|\n" + "7: TOMBSTONE\n"
                + "total artists: 1\n", baos2.toString());
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
            + "|Blind Lemon Jefferson<SEP>Long Lonesome Blues| duplicates a record already in the database.\n"
            + "|Long   Lonesome Blues| is added to the Song database.\n"
            + "|long Lonesome Blues| is added to the Song database.\n"
            + "|Ma Rainey| is added to the Artist database.\n"
            + "|Ma Rainey's Black Bottom| is added to the Song database.\n"
            + "|Ma Rainey| is removed from the Artist database.\n"
            + "|Ma Rainey| is added to the Artist database.\n"
            + "|Mississippi Boweavil Blues| is added to the Song database.\n"
            + "|Fixin' To Die Blues| is added to the Song database.\n"
            + "|Kid Rock| is added to the Artist database.\n"
            + "Song hash table size doubled.\n"
            + "|Cowboy| is added to the Song database.\n"
            + "|Avenged Sevenfold| is added to the Artist database.\n"
            + "|Gunslinger| is added to the Song database.\n"
            + "|Metallica| is added to the Artist database.\n"
            + "|Nothing Else Matters| is added to the Song database.\n"
            + "Artist hash table size doubled.\n"
            + "|Tejon Street Corner Thieves| is added to the Artist database.\n"
            + "|Whiskey| is added to the Song database.\n"
            + "|Polyphia| is added to the Artist database.\n"
            + "|Playing God| is added to the Song database.\n"
            + "|MF DOOM| is added to the Artist database.\n"
            + "Song hash table size doubled.\n"
            + "|Coffin Nails| is added to the Song database.\n"
            + "|Unholy Confessions| is added to the Song database.\n"
            + "|Coffin Nails| is removed from the Song database.\n"
            + "0: |Nothing Else Matters|\n"
            + "2: |Cowboy|\n"
            + "8: |Playing God|\n"
            + "10: |Whiskey|\n"
            + "21: |Fixin' To Die Blues|\n"
            + "22: |Mississippi Boweavil Blues|\n"
            + "24: |Gunslinger|\n"
            + "27: |long Lonesome Blues|\n"
            + "33: |Unholy Confessions|\n"
            + "35: |Long Lonesome Blues|\n"
            + "37: TOMBSTONE\n"
            + "39: |Long   Lonesome Blues|\n"
            + "total songs: 11\n"
            + "0: |Blind Lemon Jefferson|\n"
            + "1: |Kid Rock|\n"
            + "4: |Tejon Street Corner Thieves|\n"
            + "7: |Ma Rainey|\n"
            + "8: |Polyphia|\n"
            + "9: |Avenged Sevenfold|\n"
            + "12: TOMBSTONE\n"
            + "18: |Metallica|\n"
            + "total artists: 7\n"
            + "There are 8 connected components\n"
            + "The largest connected component has 4 elements\n"
            + "The diameter of the largest component is 3\n", baos
                    .toString());
    }
    
    /**
     * Tests case B
     */
    public void testCaseB() {
        ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(baos3);
        PrintStream oldOut = System.out;
        System.setOut(newOut);
        GraphProject.main(new String[] { "5", "unitTestCaseB.txt" });
        System.out.flush();
        System.setOut(oldOut);
        assertEquals(
            "|Aart| is added to the Artist database.\n"
            + "|song| is added to the Song database.\n"
            + "|Bart| is added to the Artist database.\n"
            + "Artist hash table size doubled.\n"
            + "|Cart| is added to the Artist database.\n"
            + "|Dart| is added to the Artist database.\n"
            + "|Eart| is added to the Artist database.\n"
            + "Artist hash table size doubled.\n"
            + "|Fart| is added to the Artist database.\n"
            + "|Gart| is added to the Artist database.\n"
            + "|Hart| is added to the Artist database.\n"
            + "|Iart| is added to the Artist database.\n"
            + "|Jart| is added to the Artist database.\n"
            + "Artist hash table size doubled.\n"
            + "|Kart| is added to the Artist database.\n"
            + "|Lart| is added to the Artist database.\n"
            + "|Mart| is added to the Artist database.\n"
            + "|Nart| is added to the Artist database.\n"
            + "|Oart| is added to the Artist database.\n"
            + "|Part| is added to the Artist database.\n"
            + "|Qart| is added to the Artist database.\n"
            + "|Rart| is added to the Artist database.\n"
            + "|Sart| is added to the Artist database.\n"
            + "|Tart| is added to the Artist database.\n"
            + "Artist hash table size doubled.\n"
            + "|Uart| is added to the Artist database.\n"
            + "|do this| ", baos3.toString());
    }
}
