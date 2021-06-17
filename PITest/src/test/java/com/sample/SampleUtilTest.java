package com.sample;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SampleUtilTest {

	
	@DisplayName("Test Add Method")
	@Test
	final void testAdd() {
		SampleUtil util = new SampleUtil();
		
		assertEquals( 2,util.add(1, 1), "Adding two values.");
	}
	
}
