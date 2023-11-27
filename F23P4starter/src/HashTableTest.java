/**
 * @author Phillip Jordan (alexj14)
 *
 */
public class HashTableTest extends student.TestCase  {
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
    }
    public void testInsertGraph() {
        assertEquals(4, ht.insert("song1", "artist1"));
        assertEquals(4, ht.insert("song1", "artist1"));
        assertEquals(1, ht.insert("song1", "artist2"));
        assertEquals(2, ht.insert("song2", "artist1"));
    }

}
