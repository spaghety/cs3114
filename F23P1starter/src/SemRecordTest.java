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
 * Unit tests for the SemRecord class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.11
 */

public class SemRecordTest extends TestCase {
    private SemRecord semRecord;
    private int id;
    private int index;
    private int size;

    /**
     * Sets up the tests that follow.
     */
    public void setUp() {
        id = 0;
        index = 1;
        size = 16;
        semRecord = new SemRecord(id, index, size);
    }


    /**
     * Tests all getters
     */
    public void testGetters() {
        assertEquals(id, semRecord.getId());
        assertEquals(index, semRecord.getIndex());
        assertEquals(size, semRecord.getSize());
    }


    /**
     * Tests tombstone feature
     */
    public void testTombstone() {
        assertFalse(semRecord.isTombstone());
        semRecord.makeTombstone();
        assertTrue(semRecord.isTombstone());
    }


    /**
     * Tests equals()
     */
    @SuppressWarnings("unlikely-arg-type")
    public void testEquals() {
        // Null case
        assertFalse(semRecord.equals(null));

        // Wrong object class case
        assertFalse(semRecord.equals("Hello"));

        // Same object case
        assertEquals(semRecord, semRecord);

        // Sets up other SemRecord objects
        SemRecord semRecord2 = new SemRecord(id, index + 1, size);
        SemRecord semRecord3 = new SemRecord(id + 2, index + 2, size);

        // Regular cases, no tombstone
        assertEquals(semRecord, semRecord2);

        // Tombstone cases
        semRecord2.makeTombstone();
        semRecord.makeTombstone();
        assertEquals(semRecord, semRecord2);
        semRecord3.makeTombstone();
        assertFalse(semRecord3.equals(semRecord));
    }
}
