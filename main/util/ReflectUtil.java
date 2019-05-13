package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtil {

	public static Method getMethod(Class<?> clazz, String name, Class<?>... params) throws NoSuchMethodException {
		Method method = clazz.getDeclaredMethod(name, params);
		method.setAccessible(true);
		return method;
	}

	public static Field getField(Class<?> clazz, String name) throws NoSuchFieldException {
		Field field = clazz.getDeclaredField(name);
		field.setAccessible(true);
		return field;
	}

	@SafeVarargs
	public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<T>... params) throws NoSuchMethodException {
		Constructor<T> constructor = clazz.getConstructor(params);
		constructor.setAccessible(true);
		return constructor;
	}

}
