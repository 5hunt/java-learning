package com.shunt.learning.phase0.week1.strings;

/**
 * Task 5: Check if string is palindrome (ignoring non-alphanumeric chars, case-insensitive).
 * Approach: Two pointers with in-place filtering (O(1) extra space).
 */
public class Task2IsPalindrome {

    /**
     * Checks whether the input string is a palindrome.
     * - Ignores case (compares in lowercase)
     * - Skips non-alphanumeric characters (spaces, punctuation)
     * - Null and empty strings are considered palindromes
     * <p>
     * Algorithm insight:
     * Two pointers move toward center, skipping invalid chars.
     * Inner while loops ensure pointers always land on valid chars
     * before comparison. Exit condition (left >= right) means all
     * valid pairs matched → palindrome.
     *
     * @param text input string (null-safe)
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindrome(String text) {
        if (text == null || text.isEmpty()) {
            return true;
        }

        String lowerText = text.toLowerCase();

        // Search boundaries: inclusive range [left, right]
        int left = 0;
        int right = text.length() - 1;

        // Continue while search space is non-empty
        while (left < right) {
            // Skip non-alphanumeric chars from left
            while (left < right && !Character.isLetterOrDigit(lowerText.charAt(left))) {
                left++;
            }
            // Skip non-alphanumeric chars from right
            while (left < right && !Character.isLetterOrDigit(lowerText.charAt(right))) {
                right--;
            }

            // Compare valid chars
            if (lowerText.charAt(left) != lowerText.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        // Test 1: Words palindrome
        String a = "A man a plan a canal Panama";
        System.out.println("Test 1 - String: " + "\"" + a + "\"" + ", Palindrome: " + isPalindrome(a)); // true

        // Test 2: Numbers palindrome
        String b = "12321";
        System.out.println("Test 2 - String: " + "\"" + b + "\"" + ", Palindrome: " + isPalindrome(b)); // true

        // Test 3: Words no palindrome
        String c = "race a car";
        System.out.println("Test 3 - String: " + "\"" + c + "\"" + ", Palindrome: " + isPalindrome(c)); // false

        // Test 4: One symbol palindrome
        String d = "x";
        System.out.println("Test 4 - String: " + "\"" + d + "\"" + ", Palindrome: " + isPalindrome(d)); // true

        // Test 5: Empty string palindrome
        String e = "";
        System.out.println("Test 5 - String: " + "\"" + e + "\"" + ", Palindrome: " + isPalindrome(e)); // true

        // Test 6: Null string palindrome
        String f = null;
        System.out.println("Test 6 - String: null, Palindrome: " + isPalindrome(f));    // true

        // Test 7: Only symbols
        String g = ".,!@#";
        System.out.println("Test 7 - String: " + "\"" + g + "\"" + ", Palindrome: " + isPalindrome(g)); // true
    }
}
