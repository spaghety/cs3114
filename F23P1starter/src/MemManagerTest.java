// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import student.TestCase;

/**
 * This class tests the methods and functionality of the MemManager class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.12
 */
public class MemManagerTest extends TestCase {

    private MemManager memManager;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Sets up the tests that follow.
     */
    public void setUp() {
        memManager = new MemManager(256);
    }


    /**
     * Tests the insert method
     * 
     * @throws Exception
     *             from serialization
     */
    public void testInsert() throws Exception {
        String[] tags = new String[] { "tag 1" };
        Seminar semToInsert = new Seminar(11, "test seminar", "9/10/23", 90,
            (short)1, (short)2, 12, tags, "desc");
        SemRecord newRec = null;
        // memManager.printFreeBlock();
        try {
            newRec = memManager.insert(semToInsert.serialize(), 11);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("error with insertion");
        }
        assertNotNull(newRec);
        assertEquals(new SemRecord(11, 0, 64), newRec);
        assertEquals(0, newRec.getIndex());
        // Try inserting a second record, should take an index in the buddy slot
        // of the previous record
        SemRecord newNewRec = null;
        newNewRec = memManager.insert(semToInsert.serialize(), 11);
        assertNotNull(newNewRec);
        assertEquals(64, newNewRec.getIndex());
        // memManager.printFreeBlock();
    }


    /**
     * Checks if the find command can retrieve an identical seminar to the one
     * just inserted just from the handle
     */
    public void testFind() {
        assertNull(memManager.find(null));
        String[] tags = new String[] { "tag 1" };
        Seminar semToInsert = new Seminar(11, "test seminar", "9/10/23", 90,
            (short)1, (short)2, 12, tags, "desc");
        SemRecord record = null;
        try {
            record = memManager.insert(semToInsert.serialize(), 11);
        }
        catch (Exception e) {
            System.out.println("ERROR: Seminar failed to serialize");
            e.printStackTrace();
        }
        assertTrue(semToInsert.toString().equals(memManager.find(record)
            .toString()));
        assertNull(memManager.find(new SemRecord(15, 64, 12)));
    }


    /**
     * Tests remove and clean methods and the print out after remove happens
     * 
     * @throws Exception
     *             from serialization
     */
    public void testRemove() throws Exception {
        assertFalse(memManager.remove(null));
        String[] tags = new String[] { "tag 1" };
        Seminar semToInsert = new Seminar(11, "test seminar", "9/10/23", 90,
            (short)1, (short)2, 12, tags, "desc");
        SemRecord record = memManager.insert(semToInsert.serialize(), 11);
        SemRecord record1 = memManager.insert(semToInsert.serialize(), 2);
        // Check printFreeBlock when there are multiple free spaces of the same
        // size
        assertTrue(memManager.remove(record));
// assertFalse(memManager.remove(record));
        assertTrue(memManager.remove(record1));
        System.setOut(new PrintStream(out));
        memManager.printFreeBlock();
        assertEquals("256: 0\n", out.toString());
    }


    /**
     * Tests remove at different index
     */
    public void testRemove2() {
        System.setOut(new PrintStream(out));
        memManager.insert(new byte[256], 0);
        memManager.remove(new SemRecord(0, 64, 64));
        memManager.remove(new SemRecord(1, 192, 64));
        memManager.printFreeBlock();
        assertEquals("64: 64 192\n", out.toString());
    }


    /**
     * Tests remove at different index and different size
     */
    public void testRemove3() {
        System.setOut(new PrintStream(out));
        memManager.insert(new byte[256], 0);
        memManager.remove(new SemRecord(0, 128, 32));
        memManager.remove(new SemRecord(1, 192, 64));
        memManager.printFreeBlock();
        assertEquals("32: 128\n64: 192\n", out.toString());
    }


    /**
     * Tests doubleSize when the memory pool needs an expansion
     * 
     * @throws Exception
     *             from serialization
     */
    public void testDoubleSize() throws Exception {
        MemManager smallMem = new MemManager(128);
        String[] tags = new String[] { "tag 1" };
        Seminar demoSem = new Seminar(2, "test seminar", "3/1/23", 90, (short)1,
            (short)2, 12, tags, "desc");
        System.setOut(new PrintStream(out));
        smallMem.insert(demoSem.serialize(), 2);
        assertEquals("", out.toString());
        smallMem.insert(demoSem.serialize(), 2);
        assertEquals("", out.toString());
        smallMem.insert(demoSem.serialize(), 4);
        assertEquals("Memory pool expanded to 256 bytes\n", out.toString());
    }


    /**
     * Tests printFreeBlock in the most basic sense
     */
    public void testPrint() {
        System.setOut(new PrintStream(out));
        memManager.printFreeBlock();
        assertEquals("256: 0\n", out.toString());
    }


    /**
     * Tests printFreeBlock when full
     */
    public void testPrint2() {
        memManager.insert(new byte[256], 0);
        System.setOut(new PrintStream(out));
        memManager.printFreeBlock();
        assertEquals("There are no freeblocks in the memory pool\n", out
            .toString());
    }
}
