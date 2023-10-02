import java.io.*;
// import java.nio.file.Files;
// import java.nio.file.Paths;
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
    private PrintStream stdout;

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        stdout = System.out;
        new SemSearch();
    }


    /**
     * Tests insert
     * 
     * @throws FileNotFoundException
     */
    public void testInsert() throws FileNotFoundException {
        System.setOut(new PrintStream(out));
        try {
            SemSearch.main(new String[] { "128", "insert.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "Successfully inserted record with ID 1\n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an overview of HCI "
            + "research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "Successfully inserted record with ID 2\n"
            + "ID: 2, Title: Computational Biology and Bioinformatics in CS at "
            + "Virginia Tech\n"
            + "Date: 0610071600, Length: 60, X: 10, Y: 10, Cost: 30\n"
            + "Description: Introduction to   bioinformatics and computation "
            + "biology\n"
            + "Keywords: Bioinformatics, computation_biology, Biology, "
            + "Computer_Science, VT, Virginia_Tech\n"
            + "Successfully inserted record with ID 10\n"
            + "ID: 10, Title: Computing Systems Research at VT\n"
            + "Date: 0701250830, Length: 30, X: 30, Y: 10, Cost: 17\n"
            + "Description: Seminar about the      Computing systems research "
            + "at      VT\n"
            + "Keywords: high_performance_computing, grids, VT, computer, "
            + "science\n" + "Successfully inserted record with ID 3\n"
            + "ID: 3, Title: Overview of HPC and CSE Research at VT\n"
            + "Date: 1203301125, Length: 35, X: 0, Y: 0, Cost: 25\n"
            + "Description: Learn what kind of    research is done on HPC  and "
            + "CSE at VT\n" + "Keywords: HPC, CSE, computer_science\n"
            + "Insert FAILED - There is already a record with ID 10\n";
        assertEquals(content, out.toString());
        System.setOut(stdout);
    }


    /**
     * Tests print
     * 
     * @throws FileNotFoundException
     */
    public void testPrint() throws FileNotFoundException {
        System.setOut(new PrintStream(out));
        try {
            SemSearch.main(new String[] { "128", "print.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "ID Tree:\nThis tree is empty\n"
            + "Date Tree:\nThis tree is empty\n"
            + "Keyword Tree:\nThis tree is empty\n"
            + "Cost Tree:\nThis tree is empty\n" + "Location Tree:\n\n";
        assertEquals(content, out.toString());
        System.setOut(stdout);
    }


    /**
     * Tests search location
     * 
     * @throws FileNotFoundException
     */
    public void testSearchLocation() throws FileNotFoundException {
// SemSearch.main(new String[] { "128", "search location.txt" });
        System.setOut(new PrintStream(out));
        try {
            SemSearch.main(new String[] { "128", "search location.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setOut(stdout);
    }


    /**
     * Tests search keyword
     * 
     * @throws FileNotFoundException
     */
    public void testSearchKeyword() throws FileNotFoundException {
        System.setOut(new PrintStream(out));
        try {
            SemSearch.main(new String[] { "128", "search keyword.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "Seminars matching keyword VT:\n";
        assertEquals(content, out.toString());
        System.setOut(stdout);
    }


    /**
     * Tests search date
     * 
     * @throws FileNotFoundException
     */
    public void testSearchDate() throws FileNotFoundException {
        try {
            SemSearch.main(new String[] { "128", "insert.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setOut(new PrintStream(out));
        try {
            SemSearch.main(new String[] { "128", "search date.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "Successfully inserted record with ID 1\n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an overview of HCI "
            + "research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "Successfully inserted record with ID 2\n"
            + "ID: 2, Title: Computational Biology and Bioinformatics in CS at "
            + "Virginia Tech\n"
            + "Date: 0610071600, Length: 60, X: 10, Y: 10, Cost: 30\n"
            + "Description: Introduction to   bioinformatics and computation "
            + "biology\n"
            + "Keywords: Bioinformatics, computation_biology, Biology, "
            + "Computer_Science, VT, Virginia_Tech\n"
            + "Successfully inserted record with ID 10\n"
            + "ID: 10, Title: Computing Systems Research at VT\n"
            + "Date: 0701250830, Length: 30, X: 30, Y: 10, Cost: 17\n"
            + "Description: Seminar about the      Computing systems research "
            + "at      VT\n"
            + "Keywords: high_performance_computing, grids, VT, computer, "
            + "science\n" + "Successfully inserted record with ID 3\n"
            + "ID: 3, Title: Overview of HPC and CSE Research at VT\n"
            + "Date: 1203301125, Length: 35, X: 0, Y: 0, Cost: 25\n"
            + "Description: Learn what kind of    research is done on HPC  and "
            + "CSE at VT\n" + "Keywords: HPC, CSE, computer_science\n"
            + "Seminars with dates in range 0 to 1:\n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an overview of HCI "
            + "research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "ID: 2, Title: Computational Biology and Bioinformatics in CS at "
            + "Virginia Tech\n"
            + "Date: 0610071600, Length: 60, X: 10, Y: 10, Cost: 30\n"
            + "Description: Introduction to   bioinformatics and computation "
            + "biology\n"
            + "Keywords: Bioinformatics, computation_biology, Biology, "
            + "Computer_Science, VT, Virginia_Tech\n"
            + "ID: 10, Title: Computing Systems Research at VT\n"
            + "Date: 0701250830, Length: 30, X: 30, Y: 10, Cost: 17\n"
            + "Description: Seminar about the      Computing systems research "
            + "at      VT\n"
            + "Keywords: high_performance_computing, grids, VT, computer, "
            + "science\n" + "8 nodes visited in this search\n";
        assertEquals(content, out.toString());
        System.setOut(stdout);
    }


    /**
     * Tests search cost
     * 
     * @throws FileNotFoundException
     */
    public void testSearchCost() throws FileNotFoundException {
        System.setOut(new PrintStream(out));
        try {
            SemSearch.main(new String[] { "128", "search cost.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "Successfully inserted record with ID 1\n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an overview of HCI "
            + "research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "Successfully inserted record with ID 2\n"
            + "ID: 2, Title: Computational Biology and Bioinformatics in CS at "
            + "Virginia Tech\n"
            + "Date: 0610071600, Length: 60, X: 10, Y: 10, Cost: 30\n"
            + "Description: Introduction to   bioinformatics and computation "
            + "biology\n"
            + "Keywords: Bioinformatics, computation_biology, Biology, "
            + "Computer_Science, VT, Virginia_Tech\n"
            + "Seminars with costs in range 30 to 50:\n"
            + "ID: 2, Title: Computational Biology and Bioinformatics in CS at "
            + "Virginia Tech\n"
            + "Date: 0610071600, Length: 60, X: 10, Y: 10, Cost: 30\n"
            + "Description: Introduction to   bioinformatics and computation "
            + "biology\n"
            + "Keywords: Bioinformatics, computation_biology, Biology, "
            + "Computer_Science, VT, Virginia_Tech\n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an overview of HCI "
            + "research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "5 nodes visited in this search\n";
        assertEquals(content, out.toString());
        System.setOut(stdout);
    }


    /**
     * Tests search ID
     * 
     * @throws FileNotFoundException
     */
    public void testSearchID() throws FileNotFoundException {
// SemSearch.main(new String[] { "128", "search ID.txt" });
        System.setOut(new PrintStream(out));
        try {
            SemSearch.main(new String[] { "128", "search ID.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "Search FAILED -- There is no record with ID 1\n";
        assertFuzzyEquals(content, out.toString());
        System.setOut(stdout);
    }


    /**
     * Tests search ID with found result
     * 
     * @throws FileNotFoundException
     */
    public void testSearchIDFound() throws FileNotFoundException {
        System.setOut(new PrintStream(out));
        try {
            SemSearch.main(new String[] { "128", "insert search.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String content = "Successfully inserted record with ID 1\n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an overview of HCI "
            + "research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "Found record with ID 1: \n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an overview of HCI "
            + "research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n";
        assertFuzzyEquals(content, out.toString());
        System.setOut(stdout);
    }


    /**
     * Tests delete empty
     * 
     * @throws FileNotFoundException
     */
    public void testDeleteEmpty() throws FileNotFoundException {
        System.setOut(new PrintStream(out));
        try {
            SemSearch.main(new String[] { "128", "delete.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "Delete FAILED -- There is no record with ID 1\n"
            + "Delete FAILED -- There is no record with ID 3\n"
            + "Delete FAILED -- There is no record with ID 1\n";
        assertEquals(content, out.toString());
        System.setOut(stdout);
    }


    /**
     * Tests delete non-empty
     * 
     * @throws FileNotFoundException
     */
    public void testDelete() throws FileNotFoundException {
        System.setOut(new PrintStream(out));
        try {
            SemSearch.main(new String[] { "128", "delete 1.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = "Successfully inserted record with ID 1\n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an overview of HCI "
            + "research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "Record with ID 1 successfully deleted from the database" + "\n";
        assertFuzzyEquals(content, out.toString());
        System.setOut(stdout);
    }
}
