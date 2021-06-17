package com.example.project.docs;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

/**
 * RepetitionInfoParameterResolver: if a method parameter in
 * a @RepeatedTest, @BeforeEach, or @AfterEach method is of type RepetitionInfo,
 * the RepetitionInfoParameterResolver will supply an instance of
 * RepetitionInfo. RepetitionInfo can then be used to retrieve information about
 * the current repetition and the total number of repetitions for the
 * corresponding @RepeatedTest. Note, however, that
 * RepetitionInfoParameterResolver is not registered outside the context of
 * a @RepeatedTest. See Repeated Test Examples.
 * 
 * TestReporterParameterResolver: if a constructor or method parameter is of
 * type TestReporter, the TestReporterParameterResolver will supply an instance
 * of TestReporter. The TestReporter can be used to publish additional data
 * about the current test run. The data can be consumed via the
 * reportingEntryPublished() method in a TestExecutionListener, allowing it to
 * be viewed in IDEs or included in reports.
 * 
 * In JUnit Jupiter you should use TestReporter where you used to print
 * information to stdout or stderr in JUnit 4.
 * Using @RunWith(JUnitPlatform.class) will output all reported entries to
 * stdout. In addition, some IDEs print report entries to stdout or display them
 * in the user interface for test results.
 * 
 * @author ac185300
 *
 */
public class TestReporterDemo {
	@Test
	void reportSingleValue(TestReporter testReporter) {
		testReporter.publishEntry("a status message");
	}

	@Test
	void reportKeyValuePair(TestReporter testReporter) {
		testReporter.publishEntry("a key", "a value");
	}

	@Test
	void reportMultipleKeyValuePairs(TestReporter testReporter) {
		Map<String, String> values = new HashMap<>();
		values.put("user name", "dk38");
		values.put("award year", "1974");

		testReporter.publishEntry(values);
	}

}
