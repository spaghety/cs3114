import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;
import org.junit.Test;

/**
 * This class is almost identical to SemManagerTest but uses a specific file for
 * testing all of the parsable commands
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.05
 */
public class SemParserTest extends TestCase {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     * 
     * @throws Exception
     */
    public void testMInitx() throws Exception {
        SemManager sem = new SemManager();
        assertNotNull(sem);
        try {
            SemManager.main(new String[] { "64", "4", "parseTester.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    /**
     * Tests if output matches for duplicate insert
     * 
     * @throws Exception
     */
    public void testDuplicateInsert() throws Exception {
        System.setOut(new PrintStream(out));
        try {
            SemManager.main(new String[] { "512", "4", "insertTest.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "Successfully inserted record with ID 3\n"
            + "ID: 3, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an overview of "
            + "HCI research at\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "Size: 170\n"
            + "Insert FAILED - There is already a record with ID 3\n";
        assertEquals(content, out.toString());
    }


    /**
     * Tests if output matches for search not found
     * 
     * @throws Exception
     */
    public void testSearch() throws Exception {
        System.setOut(new PrintStream(out));
        try {
            SemManager.main(new String[] { "512", "4", "searchTest.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "Successfully inserted record with ID 3\n"
            + "ID: 3, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an overview of "
            + "HCI research at\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "Size: 170\n" + "Search FAILED -- There is no record with ID 1\n"
            + "Found record with ID 3:\n" + "ID: 3, Title: Overview of "
            + "HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an overview of "
            + "HCI research at\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n";
        assertEquals(content, out.toString());
    }


    /**
     * Tests if output matches for search not found
     * 
     * @throws Exception
     */
    public void testPrint() throws Exception {
        System.setOut(new PrintStream(out));
        try {
            SemManager.main(new String[] { "128", "4", "printTest.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "Hashtable:\n" + "total records: 0\n"
            + "Freeblock List:\n" + "128: 0\n";
        assertEquals(content, out.toString());
    }


    /**
     * Tests if output matches for delete
     * 
     * @throws Exception
     */
    public void testDelete() throws Exception {
        System.setOut(new PrintStream(out));
        try {
            SemManager.main(new String[] { "256", "4", "deleteTest.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "Delete FAILED -- There is no record with ID 3\n"
            + "Successfully inserted record with ID 3\n"
            + "ID: 3, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an overview of "
            + "HCI research at\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "Size: 170\n"
            + "Record with ID 3 successfully deleted from the database\n"
            + "Delete FAILED -- There is no record with ID 1\n";
        assertEquals(content, out.toString());
    }
}
