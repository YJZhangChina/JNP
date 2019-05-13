package util.opt.buffer.reader;

import java.nio.ByteBuffer;

import sun.misc.Unsafe;
import util.opt.BootstrapUnsafe;

public class UnsafeHeapByteBufferReader implements IByteBufferReader {

	private long offset;
	private byte[] bytes;
	private Unsafe unsafe;

	public UnsafeHeapByteBufferReader(ByteBuffer buffer) {
		if (!buffer.hasArray()) {
			throw new UnsupportedOperationException("Buffer" + buffer + " has an empty array.");
		}
		offset = BootstrapUnsafe.BYTE_ARRAY_BASE_OFFSET;
		bytes = buffer.array();
		unsafe = BootstrapUnsafe.Unsafe;
	}

	public byte getByte() {
		byte value = unsafe.getByte(bytes, offset);
		offset += 1;
		return value;
	}

	public byte[] getBytes(byte[] bytes, int length) {
		unsafe.copyMemory(bytes, offset, bytes, BootstrapUnsafe.BYTE_ARRAY_BASE_OFFSET, length);
		return bytes;
	}

	public short getShort() {
		short value = unsafe.getShort(bytes, offset);
		offset += 2;
		return value;
	}

	public int getInt() {
		int value = unsafe.getInt(bytes, offset);
		offset += 4;
		return value;
	}

	public long getLong() {
		long value = unsafe.getLong(bytes, offset);
		offset += 8;
		return value;
	}

	public float getFloat() {
		float value = unsafe.getFloat(bytes, offset);
		offset += 4;
		return value;
	}

	public double getDouble() {
		double value = unsafe.getDouble(bytes, offset);
		offset += 8;
		return value;
	}

	public int position() {
		return (int) (offset - BootstrapUnsafe.BYTE_ARRAY_BASE_OFFSET);
	}

	public IByteBufferReader position(int position) {
		offset = BootstrapUnsafe.BYTE_ARRAY_BASE_OFFSET + position;
		return this;
	}

}
