import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * Tests SemSearch class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.24
 */
public class SemSearchTest extends TestCase {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Tests insert
     * 
     * @throws FileNotFoundException
     */
    public void testInsert() throws FileNotFoundException {
        System.setOut(new PrintStream(out));
        SemSearch sem = new SemSearch();
        assertNotNull(sem);
        try {
            SemSearch.main(new String[] { "128", "insert.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "FileName: insert.txt\nWorld Size: 128\n";
        assertEquals(content, out.toString());
    }
    
    /**
     * Tests print
     * 
     * @throws FileNotFoundException
     */
    public void testPrint() throws FileNotFoundException {
        System.setOut(new PrintStream(out));
        SemSearch sem = new SemSearch();
        assertNotNull(sem);
        try {
            SemSearch.main(new String[] { "128", "print.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "FileName: print.txt\nWorld Size: 128\n";
        assertEquals(content, out.toString());
    }
    
    /**
     * Tests search location
     * 
     * @throws FileNotFoundException
     */
    public void testSearchLocation() throws FileNotFoundException {
//        SemSearch.main(new String[] { "128", "search location.txt" });
        System.setOut(new PrintStream(out));
        SemSearch sem = new SemSearch();
        assertNotNull(sem);
        try {
            SemSearch.main(new String[] { "128", "search location.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "FileName: search location.txt\nWorld Size: 128\n";
        assertEquals(content, out.toString());
    }
    
    
    /**
     * Tests search keyword
     * 
     * @throws FileNotFoundException
     */
    public void testSearchKeyword() throws FileNotFoundException {
//        SemSearch.main(new String[] { "128", "search keyword.txt" });
        System.setOut(new PrintStream(out));
        SemSearch sem = new SemSearch();
        assertNotNull(sem);
        try {
            SemSearch.main(new String[] { "128", "search keyword.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "FileName: search keyword.txt\nWorld Size: 128\n";
        assertEquals(content, out.toString());
    }
    
    /**
     * Tests search date
     * 
     * @throws FileNotFoundException
     */
    public void testSearchDate() throws FileNotFoundException {
//        SemSearch.main(new String[] { "128", "search date.txt" });
        System.setOut(new PrintStream(out));
        SemSearch sem = new SemSearch();
        assertNotNull(sem);
        try {
            SemSearch.main(new String[] { "128", "search date.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "FileName: search date.txt\nWorld Size: 128\n";
        assertEquals(content, out.toString());
    }
    
    /**
     * Tests search cost
     * 
     * @throws FileNotFoundException
     */
    public void testSearchCost() throws FileNotFoundException {
//        SemSearch.main(new String[] { "128", "search cost.txt" });
        System.setOut(new PrintStream(out));
        SemSearch sem = new SemSearch();
        assertNotNull(sem);
        try {
            SemSearch.main(new String[] { "128", "search cost.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "FileName: search cost.txt\nWorld Size: 128\n";
        assertEquals(content, out.toString());
    }
    
    
    /**
     * Tests search ID
     * 
     * @throws FileNotFoundException
     */
    public void testSearchID() throws FileNotFoundException {
//        SemSearch.main(new String[] { "128", "search ID.txt" });
        System.setOut(new PrintStream(out));
        SemSearch sem = new SemSearch();
        assertNotNull(sem);
        try {
            SemSearch.main(new String[] { "128", "search ID.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "FileName: search ID.txt\nWorld Size: 128\n";
        assertEquals(content, out.toString());
    }
    
    
    /**
     * Tests delete
     * 
     * @throws FileNotFoundException
     */
    public void testdelete() throws FileNotFoundException {
//        SemSearch.main(new String[] { "128", "delete.txt" });
        System.setOut(new PrintStream(out));
        SemSearch sem = new SemSearch();
        assertNotNull(sem);
        try {
            SemSearch.main(new String[] { "128", "delete.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "FileName: delete.txt\nWorld Size: 128\n";
        assertEquals(content, out.toString());
    }
}
