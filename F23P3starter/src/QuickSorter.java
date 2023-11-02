import java.io.IOException;

/**
 * This class holds all the methods required to sort the binary file
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.11.01
 *
 */
public class QuickSorter {
    private BufferPool pool;

    /**
     * 
     * @param bp
     *            buffer pool class
     */
    public QuickSorter(BufferPool bp) {
        pool = bp;
    }


    /**
     * This method partitions the array for the quicksort
     * 
     * @param left
     *            left bound
     * @param right
     *            right bound
     * @param pivot
     *            pivot point
     * @return first position in the right partition
     * @throws IOException
     */
    private int partition(int left, int right, int pivot) throws IOException {
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
     * Helper method to find pivot point. Can be refined to improve efficiency
     * 
     * @param i
     *            left index
     * @param j
     *            right index
     * @return pivot point location
     * @throws IOException
     */
    private int findpivot(int i, int j) throws IOException {
        int index = (i + j) / 2;
        return index;
    }


    /**
     * Recursive quicksort method
     * 
     * @param i
     *            left index
     * @param j
     *            right index
     * @return the count of quicksort calls
     * @throws IOException
     */
    public int quicksort(int i, int j) throws IOException {
// System.out.println("quicksort(" + i + ", " + j + ")");
        int count = 1;
        // Check if the partition is all the same
        boolean inOrder = true;
        for (int l = i; l < j; l++) {
            if (pool.getRecord(l)[0] != pool.getRecord(l + 1)[0]) {
                inOrder = false;
                break;
            }
        }
        if (inOrder)
            return count;
        // Real quicksort
        int pivotindex = findpivot(i, j);
        pool.swap(pivotindex, j);
        short[] jRecord = pool.getRecord(j);
        int k = partition(i, j - 1, jRecord[0]);
        pool.setRecord(j, pool.getRecord(k));
        pool.setRecord(k, jRecord);
        if ((k - i) > 1)
            count += quicksort(i, k - 1);
        if ((j - k) > 1)
            count += quicksort(k + 1, j);
        return count;
    }
}
