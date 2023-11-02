// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
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
// may help me debug my program so int as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

import java.io.RandomAccessFile;
import java.io.FileWriter;

/**
 * The class containing the main method.
 *
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.11.01
 */

public class Main {

    /**
     * The main method
     * 
     * @param args
     *            Command line parameters.
     */
//    public static void main(String[] args) {
//        String fname = args[0]; // INPUT FILE NAME
//        int numb = Integer.parseInt(args[1]); // NUMBER OF BUFFERS 1-20
//        String statName = args[2]; // STATISTIC FILE TO WRITE TO
//        BufferPool bp = null;
//        FileWriter statFile = null;
//        RandomAccessFile inFile = null;
//        try {
//            statFile = new FileWriter(statName, false);
//            inFile = new RandomAccessFile(fname, "rw");
//            bp = new BufferPool(inFile, numb);
//            long tik = System.currentTimeMillis();
//            int quickCalls = Quicksort.quicksort(bp, 0, (int)(inFile.length()
//                / 4) - 1);
//            long tok = System.currentTimeMillis();
//            statFile.write("calls to quicksort: " + quickCalls);
//            statFile.write("\ntime (ms): " + (int)(tok - tik));
//            bp.flush();
//            inFile.close();
//            statFile.close();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
