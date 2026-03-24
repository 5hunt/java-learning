package com.shunt.learning.phase0.week2.consolidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Task 2: Precise understanding of matches() vs find().
 * <p>
 * Core insight:
 * - String.matches() = full string match (implicit ^ and $)
 * - Matcher.find() = partial match anywhere in string
 * <p>
 * Production note: Never add explicit ^/$ to String.matches() — it's redundant.
 */
public class Task2PhoneValidation {

    // Pattern without capturing groups (we only check existence, not extract)
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("\\+7\\s?\\d{3}\\s?\\d{3}-?\\d{2}-?\\d{2}");

    /**
     * Checks if ENTIRE string is a phone number.
     * Uses String.matches() which implicitly requires full string match (^...$).
     */
    public static boolean isFullPhone(String text) {
        return text != null
                && text.matches("\\+7\\s?\\d{3}\\s?\\d{3}-?\\d{2}-?\\d{2}");
    }

    /**
     * Checks if phone number exists ANYWHERE in string.
     * Uses Matcher.find() which scans for partial matches.
     */
    public static boolean containsPhone(String text) {
        if (text == null) {
            return false;
        }

        Matcher matcher = PHONE_PATTERN.matcher(text);
        return matcher.find();
    }

    public static void main(String[] args) {
        // Test case 1: entire string must be phone
        System.out.println("FullPhone:");
        System.out.println(isFullPhone("+7 999 123-45-67"));            // true
        System.out.println(isFullPhone("+79991234567"));                // true
        System.out.println(isFullPhone("+7 999 123-45-67 extra"));      // false
        System.out.println(isFullPhone(""));                            // false
        System.out.println(isFullPhone(null));                          // false
        System.out.println();

        // Test case 2: phone anywhere in text
        System.out.println("ContainsPhone:");
        System.out.println(containsPhone("Call me: +7 999 123-45-67 tomorrow"));    // true
        System.out.println(containsPhone("Call me: +79991234567 tomorrow"));        // true
        System.out.println(containsPhone("+79991234567 extra"));                    // true
        System.out.println(containsPhone(""));                                      // false
        System.out.println(containsPhone(null));                                    // false
    }
}