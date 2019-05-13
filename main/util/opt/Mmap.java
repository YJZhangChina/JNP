package util.opt;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import sun.misc.Unsafe;
import sun.nio.ch.FileChannelImpl;
import util.ReflectUtil;

public class Mmap {

	public Mmap(final FileChannel channel, long offset, long size, FileChannel.MapMode mode) throws IOException {
		this.channel = channel;
		this.offset = offset;
		this.size = size;
		this.mode = mode;
		init();
	}

	public Mmap(final Path path, long offset, long size, FileChannel.MapMode mode) throws IOException {
		try {
			FileChannel channel = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE,
					StandardOpenOption.TRUNCATE_EXISTING);
			this.channel = channel;
			this.offset = offset;
			this.size = size;
			this.mode = mode;
			init();
		} catch (IOException e) {
			throw e;
		}
	}

	private void init() {

	}

	private static final Unsafe Unsafe;
	private static final Method Mmap;
	private static final Method Unmmap;

	static {
		try {
			Unsafe = BootstrapUnsafe.Unsafe;
			Mmap = ReflectUtil.getMethod(FileChannelImpl.class, "map0", int.class, long.class, long.class);
			Unmmap = ReflectUtil.getMethod(FileChannelImpl.class, "unmap0", long.class, long.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private FileChannel channel;
	private FileChannel.MapMode mode;

	private long size;
	private long start;
	private long offset;

}
