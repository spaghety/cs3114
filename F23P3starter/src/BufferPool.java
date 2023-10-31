import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * The class managing the memory buffers
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.25
 */
public class BufferPool {
    private RandomAccessFile wraf;
    private Block[] buffer;
    private int buffersize;
    private static final int RECORD_COUNT = 1024;
    private static final int BLOCK_SIZE = 4096;

    /**
     * Basic constructor passes the scanner object
     * 
     * @param in
     *            scanner class to pass
     */
    public BufferPool(RandomAccessFile inFile, int numb) {
        wraf = inFile;
        buffer = new Block[numb];
        buffersize = 0;
    }


    /**
     * Gets the current buffer size
     * 
     * @return buffersize
     */
    public int getBuffersize() {
        return buffersize;
    }


    /**
     * Read a block from the input file
     * 
     * @param bIndex
     *            block index to begin reading from
     */
    public void readBlock(int bIndex) {
        bIndex *= BLOCK_SIZE;
        Block lastBlock = buffer[buffer.length - 1];
        if (lastBlock != null) {
            if (lastBlock.isDirty() == true) {
                try {
                    wraf.seek(bIndex);
                    wraf.write(lastBlock.getData());
                    wraf.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            buffersize--;
        }
        for (int i = buffer.length - 1; i > 0; i--) {
            buffer[i] = buffer[i] = buffer[i - 1];
        }

        byte[] tempArr = new byte[BLOCK_SIZE];
        try {
            wraf.seek(bIndex);
            wraf.read(tempArr);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        buffer[0] = new Block((int)bIndex / 4, tempArr);
        buffersize++;
    }


    /**
     * Get Record either from buffer pool or from IO file
     * 
     * @param index
     *            index to retrieve (record index or byte index/4)
     * @return short array representing a record with [0] representing the key
     *         and [1] the value
     */
    public short[] getRecord(long index) {
        int foundIndex = -1;
        for (int i = 0; i < buffersize; i++) {
            Block blck = buffer[i];
            if (index >= blck.getLeftBound() && index < blck.getLeftBound()
                + RECORD_COUNT) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex != -1) {
            return buffer[foundIndex].getRecord((int)(index % RECORD_COUNT));
        }
        System.out.println("readBlock(" + Math.floor(index / RECORD_COUNT)
            + ")");
        readBlock((int)Math.floor(index / RECORD_COUNT));
        return buffer[0].getRecord((int) (index % RECORD_COUNT));
    }


    /**
     * set record in buffer pool
     * 
     * @param index
     *            index of record to set
     * @param newRec
     *            new record to replace old data
     */
    public void setRecord(long index, short[] newRec) {
        int foundIndex = -1;
        for (int i = 0; i < buffersize; i++) {
            Block blck = buffer[i];
            if (index >= blck.getLeftBound() && index < blck.getLeftBound()
                + RECORD_COUNT) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex != -1) {
            buffer[foundIndex].setRecord((int)(index % RECORD_COUNT), newRec);
            return;
        }
        readBlock((int)Math.floor(index / RECORD_COUNT));
        setRecord(index, newRec);
    }


    /**
     * Swaps record data between two different locations
     * 
     * @param a
     *            index 1
     * @param b
     *            index 2
     */
    public void swap(long a, long b) {
        System.out.println("swap call");
        short[] record1 = getRecord(a);
        short[] record2 = getRecord(b);
        setRecord(a, record2);
        setRecord(b, record1);
    }

}
