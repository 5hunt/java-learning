package com.shunt.learning.phase0.week2.regex;

/**
 * Task 1: Password validator using regex with lookahead assertions.
 * Security requirements:
 * - Minimum 8 characters
 * - At least one digit
 * - At least one lowercase letter
 * - At least one uppercase letter
 * - No whitespace characters allowed
 */
public class Task1PasswordValidator {

    // Precompiled pattern for better performance in production
    private static final String PASSWORD_PATTERN =
            "^(?=.*\\d)(?!.*\\s)(?=.*[a-z])(?=.*[A-Z]).{8,}$";

    /**
     * Validates password against security policy.
     * Uses regex with positive/negative lookaheads for independent checks.
     *
     * Pattern breakdown:
     * ^              - start of string
     * (?=.*\\d)      - positive lookahead: at least one digit anywhere
     * (?!.*\\s)      - negative lookahead: no whitespace anywhere
     * (?=.*[a-z])    - positive lookahead: at least one lowercase letter
     * (?=.*[A-Z])    - positive lookahead: at least one uppercase letter
     * .{8,}          - main pattern: minimum 8 characters of any type
     * $              - end of string
     *
     * @param password candidate password
     * @return true if meets all security requirements
     */
    public static boolean isValidPassword(String password) {
        return password != null && !password.isEmpty() && password.matches(PASSWORD_PATTERN);
    }

    public static void main(String[] args) {
        // Valid passwords
        System.out.println(isValidPassword("Password123"));     // true

        // Invalid: missing uppercase
        System.out.println(isValidPassword("password123"));     // false (no uppercase)

        // Invalid: missing lowercase
        System.out.println(isValidPassword("PASSWORD123"));     // false (no lowercase)

        // Invalid: too short
        System.out.println(isValidPassword("Pass123"));         // false (less than 8 characters)

        // Invalid: contains whitespace
        System.out.println(isValidPassword("Password 123"));    // false (there is a space)

        // Invalid: no letters
        System.out.println(isValidPassword("!@#$%123"));        // false (characters only)

        // Edge cases
        System.out.println(isValidPassword(null));              // false
        System.out.println(isValidPassword(""));                // false
    }
}