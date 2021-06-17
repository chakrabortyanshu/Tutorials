package com.example.project.docs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.example.project.docs.api.RandomParametersExtension;
import com.example.project.docs.api.RandomParametersExtension.Random;

/**
 * Other parameter resolvers must be explicitly enabled by registering
 * appropriate extensions via @ExtendWith.
 * 
 * Check out the RandomParametersExtension
 * {https://github.com/junit-team/junit5-samples/blob/r5.7.0/junit5-jupiter-extensions/src/main/java/com/example/random/RandomParametersExtension.java}
 * for an example of a custom ParameterResolver. While not intended to be
 * production-ready, it demonstrates the simplicity and expressiveness of both
 * the extension model and the parameter resolution process.
 * MyRandomParametersTest demonstrates how to inject random values into @Test
 * methods.
 * 
 * For real-world use cases, check out the source code for the MockitoExtension
 * and the SpringExtension.
 * 
 * When the type of the parameter to inject is the only condition for your
 * ParameterResolver, you can use the generic TypeBasedParameterResolver base
 * class. The supportsParameters method is implemented behind the scenes and
 * supports parameterized types.
 * 
 * @author ac185300
 *
 */

@ExtendWith(RandomParametersExtension.class)
class MyRandomParametersTest {

	@Test
	void injectsInteger(@Random int i, @Random int j) {
		assertNotEquals(i, j);
	}

	@Test
	void injectsDouble(@Random double d) {
		assertEquals(0.0, d, 1.0);
	}

}