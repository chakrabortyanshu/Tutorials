package com.example.project.docs.api;

import org.junit.jupiter.params.converter.TypedArgumentConverter;

/**
 * If the converter is only meant to convert one type to another, you can extend
 * TypedArgumentConverter to avoid boilerplate type checks.
 * 
 * @author ac185300
 *
 */
public class ToLengthArgumentConverter extends TypedArgumentConverter<String, Integer> {

	protected ToLengthArgumentConverter() {
		super(String.class, Integer.class);
	}

	@Override
	protected Integer convert(String source) {
		return source.length();
	}

}