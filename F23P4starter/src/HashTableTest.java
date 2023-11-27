/**
 * @author Phillip Jordan (alexj14)
 *
 */
public class HashTableTest extends student.TestCase {
    HashTable ht;

    public void setUp() {
        ht = new HashTable(8);
    }


    public void testInsertSimple() {
        assertEquals(0, ht.insert("song1", "artist1"));
        assertEquals(1, ht.artistCount());
        assertEquals(1, ht.songCount());
        assertEquals(0, ht.insert("song2", "artist2"));
        assertEquals(2, ht.artistCount());
        assertEquals(2, ht.songCount());
        assertEquals(4, ht.insert("song2", "artist2"));
    }


    public void testInsertGraph() {
        assertEquals(0, ht.insert("song1", "artist1"));
        assertEquals(1, ht.insert("song1", "artist2"));
        assertEquals(2, ht.artistCount());
        assertEquals(1, ht.songCount());
        assertEquals(2, ht.insert("song2", "artist1"));
        assertEquals(2, ht.songCount());
        assertEquals(0, ht.insert("song3", "artist3"));
        assertEquals(3, ht.songCount());
        assertEquals(3, ht.artistCount());
        assertEquals(3, ht.insert("song2", "artist3"));
        assertEquals(3, ht.songCount());
        assertEquals(3, ht.artistCount());
    }


    public void testRemoveSimple() {
        assertEquals(0, ht.insert("song1", "artist1"));
        assertEquals(1, ht.artistCount());
        assertTrue(ht.remove(false, "artist1"));
        assertFalse(ht.remove(false, "asdf"));
        assertEquals(0, ht.artistCount());
        assertEquals(0, ht.songCount());
        assertEquals(0, ht.insert("song1", "artist1"));
        assertEquals(1, ht.insert("song1", "artist2"));
        assertTrue(ht.remove(true, "song1"));
        assertEquals(0, ht.artistCount());
        assertEquals(0, ht.songCount());
    }


    public void testRemoveComplex() {
        ht.insert("song1", "artist1");
        ht.insert("song2", "artist2");
        ht.insert("song3", "artist2");
        ht.insert("song4", "artist2");
        assertTrue(ht.remove(false, "artist2"));
        assertEquals(1, ht.artistCount());
        assertEquals(1, ht.songCount());
        ht.insert("song2", "artist2");
        ht.insert("song3", "artist2");
        ht.insert("song4", "artist2");
        assertTrue(ht.remove(true, "song3"));
        assertTrue(ht.remove(true, "song2"));
        assertEquals(2, ht.artistCount());
        assertEquals(2, ht.songCount());
    }
    
    public void testPrint() {
        ht.insert("song1", "artist1");
        ht.insert("song2", "artist2");
        ht.insert("song3", "artist3");
        ht.insert("song4", "artist4");
        System.out.print(ht.printArtists());
        System.out.print(ht.printSongs());
    }

}
