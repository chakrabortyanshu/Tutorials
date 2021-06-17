package com.example.project.docs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static org.junit.jupiter.params.provider.EnumSource.Mode.MATCH_ALL;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.project.Book;
import com.example.project.Gender;
import com.example.project.Person;
import com.example.project.docs.api.CsvToPerson;
import com.example.project.docs.api.PersonAggregator;
import com.example.project.docs.api.TestInterfaceDynamicTestsDemo;

/**
 * https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests
 * 
 * @author ac185300
 *
 */
public class ParameterizedTestDemo implements TestInterfaceDynamicTestsDemo {

	@ParameterizedTest
	@CsvSource({ "Jane, Doe, FEMALE, 1990-05-20", "John, Doe, MALE, 1990-10-22" })
	void testWithArgumentsAccessor(ArgumentsAccessor arguments) {
		// An instance of ArgumentsAccessor is automatically injected into any parameter
		// of type ArgumentsAccessor.
		Person person = new Person(arguments.getString(0), arguments.getString(1), arguments.get(2, Gender.class),
				arguments.get(3, LocalDate.class));

		if (person.getFirstName().equals("Jane")) {
			assertEquals(Gender.FEMALE, person.getGender());
		} else {
			assertEquals(Gender.MALE, person.getGender());
		}
		assertEquals("Doe", person.getLastName());
		assertEquals(1990, person.getDateOfBirth().getYear());
	}

