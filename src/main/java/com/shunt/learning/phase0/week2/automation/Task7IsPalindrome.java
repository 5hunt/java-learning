package com.shunt.learning.phase0.week2.automation;

/**
 * Task 7: Check if string is palindrome (ignoring non-alphanumeric chars and case).
 * Key insight: Two pointers must BOTH stand on valid chars before comparison.
 */
public class Task7IsPalindrome {

    public static boolean isPalindrome(String text) {
        if (text == null || text.isEmpty()) {
            return true;
        }

        String lower = text.toLowerCase();
        int left = 0;
        int right = lower.length() - 1;

        while (left < right) {
            // Skip non-letters/numbers on the left
            while (left < right && !Character.isLetterOrDigit(lower.charAt(left))) {
                left++;
            }

            // Skip non-letters/numbers on the right
            while (left < right && !Character.isLetterOrDigit(lower.charAt(right))) {
                right--;
            }

            // Now both pointers on letters/numbers (or met)
            if (lower.charAt(left) != lower.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("racecar"));                        // true
        System.out.println(isPalindrome("A man, a plan, a canal Panama"));  // true
        System.out.println(isPalindrome("hello"));                          // false
        System.out.println(isPalindrome("a  a"));                           // true
        System.out.println(isPalindrome("a b"));                            // false
        System.out.println(isPalindrome("  "));                             // true
        System.out.println(isPalindrome(""));                               // true
        System.out.println(isPalindrome(null));                             // true
    }
}