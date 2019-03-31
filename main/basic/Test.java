package basic;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) throws Exception {
		List l = new ArrayList();
		l.add(1);
		l.add("String");

		String o = (String) l.get(1);
		System.out.println(o.length());
//		ClassLoaderTemplate templateA = new ClassLoaderTemplate("D:/Projects/Java/Test.class");
//		ClassLoaderTemplate templateB = new ClassLoaderTemplate("D:/Projects/Java/Test.class");
//		Class<?> klassA = templateA.getClass();
//		Class<?> klassB = templateB.getClass();
//		Object a = klassA.newInstance();
//		Object b = klassB.newInstance();
//		a = (Class<?>) b;
//		
//		Thread.State state;
	}

}
