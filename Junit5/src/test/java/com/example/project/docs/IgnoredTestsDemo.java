package com.example.project.docs;

class IgnoredTestsDemo {

	/**
	 * To use @Ignore with JUnit Jupiter based tests, configure a test dependency on
	 * the junit-jupiter-migrationsupport module in your build and then annotate
	 * your test class with @ExtendWith(IgnoreCondition.class)
	 * or @EnableJUnit4MigrationSupport (which automatically registers the
	 * IgnoreCondition along with Limited JUnit 4 Rule Support). The IgnoreCondition
	 * is an ExecutionCondition that disables test classes or test methods that are
	 * annotated with @Ignore.
	 * 
	 * import org.junit.Ignore; import org.junit.jupiter.api.Test; import
	 * org.junit.jupiter.migrationsupport.EnableJUnit4MigrationSupport;
	 * 
	 * // @ExtendWith(IgnoreCondition.class)
	 * 
	 * @EnableJUnit4MigrationSupport class IgnoredTestsDemo {
	 * 
	 * @Ignore
	 * @Test void testWillBeIgnored() { }
	 * 
	 * @Test void testWillBeExecuted() { } }
	 */
}