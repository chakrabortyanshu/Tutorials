package com.example.project.docs;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * 2.8.1. Syntax Rules for Tags A tag must not be null or blank.
 * 
 * A trimmed tag must not contain whitespace.
 * 
 * A trimmed tag must not contain ISO control characters.
 * 
 * A trimmed tag must not contain any of the following reserved characters.
 * 
 * ,: comma
 * 
 * (: left parenthesis
 * 
 * ): right parenthesis
 * 
 * &: ampersand
 * 
 * |: vertical bar
 * 
 * !: exclamation point
 * 
 * In the above context, "trimmed" means that leading and trailing whitespace
 * characters have been removed.
 * 
 * @author ac185300
 *
 */

@Tag("fast")
@Tag("model")
class TaggingDemo {

	@Test
	@Tag("taxes")
	void testingTaxCalculation() {
	}

}