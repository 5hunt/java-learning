package com.shunt.learning.phase0.week2.automation;

/**
 * Task 5: Remove all whitespace characters from string.
 * Key insight: StringBuilder for mutable string building + isWhitespace for robustness.
 */
public class Task5RemoveSpaces {

    public static String removeSpaces(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!Character.isWhitespace(c)) {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeSpaces("hello world"));   // "helloworld"
        System.out.println(removeSpaces("  a  b  c  "));   // "abc"
        System.out.println(removeSpaces("no spaces"));     // "nospaces"
        System.out.println(removeSpaces("a\tb\nc"));       // "abc"
        System.out.println(removeSpaces(""));              // ""
        System.out.println(removeSpaces(null));            // ""
    }
}