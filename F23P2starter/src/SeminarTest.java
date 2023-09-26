import student.TestCase;

// -------------------------------------------------------------------------
/**
 *  Test the Seminar class
 *
 *  @author CS3114/CS5040 staff
 *  @version July 2023, last updated September 2023
 */
public class SeminarTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing Here
    }


    /**
     * Check the toString method
     */
    public void testtoString()
    {
        String[] keywords = {"Good", "Bad", "Ugly"};
        String expected = "ID: 1729, Title: Seminar Title\n"
            + "Date: 2405231000, Length: 75, X: 15, Y: 33, Cost: 125\n"
            + "Description: This is a great seminar\n"
            + "Keywords: Good, Bad, Ugly\n";
        Seminar mysem = new Seminar(1729, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        String semPrint = mysem.toString();
        System.out.println("testtoString");
        System.out.println(semPrint);
        assertEquals(expected, semPrint);
        assertEquals(1729, mysem.id()  );
        assertEquals(125, mysem.cost()  );
        assertEquals(15, mysem.x() );
        assertEquals(33,mysem.y()  );
        assertEquals("2405231000",mysem.date());
        String[] tempKeywords = mysem.keywords();
        for (int i = 0; i < keywords.length; i++) {
            assertEquals(tempKeywords[i],keywords[i]);
        }
    }


    /**
     * Check the serialization/deserialization process
     * @throws Exception
     */
    public void testSeminarDS()
        throws Exception
    {
        String[] keywords = {"Good", "Bad", "Ugly"};

        Seminar mysem = new Seminar(1729, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        String semPrint = mysem.toString();
        System.out.println(semPrint);
        assertTrue(semPrint.equals("ID: 1729, Title: Seminar Title\n" +
            "Date: 2405231000, Length: 75, X: 15, Y: 33, Cost: 125\n" +
            "Description: This is a great seminar\n" +
            "Keywords: Good, Bad, Ugly\n"));
    }
}
