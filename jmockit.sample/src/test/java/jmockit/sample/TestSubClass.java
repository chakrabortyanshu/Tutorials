package jmockit.sample;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mockit.Mocked;

public class TestSubClass {

	private SubClass subClass;

	@Test
	public void shouldGetData(@Mocked final SuperClass superClass) {
		// https://www.youtube.com/watch?v=EcxgfWML97M
		// When you don't want to initialize SuperClass.
		subClass = new SubClass("Data");
		assertEquals("Data", subClass.getData());
	}

}
