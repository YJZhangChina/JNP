package util.opt.buffer.reader;

import java.nio.Buffer;
import java.nio.ByteBuffer;

import sun.misc.Unsafe;
import util.ReflectUtil;
import util.opt.BootstrapUnsafe;

public class UnsafeDirectByteBufferReadr implements IByteBufferReader {

	private long address;
	private long offset;
	private Unsafe unsafe;

	public UnsafeDirectByteBufferReadr(ByteBuffer buffer) {
		address = getAddress(buffer);
		offset = address;
		unsafe = BootstrapUnsafe.Unsafe;
	}

	public byte getByte() {
		byte value = unsafe.getByte(offset);
		offset += 1;
		return value;
	}

	public byte[] getBytes(byte[] bytes, int length) {
		unsafe.copyMemory(null, offset, bytes, BootstrapUnsafe.BYTE_ARRAY_BASE_OFFSET, length);
		return bytes;
	}

	public short getShort() {
		short value = unsafe.getShort(offset);
		offset += 2;
		return value;
	}

	public int getInt() {
		int value = unsafe.getInt(offset);
		offset += 4;
		return value;
	}

	public long getLong() {
		long value = unsafe.getLong(offset);
		offset += 8;
		return value;
	}

	public float getFloat() {
		float value = unsafe.getFloat(offset);
		offset += 4;
		return value;
	}

	public double getDouble() {
		double value = unsafe.getDouble(offset);
		offset += 8;
		return value;
	}

	public int position() {
		return (int) (offset - address);
	}

	public IByteBufferReader position(int position) {
		offset = address + position;
		return this;
	}

	private static long getAddress(ByteBuffer buffer) {
		long address;
		try {
			address = ReflectUtil.getField(Buffer.class, "address").getLong(buffer);
		} catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
		return address;
	}

}
