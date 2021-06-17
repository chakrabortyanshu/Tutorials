package com.example.project.docs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.project.docs.api.ToStringArgumentConverter;

public class ParameterizedExplicitConverterDemo {

	@ParameterizedTest
	@EnumSource(ChronoUnit.class)
	void testWithExplicitArgumentConversion(
	        @ConvertWith(ToStringArgumentConverter.class) String argument) {

	    assertNotNull(ChronoUnit.valueOf(argument));
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "01.01.2017", "31.12.2017" })
	void testWithExplicitJavaTimeConverter(
	        @JavaTimeConversionPattern("dd.MM.yyyy") LocalDate argument) {

	    assertEquals(2017, argument.getYear());
	}
	
}
