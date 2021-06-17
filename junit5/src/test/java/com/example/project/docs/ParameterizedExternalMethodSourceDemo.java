package com.example.project.docs;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

class ParameterizedExternalMethodSourceDemo {

	@ParameterizedTest
	@MethodSource("com.example.project.docs.StringsProviders#tinyStrings")
	void testWithExternalMethodSource(String tinyString) {
		// test with tiny string
	}

	@ParameterizedTest
	@ArgumentsSource(MyArgumentsProvider.class)
	void testWithArgumentsSource(String argument) {
		assertNotNull(argument);
	}

}

class StringsProviders {

	static Stream<String> tinyStrings() {
		return Stream.of(".", "oo", "OOO");
	}
}

class MyArgumentsProvider implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
		return Stream.of("apple", "banana").map(Arguments::of);
	}
}