import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * The class managing the memory buffers
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.11.01
 */
public class BufferPool {
    private RandomAccessFile wraf;
    private Block[] buffer;
    private int buffersize;
    private static final int RECORD_COUNT = 1024;
    private static final int BLOCK_SIZE = 4096;
    private int cacheHits;
    private int diskReads;
    private int diskWrites;

    /**
     * Basic constructor
     * 
     * @param inFile
     *            Input file
     * @param numb
     *            The index of the block
     */
    public BufferPool(RandomAccessFile inFile, int numb) {
        wraf = inFile;
        buffer = new Block[numb];
        buffersize = 0;
        cacheHits = 0;
        diskReads = 0;
        diskWrites = 0;
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
     * reads a block from the binary file
     * 
     * @param bIndex
     *            block index, multiplied by block size during method
     * @return Block object
     * @throws IOException
     */
    public Block readBlock(int bIndex) throws IOException {
        diskReads++;
        bIndex *= BLOCK_SIZE;
        byte[] tempArr = new byte[BLOCK_SIZE];
        wraf.seek(bIndex);
        wraf.read(tempArr);
        return new Block((int)bIndex / 4, tempArr);
    }


    /**
     * This method shifts a block at a particular index to the top of the
     * bufferpool
     * 
     * @param ind
     *            index of the block to shift. -1 if it's just making room for a
     *            completely new block
     * @throws IOException
     */
    private void moveToTop(int ind) throws IOException {
        Block temp;
        if (ind == 0)
            return;
        if (ind == -1) {
            Block lastBlock = buffer[buffer.length - 1];
            if (buffersize < buffer.length)
                buffersize++;
            else if (lastBlock.isDirty()) {
                wraf.seek(lastBlock.getLeftBound() * 4);
                if (wraf.getFilePointer() + lastBlock.getData().length > wraf
                    .length())
                    throw new IOException("SCANNER OUT OF BOUNDS");
                diskWrites++;
                wraf.write(lastBlock.getData());
            }
            ind = buffersize - 1;
            temp = null;
        }
        else {
            temp = buffer[ind];
        }
        for (int i = ind; i > 0; i--) {
            buffer[i] = buffer[i - 1];
        }
        buffer[0] = temp;
    }


    /**
     * Get Record either from buffer pool or from IO file
     * 
     * @param index
     *            index to retrieve (record index or byte index/4)
     * @return short array representing a record with [0] representing the key
     *         and [1] the value
     * @throws IOException
     */
    public short[] getRecord(int index) throws IOException {
        if (index == -1)
            return new short[] { -1, -1 };
        int foundIndex = -1;
        for (int i = 0; i < buffersize; i++) {
            Block blck = buffer[i];
            if (index >= blck.getLeftBound() && index < blck.getLeftBound()
                + RECORD_COUNT) {
                foundIndex = i;
                break;
            }
        }
        moveToTop(foundIndex);
        if (foundIndex == -1) {
            buffer[0] = readBlock((int)Math.floor(index / RECORD_COUNT));
        }
        cacheHits++;
        return buffer[0].getRecord((int)(index % RECORD_COUNT));
    }


    /**
     * set record in buffer pool
     * 
     * @param index
     *            index of record to set
     * @param newRec
     *            new record to replace old data
     * @throws IOException
     */
    public void setRecord(int index, short[] newRec) throws IOException {
        int foundIndex = -1;
        for (int i = 0; i < buffersize; i++) {
            Block blck = buffer[i];
            if (index >= blck.getLeftBound() && index < blck.getLeftBound()
                + RECORD_COUNT) {
                foundIndex = i;
                break;
            }
        }
        moveToTop(foundIndex);
        if (foundIndex == -1) {
            buffer[0] = readBlock((int)Math.floor(index / RECORD_COUNT));
        }
        buffer[0].setRecord((int)(index % RECORD_COUNT), newRec);
    }


    /**
     * Swaps record data between two different locations
     * 
     * @param a
     *            index 1
     * @param b
     *            index 2
     * @throws IOException
     */
    public void swap(int a, int b) throws IOException {
        short[] record1 = getRecord(a);
        short[] record2 = getRecord(b);
        setRecord(a, record2);
        setRecord(b, record1);
    }


    /**
     * Clears the buffer pool by writing all dirty buffers
     * 
     * @throws IOException
     */
    public void flush() throws IOException {
        for (int i = 0; i < buffersize; i++) {
            if (buffer[i].isDirty()) {
                wraf.seek(buffer[i].getLeftBound() * 4);
                diskWrites++;
                wraf.write(buffer[i].getData());
            }
            buffer[i] = null;
        }
        buffersize = 0;
    }


    /**
     * Gets the number of cache hits
     * 
     * @return the number of cache hits
     */
    public int getCacheHits() {
        return cacheHits;
    }


    /**
     * Gets the number of disk reads
     * 
     * @return the number of disk reads
     */
    public int getDiskReads() {
        return diskReads;
    }


    /**
     * Gets the number of disk writes
     * 
     * @return the number of disk writes
     */
    public int getDiskWrites() {
        return diskWrites;
    }

// /**
// * Finds index
// *
// * @param index
// * the index to be found
// * @return index, or -1 if not found
// */
// private int findIndex(int index) {
// int foundIndex = -1;
// for (int i = 0; i < buffersize; i++) {
// Block blck = buffer[i];
// if (index >= blck.getLeftBound() && index < blck.getLeftBound()
// + RECORD_COUNT) {
// foundIndex = i;
// break;
// }
// }
// return foundIndex;
// }

}
