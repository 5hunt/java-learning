package com.shunt.learning.phase0.week2.automation;

/**
 * Task 6: Find substring index without library methods.
 * Key insight: Nested loops with index arithmetic (i for text start, j for pattern offset).
 * Real-world note: Never implement this manually in production — use String.indexOf().
 */
public class Task6FindSubstring {

    public static int findSubstring(String text, String pattern) {
        // Null-safe implementation (unlike String.indexOf() which throws NPE on null)
        if (text == null || pattern == null) {
            return -1;
        }
        if (pattern.isEmpty()) {
            return 0;   // empty pattern matches at start
        }
        if (text.isEmpty() || pattern.length() > text.length()) {
            return -1;
        }

        // Brute-force search
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            if (text.charAt(i) == pattern.charAt(0)) {
                boolean match = true;
                for (int j = 1; j < pattern.length(); j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findSubstring("hello world", "world"));  // 6
        System.out.println(findSubstring("hello world", "xyz"));    // -1
        System.out.println(findSubstring("aaaa", "aa"));            // 0 (первое вхождение)
        System.out.println(findSubstring("abc", ""));               // 0
        System.out.println(findSubstring("", "a"));                 // -1
        System.out.println(findSubstring(null, "a"));               // -1
    }
}