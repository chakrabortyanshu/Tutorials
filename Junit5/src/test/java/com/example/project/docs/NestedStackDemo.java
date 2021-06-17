package com.example.project.docs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Only non-static nested classes (i.e. inner classes) can serve as @Nested test
 * classes. Nesting can be arbitrarily deep, and those inner classes are
 * considered to be full members of the test class family with one
 * exception: @BeforeAll and @AfterAll methods do not work by default. The
 * reason is that Java does not allow static members in inner classes. However,
 * this restriction can be circumvented by annotating a @Nested test class
 * with @TestInstance(Lifecycle.PER_CLASS)
 * 
 * @author ac185300
 *
 */
@DisplayName("A stack")
class NestedStackDemo {

	Stack<Object> stack;

	@Test
	@DisplayName("is instantiated with new Stack()")
	void isInstantiatedWithNew() {
		new Stack<>();
	}

	@Nested
	@DisplayName("when new")
	class WhenNew {

		@BeforeEach
		void createNewStack() {
			stack = new Stack<>();
		}

		@Test
		@DisplayName("is empty")
		void isEmpty() {
			assertTrue(stack.isEmpty());
		}

		@Test
		@DisplayName("throws EmptyStackException when popped")
		void throwsExceptionWhenPopped() {
			assertThrows(EmptyStackException.class, stack::pop);
		}

		@Test
		@DisplayName("throws EmptyStackException when peeked")
		void throwsExceptionWhenPeeked() {
			assertThrows(EmptyStackException.class, stack::peek);
		}

		@Nested
		@DisplayName("after pushing an element")
		class AfterPushing {

			String anElement = "an element";

			@BeforeEach
			void pushAnElement() {
				stack.push(anElement);
			}

			@Test
			@DisplayName("it is no longer empty")
			void isNotEmpty() {
				assertFalse(stack.isEmpty());
			}

			@Test
			@DisplayName("returns the element when popped and is empty")
			void returnElementWhenPopped() {
				assertEquals(anElement, stack.pop());
				assertTrue(stack.isEmpty());
			}

			@Test
			@DisplayName("returns the element when peeked but remains not empty")
			void returnElementWhenPeeked() {
				assertEquals(anElement, stack.peek());
				assertFalse(stack.isEmpty());
			}
		}
	}
}