	@ParameterizedTest
	@ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
	void palindromes(String candidate) {
		assertTrue(isPalindrome(candidate));
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3 })
	void testWithValueSource(int argument) {
		assertTrue(argument > 0 && argument < 4);
	}

	@ParameterizedTest
	// @NullSource
	// @EmptySource
	@NullAndEmptySource
	@ValueSource(strings = { " ", "   ", "\t", "\n" })
	void nullEmptyAndBlankStrings(String text) {
		// Both variants of the nullEmptyAndBlankStrings(String) parameterized test
		// method result in six invocations: 1 for null, 1 for the empty string, and 4
		// for the explicit blank strings supplied via @ValueSource.
		assertTrue(text == null || text.trim().isEmpty());
	}

	@ParameterizedTest
	@EnumSource(ChronoUnit.class)
	void testWithEnumSource(TemporalUnit unit) {
		assertNotNull(unit);
	}

	@ParameterizedTest
	@EnumSource
	void testWithEnumSourceWithAutoDetection(ChronoUnit unit) {
		assertNotNull(unit);
	}

	@ParameterizedTest
	@EnumSource(names = { "DAYS", "HOURS" })
	void testWithEnumSourceInclude(ChronoUnit unit) {
		assertTrue(EnumSet.of(ChronoUnit.DAYS, ChronoUnit.HOURS).contains(unit));
	}

	@ParameterizedTest
	@EnumSource(mode = EXCLUDE, names = { "ERAS", "FOREVER" })
	void testWithEnumSourceExclude(ChronoUnit unit) {
		assertFalse(EnumSet.of(ChronoUnit.ERAS, ChronoUnit.FOREVER).contains(unit));
	}

	@ParameterizedTest
	@EnumSource(mode = MATCH_ALL, names = "^.*DAYS$")
	void testWithEnumSourceRegex(ChronoUnit unit) {
		assertTrue(unit.name().endsWith("DAYS"));
	}

	@ParameterizedTest
	@MethodSource("stringProvider")
	void testWithExplicitLocalMethodSource(String argument) {
		assertNotNull(argument);
	}

	static Stream<String> stringProvider() {
		return Stream.of("apple", "banana");
	}

	@ParameterizedTest
	@MethodSource
	void testWithDefaultLocalMethodSource(String argument) {
		assertNotNull(argument);
	}

	static Stream<String> testWithDefaultLocalMethodSource() {
		return Stream.of("apple", "banana");
	}

	@ParameterizedTest
	@MethodSource("range")
	void testWithRangeMethodSource(int argument) {
		assertNotEquals(9, argument);
	}

	static IntStream range() {
		return IntStream.range(0, 20).skip(10);
	}

	@ParameterizedTest
	@MethodSource("stringIntAndListProvider")
	void testWithMultiArgMethodSource(String str, int num, List<String> list) {
		assertEquals(5, str.length());
		assertTrue(num >= 1 && num <= 2);
		assertEquals(2, list.size());
	}

	static Stream<Arguments> stringIntAndListProvider() {
		return Stream.of(arguments("apple", 1, Arrays.asList("a", "b")),
				arguments("lemon", 2, Arrays.asList("x", "y")));
	}

	@ParameterizedTest
	@CsvSource({ "apple,         1", "banana,        2", "'lemon, lime', 0xF1" })
	void testWithCsvSource(String fruit, int rank) {
		assertNotNull(fruit);
		assertNotEquals(0, rank);
	}

	/**
	 * Example Input Resulting Argument List @CsvSource({ "apple, banana" })
	 * 
	 * "apple", "banana"
	 * 
	 * @CsvSource({ "apple, 'lemon, lime'" })
	 * 
	 * "apple", "lemon, lime"
	 * 
	 * @CsvSource({ "apple, ''" })
	 * 
	 * "apple", ""
	 * 
	 * @CsvSource({ "apple, " })
	 * 
	 * "apple", null
	 * 
	 * @CsvSource(value = { "apple, banana, NIL" }, nullValues = "NIL")
	 * 
	 *                  "apple", "banana", null
	 */

	@ParameterizedTest
	@CsvFileSource(files = "src/test/resources/two-column.csv", numLinesToSkip = 1)
	void testWithCsvFileSourceFromFile(String country, int reference) {
		assertNotNull(country);
		assertNotEquals(0, reference);
	}

	@ParameterizedTest
	@ValueSource(strings = "SECONDS")
	void testWithImplicitArgumentConversion(ChronoUnit argument) {
		assertNotNull(argument.name());
		// https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-argument-conversion-implicit
	}

	@ParameterizedTest
	@ValueSource(strings = "42 Cats")
	void testWithImplicitFallbackArgumentConversion(Book book) {
		assertEquals("42 Cats", book.getTitle());
	}

	@ParameterizedTest
	@CsvSource({ "Jane, Doe, FEMALE, 1990-05-20", "John, Doe, MALE, 1990-10-22" })
	void testWithArgumentsAggregator(@AggregateWith(PersonAggregator.class) Person person) {
		if (person.getFirstName().equals("Jane")) {
			assertEquals(Gender.FEMALE, person.getGender());
		} else {
			assertEquals(Gender.MALE, person.getGender());
		}
		assertEquals("Doe", person.getLastName());
		assertEquals(1990, person.getDateOfBirth().getYear());
	}

	/**
	 * If you find yourself repeatedly
	 * declaring @AggregateWith(MyTypeAggregator.class) for multiple parameterized
	 * test methods across your codebase, you may wish to create a custom composed
	 * annotation such as @CsvToMyType that is meta-annotated
	 * with @AggregateWith(MyTypeAggregator.class). The following example
	 * demonstrates this in action with a custom @CsvToPerson annotation.
	 * 
	 * @param person
	 */
	@ParameterizedTest
	@CsvSource({ "Jane, Doe, FEMALE, 1990-05-20", "John, Doe, MALE, 1990-10-22" })
	void testWithCustomAggregatorAnnotation(@CsvToPerson Person person) {
		assertEquals("Doe", person.getLastName());
	}

	/**
	 * you can customize invocation display names via the name attribute of
	 * the @ParameterizedTest annotation like in the following example.
	 * 
	 * Please note that name is a MessageFormat pattern. Thus, a single quote (')
	 * needs to be represented as a doubled single quote ('') in order to be
	 * displayed.
	 * 
	 * When including arguments in display names, their string representations are
	 * truncated if they exceed the configured maximum length. The limit is
	 * configurable via the junit.jupiter.params.displayname.argument.maxlength
	 * configuration parameter and defaults to 512 characters.
	 * 
	 * You may at will mix regular @Test methods and @ParameterizedTest methods
	 * within the same test class.
	 * 
	 * @param fruit
	 * @param rank
	 */
	@DisplayName("Display name of container")
	@ParameterizedTest(name = "{index} ==> the rank of ''{0}'' is {1}")
	@CsvSource({ "apple, 1", "banana, 2", "'lemon, lime', 3" })
	void testWithCustomDisplayNames(String fruit, int rank) {
		if ("apple".equals(fruit)) {
			assertEquals(1, rank);
		}
		if ("banana".equals(fruit)) {
			assertEquals(2, rank);
		}
		if ("lemon, lime".equals(fruit)) {
			assertEquals(3, rank);
		}
	}

	
}
