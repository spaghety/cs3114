import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * The class managing the memory buffers
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.16
 *
 */
public class BufferPool {
    private String fname;
    Block[] buffer;

    /**
     * Basic constructor passes the scanner object
     * 
     * @param in
     *            scanner class to pass
     */
    public BufferPool(String inFile, int numb) {
        fname = inFile;
        buffer = new Block[numb];
    }


    /**
     * Read a block from the input file
     * 
     * @param bIndex
     *            index to begin reading from
     */
    public void readBlock(int bIndex) {
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
            for (int i = buffer.length - 1; i > 0; i--) {
                buffer[i - 1] = buffer[i];
            }
        }

        byte[] tempArr = new byte[4096];
        try {
            RandomAccessFile raf = new RandomAccessFile(fname, "r");
            raf.skipBytes(bIndex);
            raf.read(tempArr);
            raf.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        buffer[0] = new Block((int)bIndex / 4, tempArr);
    }


    /**
     * Get Record either from buffer pool or from IO file
     * 
     * @param index
     *            index to retrieve (record index or byte index/4)
     * @return short array representing a record with [0] representing the key
     *         and [1] the value
     */
    public short[] getRecord(int index) {
        int foundIndex = -1;
        for (int i = 0; i < buffer.length; i++) {
            Block blck = buffer[i];
            if (index > blck.getLeftBound() && index < blck.getLeftBound()
                + 1024) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex != -1)
            return buffer[foundIndex].getRecord(foundIndex % 1024);

        readBlock((int)Math.floor(index / 1024) * 4096);
        return getRecord(index);
    }

}
