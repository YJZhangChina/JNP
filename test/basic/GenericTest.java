package basic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class GenericTest {
	
	@Test
	public void test() {
		Class<?> clsA = new ArrayList<Float>().getClass();
		Class<?> clsB = new ArrayList<Double>().getClass();
		assertEquals(clsA, clsB); // True
	}

}
