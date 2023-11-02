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

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 * The class containing the main method.
 *
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.16
 */

public class Quicksort {

    /**
     * This method is used to generate a file of a certain size, containing a
     * specified number of records.
     *
     * @param filename
     *            the name of the file to create/write to
     * @param blockSize
     *            the size of the file to generate
     * @param format
     *            the format of file to create
     * @throws IOException
     *             throw if the file is not open and proper
     */
    public static void generateFile(
        String filename,
        String blockSize,
        char format)
        throws IOException {
        FileGenerator generator = new FileGenerator();
        String[] inputs = new String[3];
        inputs[0] = "-" + format;
        inputs[1] = filename;
        inputs[2] = blockSize;
        generator.generateFile(inputs);
    }


    /**
     * Recursive quicksort method
     * 
     * @param pool
     *            bufferpool object
     * @param i
     *            left index
     * @param j
     *            right index
     * @return the count of quicksort calls
     * @throws IOException
     */
    private static int quicksort(BufferPool pool, int i, int j)
        throws IOException {
//        System.out.println("quicksort(" + i + ", " + j + ")");
        int count = 1;
        boolean same = true;
        // Check if the partition is all the same
        for (int l = i; l < j - 1; l++) {
            if (pool.getRecord(l)[0] != pool.getRecord(l + 1)[0]) {
                same = false;
                break;
            }
        }
        if (same)
            return count;
        // Real quicksort
        int pivotindex = findpivot(pool, i, j);
        pool.swap(pivotindex, j);
        short[] jRecord = pool.getRecord(j);
        int k = partition(pool, i, j - 1, jRecord[0]);
        pool.setRecord(j, pool.getRecord(k));
        pool.setRecord(k, jRecord);
        if ((k - i) > 1)
            count += quicksort(pool, i, k - 1);
        if ((j - k) > 1)
            count += quicksort(pool, k + 1, j);
        return count;
    }


    /**
     * Helper method to find pivot point. Can be refined to improve efficiency
     * 
     * @param pool
     *            bufferpool object
     * @param i
     *            left index
     * @param j
     *            right index
     * @return pivot point location
     * @throws IOException
     */
    private static int findpivot(BufferPool pool, int i, int j)
        throws IOException {
        int index = (i + j) / 2;
        return index;
    }


    /**
     * This method partitions the array for the quicksort
     * 
     * @param pool
     *            array object
     * @param left
     *            left bound
     * @param right
     *            right bound
     * @param pivot
     *            pivot point
     * @return first position in the right partition
     * @throws IOException
     */
    private static int partition(
        BufferPool pool,
        int left,
        int right,
        int pivot)
        throws IOException {
        while (left <= right) {
            short[] leftRecord = pool.getRecord(left);
            while (leftRecord[0] < pivot) {
                left++;
                leftRecord = pool.getRecord(left);
            }
            short[] rightRecord = pool.getRecord(right);
            while ((right >= left) && (rightRecord[0] >= pivot)) {
                right--;
                rightRecord = pool.getRecord(right);
            }
            if (right > left) {
                pool.setRecord(left, rightRecord);
                pool.setRecord(right, leftRecord);
            }
        }
        return left;
    }


    /**
     * The main method
     * 
     * @param args
     *            Command line parameters.
     */
    public static void main(String[] args) {
        String fname = args[0]; // INPUT FILE NAME
        int numb = Integer.parseInt(args[1]); // NUMBER OF BUFFERS 1-20
        String statName = args[2]; // STATISTIC FILE TO WRITE TO
        BufferPool bp = null;
        FileWriter statFile = null;
        RandomAccessFile inFile = null;
        try {
            statFile = new FileWriter(statName, false);
            inFile = new RandomAccessFile(fname, "rw");
            bp = new BufferPool(inFile, numb);
            long tik = System.currentTimeMillis();
            int quickCalls = quicksort(bp, 0, (int)(inFile.length() / 4) - 1);
            long tok = System.currentTimeMillis();
            statFile.write("calls to quicksort: " + quickCalls);
            statFile.write("\ntime (ms): " + (int)(tok - tik));
            bp.flush();
            inFile.close();
            statFile.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
