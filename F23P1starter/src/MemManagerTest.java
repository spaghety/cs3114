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
     *             if there is an error with insertion
     */
    public void testInsert() throws Exception {
        String[] tags = new String[] { "tag 1" };
        Seminar semToInsert = new Seminar(11, "test seminar", "9/10/23", 90,
            (short)1, (short)2, 12, tags, "desc");
        SemRecord newRec = null;
        memManager.printFreeBlock();
        try {
            newRec = memManager.insert(semToInsert.serialize(), 11);
        }
        catch (Exception e) {
            fail("error with insertion");
        }
        assertNotNull(newRec);
        assertEquals(0, newRec.getIndex());
        // Try inserting a second record, should take an index in the buddy slot
        // of the previous record
        SemRecord newNewRec = null;
        newNewRec = memManager.insert(semToInsert.serialize(), 11);
        assertNotNull(newNewRec);
        assertEquals(64, newNewRec.getIndex());
        memManager.printFreeBlock();
    }


    /**
     * Checks if the find command can retrieve an identical seminar to the one
     * just inserted just from the handle
     * 
     * @throws Exception
     *             from serialization
     */
    public void testFind() throws Exception {
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
     * Tests remove method
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
        assertFalse(memManager.remove(record));
        assertTrue(memManager.remove(record1));
        memManager.printFreeBlock();
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
        smallMem.insert(demoSem.serialize(), 2);
        smallMem.insert(demoSem.serialize(), 2);
        smallMem.insert(demoSem.serialize(), 4);
    }
}
