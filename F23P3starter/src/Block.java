import java.nio.ByteBuffer;

/**
 * This class represents a 4096 byte block in the buffer pool
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 *
 */
public class Block {
    private byte[] data;
    private int lbound;
    private boolean dirty;

    /**
     * Basic constructor
     * 
     * @param l
     *            left bound of the block
     * @param dt
     *            byte array of data
     */
    public Block(int l, byte[] dt) {
        data = dt;
        dirty = false;
        lbound = l;
    }


    /**
     * Gets record by record index (byte index/4) within the block
     * 
     * @param index
     *            index to retrieve
     * @return short array with [0] representing the record key and [1] the
     *         value
     */
    public short[] getRecord(int index) {
        index *= 4;
        byte[] tempArr = new byte[4];
        System.arraycopy(data, index, tempArr, index, 4);
        short[] record = new short[2];
        record[0] = ByteBuffer.wrap(tempArr).getShort(0);
        record[1] = ByteBuffer.wrap(tempArr).getShort(2);
        return record;
    }


    /**
     * Sets a record within the block and marks the block as dirty
     * 
     * @param index
     *            index to write to
     * @param record
     *            record to insert
     */
    public void setRecord(int index, short[] record) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.asShortBuffer().put(record);
        System.arraycopy(buffer.array(), 0, record, index, 4);
        dirty = true;
    }


    /**
     * Gets the block byte array
     * 
     * @return byte array of block data
     */
    public byte[] getData() {
        return data;
    }


    /**
     * Checks if this block has been edited
     * 
     * @return
     */
    public boolean isDirty() {
        return dirty;
    }


    /**
     * Gets the left bound of the block
     * 
     * @return
     */
    public int getLeftBound() {
        return lbound;
    }
}
