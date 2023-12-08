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
        assertEquals("0: |song3|\n8: |song1|\n9: |song2|\ntotal songs: 3", ct
            .printSongs());
    }


    /**
     * Tests the insert method but extend the table
     */
    public void testInsertExtend() {
        assertEquals(3, ct.insert("artist1", "song1"));
        assertEquals(1, ct.insert("artist2", "song1"));
        assertEquals(1, ct.insert("artist3", "song1"));
        assertEquals(1, ct.insert("artist4", "song1"));
        assertEquals(1, ct.insert("artist5", "song1"));
        assertEquals(11, ct.insert("artist6", "song1"));
        assertEquals(2, ct.insert("artist1", "song2"));
        assertEquals(2, ct.insert("artist1", "song3"));
        assertEquals(2, ct.insert("artist1", "song4"));
        assertEquals(2, ct.insert("artist1", "song5"));
        assertEquals(22, ct.insert("artist1", "song6"));
        System.out.println(ct.printArtists());
        System.out.println(ct.printSongs());
        assertEquals("0: |artist4|\n" + "4: |artist3|\n" + "8: |artist2|\n"
            + "12: |artist1|\n" + "13: |artist6|\n" + "16: |artist5|\n"
            + "total artists: 6", ct.printArtists());
        assertEquals("8: |song1|\n" + "9: |song2|\n" + "10: |song3|\n"
            + "11: |song4|\n" + "12: |song5|\n" + "13: |song6|\n"
            + "total songs: 6", ct.printSongs());
    }


    /**
     * Tests the remove method
     */
    public void testRemove() {
        assertFalse(ct.removeArtist("artist1"));
        assertFalse(ct.removeSong("song1"));
        assertEquals(3, ct.insert("artist1", "song1"));
        assertTrue(ct.removeArtist("artist1"));
        assertEquals(1, ct.insert("artist1", "song1"));
        assertEquals(2, ct.insert("artist1", "song2"));
        assertTrue(ct.removeArtist("artist1"));
        assertEquals("2: TOMBSTONE\ntotal artists: 0", ct.printArtists());
        assertEquals("8: |song1|\n9: |song2|\ntotal songs: 2", ct.printSongs());
        assertEquals(3, ct.insert("artist5", "song5"));
        assertTrue(ct.removeSong("song5"));
        assertEquals("2: TOMBSTONE\n6: |artist5|\ntotal artists: 1", ct
            .printArtists());
        assertEquals("2: TOMBSTONE\n8: |song1|\n9: |song2|\ntotal songs: 2", ct
            .printSongs());
    }


    /**
     * Tests remove then insert back
     */
    public void testRemoveInsertBack() {
        String art1 =
            "2: |artist1|\n4: |artist3|\n8: |artist2|\ntotal artists: 3";
        String son1 = "0: |song3|\n8: |song1|\n9: |song2|\ntotal songs: 3";
        String art2 =
            "2: TOMBSTONE\n4: TOMBSTONE\n8: TOMBSTONE\ntotal artists: 0";
        String son2 =
            "0: TOMBSTONE\n8: TOMBSTONE\n9: TOMBSTONE\ntotal songs: 0";
        assertEquals(3, ct.insert("artist1", "song1"));
        assertEquals(1, ct.insert("artist2", "song1"));
        assertEquals(2, ct.insert("artist1", "song2"));
        assertEquals(3, ct.insert("artist3", "song3"));
        assertEquals(0, ct.insert("artist1", "song3"));
        assertEquals(art1, ct.printArtists());
        assertEquals(son1, ct.printSongs());
        assertTrue(ct.removeSong("song1"));
        assertTrue(ct.removeSong("song2"));
        assertTrue(ct.removeSong("song3"));
        assertEquals(art1, ct.printArtists());
        assertEquals(son2, ct.printSongs());
        assertTrue(ct.removeArtist("artist1"));
        assertTrue(ct.removeArtist("artist2"));
        assertTrue(ct.removeArtist("artist3"));
        assertEquals(art2, ct.printArtists());
        assertEquals(son2, ct.printSongs());
        assertEquals(3, ct.insert("artist1", "song1"));
        assertEquals(4, ct.insert("artist1", "song1"));
        assertEquals(1, ct.insert("artist2", "song1"));
        assertEquals(2, ct.insert("artist1", "song2"));
        assertEquals(3, ct.insert("artist3", "song3"));
        assertEquals(0, ct.insert("artist1", "song3"));
        assertEquals(art1, ct.printArtists());
        assertEquals(son1, ct.printSongs());
        assertTrue(ct.removeArtist("artist1"));
        assertTrue(ct.removeArtist("artist2"));
        assertTrue(ct.removeArtist("artist3"));
        assertEquals(art2, ct.printArtists());
        assertEquals(son1, ct.printSongs());
        assertTrue(ct.removeSong("song1"));
        assertTrue(ct.removeSong("song2"));
        assertTrue(ct.removeSong("song3"));
        assertEquals(art2, ct.printArtists());
        assertEquals(son2, ct.printSongs());
        assertEquals(3, ct.insert("artist1", "song1"));
        assertEquals(4, ct.insert("artist1", "song1"));
        assertEquals(1, ct.insert("artist2", "song1"));
        assertEquals(2, ct.insert("artist1", "song2"));
        assertEquals(3, ct.insert("artist3", "song3"));
        assertEquals(0, ct.insert("artist1", "song3"));
        assertEquals(art1, ct.printArtists());
        assertEquals(son1, ct.printSongs());
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
