package com.example.project;

public class StringUtils {

	
	public static boolean isPalindrome(String text) {
		String reverse = new StringBuilder().append(text).reverse().toString();
		return text.equals(reverse);
	}
	
}
