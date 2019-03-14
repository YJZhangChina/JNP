package pattern;

public class ReflectUtil {

    public static Object newInstance(String className) {
        Object instance = null;
        try {
            Class<?> cls = Class.forName(className);
            instance = newInstance(cls);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public static Object newInstance(Class<?> cls) {
        Object instance = null;
        try {
            instance = cls.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return instance;
    }

}
