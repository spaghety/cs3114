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
    private String fname;
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
    public BufferPool(String inFile, int numb) {
        fname = inFile;
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
                    RandomAccessFile wraf = new RandomAccessFile(fname, "w");
                    wraf.skipBytes(bIndex);
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
            RandomAccessFile raf = new RandomAccessFile(fname, "r");
            System.out.println(bIndex);
            raf.skipBytes(bIndex);
            raf.read(tempArr);
            raf.close();
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
        if (foundIndex != -1)
            return buffer[foundIndex].getRecord(foundIndex % RECORD_COUNT);

        readBlock((int)Math.floor(index / RECORD_COUNT) * BLOCK_SIZE);
        return getRecord(index);
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
            System.out.println("setRecord(" + (foundIndex % RECORD_COUNT)
                + ", [" + newRec[0] + ", " + newRec[1] + ");");
            buffer[foundIndex].setRecord(foundIndex % RECORD_COUNT, newRec);
            return;
        }
        readBlock((int)Math.floor(index / RECORD_COUNT) * BLOCK_SIZE);
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
        short[] record1 = getRecord(a);
        short[] record2 = getRecord(b);
        setRecord(a, record2);
        setRecord(b, record1);
    }


    /**
     * Gets the size of the input file in bytes
     * 
     * @return length of the file in bytes
     */
    public long byteSize() {
        try {
            RandomAccessFile file = new RandomAccessFile(fname, "r");
            return file.length();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
