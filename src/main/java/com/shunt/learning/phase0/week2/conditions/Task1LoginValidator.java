package com.shunt.learning.phase0.week2.conditions;

/**
 * Task 1: Login validator for user registration service.
 * Validation order: cheap checks first → expensive checks last.
 * Principle: Format validation before semantic validation.
 */
public class Task1LoginValidator {

    /**
     * Validates user login according to business rules:
     * 1. Not null/empty
     * 2. Length 3-20 characters
     * 3. Does not start with digit
     * 4. Contains only [a-zA-Z0-9_]
     * 5. Not reserved word (admin, root, system)
     *
     * Optimization insight:
     * - Length check before character access (charAt requires bounds check)
     * - Format validation (steps 1-4) before semantic (step 5)
     * - Early exit on first failure (no unnecessary work)
     *
     * @param login candidate login string
     * @return true if valid, false otherwise
     */
    public static boolean isValidLogin(String login) {
        // Step 1: Null/empty check (cheapest)
        if (login == null || login.isEmpty()) {
            return false;
        }

        // Step 2: Length check (cheap, avoids char access for invalid lengths)
        if (login.length() < 3 || login.length() > 20) {
            return false;
        }

        // Step 3: First char not digit (requires length ≥ 1, already guaranteed)
        if (Character.isDigit(login.charAt(0))) {
            return false;
        }

        // Step 4: Character whitelist (expensive — full scan)
        for (int i = 0; i < login.length(); i++) {
            char c = login.charAt(i);
            if (!((c >= '0' && c <= '9')
                    || (c >= 'a' && c <= 'z')
                    || (c >= 'A' && c <= 'Z')
                    || c == '_')) {
                return false;
            }
        }

        // Step 5: Semantic validation (reserved words) after format is confirmed
        String lowerLogin = login.toLowerCase();
        return !("admin".equals(lowerLogin)
                || "root".equals(lowerLogin)
                || "system".equals(lowerLogin));
    }

    public static void main(String[] args) {
        System.out.println(isValidLogin("john_doe"));   // true
        System.out.println(isValidLogin("j1"));         // false (too short)
        System.out.println(isValidLogin("123user"));    // false (starts with digit)
        System.out.println(isValidLogin("user@name"));  // false (invalid char)
        System.out.println(isValidLogin("admin"));      // false (reserved)
        System.out.println(isValidLogin("Admin"));      // false (case-insensitive)
        System.out.println(isValidLogin(""));           // false (empty)
        System.out.println(isValidLogin(null));         // false
        System.out.println(isValidLogin("a_b_c"));      // true
    }
}