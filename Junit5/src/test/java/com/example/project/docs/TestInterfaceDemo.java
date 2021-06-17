package com.example.project.docs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.project.docs.api.TestInterfaceDynamicTestsDemo;
import com.example.project.docs.api.TestLifecycleLogger;
import com.example.project.docs.api.TimeExecutionLogger;

public class TestInterfaceDemo implements TestLifecycleLogger, TimeExecutionLogger, TestInterfaceDynamicTestsDemo {

	@Test
	void isEqualValue() {
		assertEquals(1, "a".length(), "is always equal");
	}

}