/**
 * This class tests the controller class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.12.05
 */
public class ControllerTest extends student.TestCase {
    private Controller ct;

    /**
     * Sets up conditions for future tests
     */
    public void setUp() {
        ct = new Controller(10);
    }


    /**
     * Tests the insert method
     */
    public void testInsert() {
        assertEquals(3, ct.insert("artist1", "song1"));
        assertEquals(4, ct.insert("artist1", "song1"));
        assertEquals(1, ct.insert("artist2", "song1"));
        assertEquals(2, ct.insert("artist1", "song2"));
        assertEquals(3, ct.insert("artist3", "song3"));
        assertEquals(0, ct.insert("artist1", "song3"));
        assertEquals(
            "2: |artist1|\n4: |artist3|\n8: |artist2|\ntotal artists: 3", ct
                .printArtists());
    }


    /**
     * Tests the remove method
     */
    public void testRemove() {
        assertFalse(ct.removeArtist("artist1"));
        assertFalse(ct.removeSong("song1"));
        assertEquals(3, ct.insert("artist1", "song1"));
        assertTrue(ct.removeArtist("artist1"));
        assertFalse(ct.removeSong("song1"));
        assertEquals(3, ct.insert("artist1", "song1"));
        assertEquals(2, ct.insert("artist1", "song2"));
        assertTrue(ct.removeArtist("artist1"));
        assertEquals("2: TOMBSTONE\r\n" + "3: TOMBSTONE\ntotal artists: 0", ct
            .printArtists());
        assertEquals("0: TOMBSTONE\n8: TOMBSTONE\n9: TOMBSTONE\ntotal songs: 0",
            ct.printSongs());
        assertEquals(3, ct.insert("artist5", "song5"));
        assertTrue(ct.removeSong("song5"));
        assertEquals("0: TOMBSTONE\n2: TOMBSTONE\n8: TOMBSTONE\n9: TOMBSTONE\n"
            + "total songs: 0", ct.printSongs());
    }


    /**
     * Tests the graph functionality with the printGraph method
     */
    public void testGraph() {
        ct.insert("artist1", "song1");
        ct.insert("artist2", "song1");
        ct.insert("artist2", "song3");
        ct.insert("artist5", "song5");
        ct.insert("artist6", "song5");
        assertEquals(
            "There are 2 connected components\nThe largest connected component "
                + "has 4 elements\nThe diameter of the largest component is 4",
            ct.printGraph());
    }
}
