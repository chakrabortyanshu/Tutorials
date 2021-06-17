package com.example.project.docs;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class TestInstanceLifecycleDemo {

	/**
	 * In order to allow individual test methods to be executed in isolation and to
	 * avoid unexpected side effects due to mutable test instance state, JUnit
	 * creates a new instance of each test class before executing each test method
	 * (see Test Classes and Methods). This "per-method" test instance lifecycle is
	 * the default behavior in JUnit Jupiter and is analogous to all previous
	 * versions of JUnit.
	 * 
	 * Please note that the test class will still be instantiated if a given test
	 * method is disabled via a condition (e.g., @Disabled, @DisabledOnOs, etc.)
	 * even when the "per-method" test instance lifecycle mode is active. If you
	 * would prefer that JUnit Jupiter execute all test methods on the same test
	 * instance, annotate your test class with @TestInstance(Lifecycle.PER_CLASS).
	 * When using this mode, a new test instance will be created once per test
	 * class. Thus, if your test methods rely on state stored in instance variables,
	 * you may need to reset that state in @BeforeEach or @AfterEach methods.
	 * 
	 * The "per-class" mode has some additional benefits over the default
	 * "per-method" mode. Specifically, with the "per-class" mode it becomes
	 * possible to declare @BeforeAll and @AfterAll on non-static methods as well as
	 * on interface default methods. The "per-class" mode therefore also makes it
	 * possible to use @BeforeAll and @AfterAll methods in @Nested test classes.
	 * 
	 * If you are authoring tests using the Kotlin programming language, you may
	 * also find it easier to implement @BeforeAll and @AfterAll methods by
	 * switching to the "per-class" test instance lifecycle mode.
	 */

	/**
	 * 2.10.1. Changing the Default Test Instance Lifecycle If a test class or test
	 * interface is not annotated with @TestInstance, JUnit Jupiter will use a
	 * default lifecycle mode. The standard default mode is PER_METHOD; however, it
	 * is possible to change the default for the execution of an entire test plan.
	 * To change the default test instance lifecycle mode, set the
	 * junit.jupiter.testinstance.lifecycle.default configuration parameter to the
	 * name of an enum constant defined in TestInstance.Lifecycle, ignoring case.
	 * This can be supplied as a JVM system property, as a configuration parameter
	 * in the LauncherDiscoveryRequest that is passed to the Launcher, or via the
	 * JUnit Platform configuration file (see Configuration Parameters for details).
	 * 
	 * For example, to set the default test instance lifecycle mode to
	 * Lifecycle.PER_CLASS, you can start your JVM with the following system
	 * property.
	 * 
	 * -Djunit.jupiter.testinstance.lifecycle.default=per_class
	 * 
	 * Note, however, that setting the default test instance lifecycle mode via the
	 * JUnit Platform configuration file is a more robust solution since the
	 * configuration file can be checked into a version control system along with
	 * your project and can therefore be used within IDEs and your build software.
	 * 
	 * To set the default test instance lifecycle mode to Lifecycle.PER_CLASS via
	 * the JUnit Platform configuration file, create a file named
	 * junit-platform.properties in the root of the class path (e.g.,
	 * src/test/resources) with the following content.
	 * 
	 * junit.jupiter.testinstance.lifecycle.default = per_class
	 * 
	 * Changing the default test instance lifecycle mode can lead to unpredictable
	 * results and fragile builds if not applied consistently. For example, if the
	 * build configures "per-class" semantics as the default but tests in the IDE
	 * are executed using "per-method" semantics, that can make it difficult to
	 * debug errors that occur on the build server. It is therefore recommended to
	 * change the default in the JUnit Platform configuration file instead of via a
	 * JVM system property.
	 */
}
