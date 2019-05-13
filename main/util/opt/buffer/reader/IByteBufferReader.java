package util.opt.buffer.reader;

public interface IByteBufferReader {

	public byte getByte();

	public byte[] getBytes(byte[] bytes, int length);

	public short getShort();

	public int getInt();

	public long getLong();

	public float getFloat();

	public double getDouble();

	public int position();

	public IByteBufferReader position(int position);

}
