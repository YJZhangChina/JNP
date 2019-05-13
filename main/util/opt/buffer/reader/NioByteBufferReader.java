package util.opt.buffer.reader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class NioByteBufferReader implements IByteBufferReader {

	private ByteBuffer buffer;

	public NioByteBufferReader(ByteBuffer buffer) {
		this.buffer = buffer.duplicate();
		this.buffer.order(ByteOrder.nativeOrder());
	}

	public byte getByte() {
		return buffer.get();
	}

	public byte[] getBytes(byte[] bytes, int length) {
		buffer.get(bytes, 0, length);
		return bytes;
	}

	public short getShort() {
		return buffer.getShort();
	}

	public int getInt() {
		return buffer.getInt();
	}

	public long getLong() {
		return buffer.getLong();
	}

	public float getFloat() {
		return buffer.getFloat();
	}

	public double getDouble() {
		return buffer.getDouble();
	}

	public int position() {
		return buffer.position();
	}

	public IByteBufferReader position(int position) {
		buffer.position(position);
		return this;
	}

}
