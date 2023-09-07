
/**
 * 
 */

import student.TestCase;

/**
 * Unit tests for the FreeBlock class
 */
public class FreeBlockTest extends TestCase {
    private FreeBlock freeBlock;
    private static int index = 4;

    /**
     * Sets up the tests that follow.
     */
    public void setUp() {
        freeBlock = new FreeBlock(index);
    }
    
    public void testGetters() {
        assertEquals(null, freeBlock.getNext());
        assertEquals(index, freeBlock.getIndex());
    }
    
    public void testSetters() {
        FreeBlock next = new FreeBlock(index);
        assertTrue(freeBlock.setNext(next));
        assertEquals(next, freeBlock.getNext());
        assertFalse(freeBlock.setNext(null));
    }
}
