import student.TestCase;

/**
 * Unit tests for the FreeBlock class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.11
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


    /**
     * Tests the getter methods
     */
    public void testGetters() {
        assertEquals(null, freeBlock.getNext());
        assertEquals(index, freeBlock.getIndex());
    }


    /**
     * Tests the setter methods
     */
    public void testSetters() {
        FreeBlock next = new FreeBlock(8);
        assertTrue(freeBlock.setNext(next));
        assertEquals(next, freeBlock.getNext());
        assertFalse(freeBlock.setNext(null));
    }
}
