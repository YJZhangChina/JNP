package util.opt;

import java.lang.reflect.Field;

import sun.misc.Unsafe;
import util.ReflectUtil;

public class BootstrapUnsafe {

	public static sun.misc.Unsafe Unsafe;
	public static long BYTE_ARRAY_BASE_OFFSET;

	static {
		try {
			Field unsafeField = ReflectUtil.getField(sun.misc.Unsafe.class, "theUnsafe");
			Unsafe = (Unsafe) unsafeField.get(null);
			BYTE_ARRAY_BASE_OFFSET = Unsafe.arrayBaseOffset(byte[].class);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			// ignore
		}
	}

}
