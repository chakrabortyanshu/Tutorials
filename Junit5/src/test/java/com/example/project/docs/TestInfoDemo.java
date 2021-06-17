package com.example.project.docs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * 2.12. Dependency Injection for Constructors and Methods In all prior JUnit
 * versions, test constructors or methods were not allowed to have parameters
 * (at least not with the standard Runner implementations). As one of the major
 * changes in JUnit Jupiter, both test constructors and methods are now
 * permitted to have parameters. This allows for greater flexibility and enables
 * Dependency Injection for constructors and methods.
 * 
 * ParameterResolver defines the API for test extensions that wish to
 * dynamically resolve parameters at runtime. If a test class constructor, a
 * test method, or a lifecycle method (see Test Classes and Methods) accepts a
 * parameter, the parameter must be resolved at runtime by a registered
 * ParameterResolver.
 * 
 * There are currently three built-in resolvers that are registered
 * automatically.
 * 
 * TestInfoParameterResolver: if a constructor or method parameter is of type
 * TestInfo, the TestInfoParameterResolver will supply an instance of TestInfo
 * corresponding to the current container or test as the value for the
 * parameter. The TestInfo can then be used to retrieve information about the
 * current container or test such as the display name, the test class, the test
 * method, and associated tags. The display name is either a technical name,
 * such as the name of the test class or test method, or a custom name
 * configured via @DisplayName.
 * 
 * TestInfo acts as a drop-in replacement for the TestName rule from JUnit 4.
 * The following demonstrates how to have TestInfo injected into a test
 * constructor, @BeforeEach method, and @Test method.
 * 
 * @author ac185300
 *
 */

@DisplayName("TestInfo Demo")
class TestInfoDemo {

	TestInfoDemo(TestInfo testInfo) {
		assertEquals("TestInfo Demo", testInfo.getDisplayName());
	}

	@BeforeEach
	void init(TestInfo testInfo) {
		String displayName = testInfo.getDisplayName();
		assertTrue(displayName.equals("TEST 1") || displayName.equals("test2()"));
	}

	@Test
	@DisplayName("TEST 1")
	@Tag("my-tag")
	void test1(TestInfo testInfo) {
		assertEquals("TEST 1", testInfo.getDisplayName());
		assertTrue(testInfo.getTags().contains("my-tag"));
	}

	@Test
	void test2() {
	}

}