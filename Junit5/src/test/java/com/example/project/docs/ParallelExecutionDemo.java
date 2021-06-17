package com.example.project.docs;

public class ParallelExecutionDemo {

	/**
	 * https://junit.org/junit5/docs/current/user-guide/#writing-tests-parallel-execution
	 * 
	 * By default, JUnit Jupiter tests are run sequentially in a single thread.
	 * Running tests in parallel — for example, to speed up execution — is available
	 * as an opt-in feature since version 5.3. To enable parallel execution, set the
	 * junit.jupiter.execution.parallel.enabled configuration parameter to true —
	 * for example, in junit-platform.properties (see Configuration Parameters
	 * {https://junit.org/junit5/docs/current/user-guide/#running-tests-config-params}
	 * for other options).
	 * 
	 * By default, JUnit Jupiter tests are run sequentially in a single thread.
	 * Running tests in parallel — for example, to speed up execution — is available
	 * as an opt-in feature since version 5.3. To enable parallel execution, set the
	 * junit.jupiter.execution.parallel.enabled configuration parameter to true —
	 * for example, in junit-platform.properties (see Configuration Parameters for
	 * other options).
	 * 
	 * Please note that enabling this property is only the first step required to
	 * execute tests in parallel. If enabled, test classes and methods will still be
	 * executed sequentially by default. Whether or not a node in the test tree is
	 * executed concurrently is controlled by its execution mode. The following two
	 * modes are available.
	 * 
	 * SAME_THREAD Force execution in the same thread used by the parent. For
	 * example, when used on a test method, the test method will be executed in the
	 * same thread as any @BeforeAll or @AfterAll methods of the containing test
	 * class.
	 * 
	 * CONCURRENT Execute concurrently unless a resource lock forces execution in
	 * the same thread.
	 * 
	 * By default, nodes in the test tree use the SAME_THREAD execution mode. You
	 * can change the default by setting the
	 * junit.jupiter.execution.parallel.mode.default configuration parameter.
	 * Alternatively, you can use the @Execution annotation to change the execution
	 * mode for the annotated element and its subelements (if any) which allows you
	 * to activate parallel execution for individual test classes, one by one.
	 * 
	 * Configuration parameters to execute all tests in parallel
	 * junit.jupiter.execution.parallel.enabled = true
	 * junit.jupiter.execution.parallel.mode.default = concurrent
	 * 
	 * Configuration parameters to execute top-level classes sequentially but their
	 * methods in parallel junit.jupiter.execution.parallel.enabled = true
	 * junit.jupiter.execution.parallel.mode.default = concurrent
	 * junit.jupiter.execution.parallel.mode.classes.default = same_thread
	 * 
	 * 
	 * Configuration parameters for higher concurrent performance.
	 * methods in parallel junit.jupiter.execution.parallel.enabled = true
	 * junit.jupiter.execution.parallel.mode.default = concurrent
	 * junit.jupiter.execution.parallel.mode.classes.default = concurrent
	 * 
	 * 
	 * 
	 * 
	 */
}
