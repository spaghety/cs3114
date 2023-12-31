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

/**
 * This program manages a database of seminars whose data is read from a command
 * file.
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The class containing the main method.
 *
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 1.0
 */
public class SemManager {
// private byte[] db;
// private FreeBlock[] freespace;
// private HashTable ht;

    /**
     * This is the main file for the program
     * Note: both memsize and hashsize are always a power of two
     * 
     * @param args
     *            Command line parameters
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        int memsize = Integer.parseInt(args[0]);
        int hashsize = Integer.parseInt(args[1]);
        String fname = args[2];
        MemManager memoryManager = new MemManager(memsize);
        HashTable ht = new HashTable(hashsize);
// FreeBlock[] freespace = new FreeBlock[];
        SemParser fileRead = new SemParser(fname, ht, memoryManager);
    }
}
