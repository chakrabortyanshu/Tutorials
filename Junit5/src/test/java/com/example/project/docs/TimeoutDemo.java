package com.example.project.docs;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Contrary to the assertTimeoutPreemptively() assertion, the execution of the
 * annotated method proceeds in the main thread of the test. If the timeout is
 * exceeded, the main thread is interrupted from another thread. This is done to
 * ensure interoperability with frameworks such as Spring that make use of
 * mechanisms that are sensitive to the currently running thread — for example,
 * ThreadLocal transaction management.
 * 
 * To apply the same timeout to all test methods within a test class and all of
 * its @Nested classes, you can declare the @Timeout annotation at the class
 * level. It will then be applied to all test, test factory, and test template
 * methods within that class and its @Nested classes unless overridden by
 * a @Timeout annotation on a specific method or @Nested class. Please note
 * that @Timeout annotations declared at the class level are not applied to
 * lifecycle methods.
 * 
 * 
 * If you need more control over polling intervals and greater flexibility with
 * asynchronous tests, consider using a dedicated library such as Awaitility.
 * {https://github.com/awaitility/awaitility}
 * 
 * 
 * 
 * @author ac185300
 *
 */
public class TimeoutDemo {

	@BeforeEach
	@Timeout(5)
	void setUp() {
		// fails if execution time exceeds 5 seconds
	}

	@Test
	@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
	void failsIfExecutionTimeExceeds100Milliseconds() {
		// fails if execution time exceeds 100 milliseconds
	}

	@Test
	@Timeout(5) // Poll at most 5 seconds
	void pollUntil() throws InterruptedException {
		// while (asynchronousResultNotAvailable()) {
		Thread.sleep(250); // custom poll interval
		// }
		// Obtain the asynchronous result and perform assertions
	}

}
