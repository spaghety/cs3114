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
// may help me debug my program so long as neither of us writes
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
     *            right index @return
     * @throws IOException
     */
    private static int quicksort(BufferPool pool, long i, long j)
        throws IOException {
        int count = 1;
        //System.out.println("quicksort(buffpool, "+i+", "+j+")");
        long pivotindex = findpivot(pool, i, j);
        pool.swap(pivotindex, j);
        long k = partition(pool, i, j - 1, pool.getRecord(j)[0]);
        pool.swap(k, j);
        if ((k - i) > 1)
            count+=quicksort(pool, i, k - 1);
        if ((j - k) > 1)
            count+=quicksort(pool, k + 1, j);
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
    private static long findpivot(BufferPool pool, long i, long j)
        throws IOException {
        long index = (i + j) / 2;
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
    private static long partition(
        BufferPool pool,
        long left,
        long right,
        long pivot)
        throws IOException {
        while (left <= right) {
            //short[] leftRecord = pool.getRecord(left);
            while (pool.getRecord(left)[0] < pivot) {
                left++;
            }
            //short[] rightRecord = pool.getRecord(right);
            while ((right >= left) && pool.getRecord(right)[0] >= pivot) {
                right--;
            }
            if (right > left) {
                pool.swap(left, right);
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
        String fname = args[1]; // INPUT FILE NAME
        int numb = Integer.parseInt(args[2]); // NUMBER OF BUFFERS 1-20
        String statName = args[3]; // STATISTIC FILE TO WRITE TO
        BufferPool bp = null;
        FileWriter statFile = null;
        RandomAccessFile inFile = null;
        try {
            statFile = new FileWriter(statName);
            inFile = new RandomAccessFile(fname, "rw");
            bp = new BufferPool(inFile, numb);
            long tik = System.currentTimeMillis();
            int quickCalls = quicksort(bp, 0, (inFile.length() / 4) - 1);
            long tok = System.currentTimeMillis();
            statFile.write("calls to quicksort: ");
            statFile.write(quickCalls);
            statFile.write("\ntime (ms): ");
            statFile.write((int)(tok - tik));
            bp.flush();
            inFile.close();
            statFile.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
