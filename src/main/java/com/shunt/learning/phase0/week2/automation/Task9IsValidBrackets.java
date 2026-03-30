package com.shunt.learning.phase0.week2.automation;

/**
 * Task 9: Validate bracket sequence using array-based stack simulation.
 * Key insight: LIFO (Last-In-First-Out) structure matches bracket nesting semantics.
 * Real-world application: Syntax validation in compilers/IDEs.
 */
public class Task9IsValidBrackets {

    public static boolean isValidBrackets(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        char[] stack = new char[s.length()];
        int top = -1;   // -1 = empty stack

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack[++top] = c;   // push: increment then assign
            } else if (c == ')' || c == ']' || c == '}') {
                if (top == -1 || !matches(stack[top], c)) {
                    return false;   // empty stack or mismatch
                }
                top--;  // pop: decrement pointer
            }
            // Note: Non-bracket characters are ignored (not required by task)
        }
        return top == -1;   // stack must be empty at end
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')' ||
                open == '[' && close == ']' ||
                open == '{' && close == '}');
    }

    public static void main(String[] args) {
        System.out.println(isValidBrackets("()"));        // true
        System.out.println(isValidBrackets("()[]{}"));    // true
        System.out.println(isValidBrackets("(]"));        // false
        System.out.println(isValidBrackets("([)]"));      // false (critical test)
        System.out.println(isValidBrackets("{[]}"));      // true
        System.out.println(isValidBrackets("({[]})"));    // true
        System.out.println(isValidBrackets(""));          // true
        System.out.println(isValidBrackets(null));        // true
    }
}