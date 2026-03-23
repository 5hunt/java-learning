package com.shunt.learning.phase0.week2.datavalidator;

import java.util.Scanner;

/**
 * UserDataValidator: Consolidates Week 2 concepts (loops, conditions, regex).
 * Architecture insight: Validation pipeline with early exits + error aggregation.
 * Production note: Real backend would receive DTO/JSON, not console input.
 */
public class UserDataValidator {

    private static final String PASSWORD_PATTERN =
            "^(?=.*\\d)(?!.*\\s)(?=.*[a-z])(?=.*[A-Z]).{8,}$";

    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private static final String[] RESERVED_LOGINS =
            {"admin", "root", "system"};

    /**
     * Validates all user fields and aggregates errors.
     * Order: cheap checks first (age) → expensive last (email regex).
     * Returns "OK" if valid, or comma-separated error list.
     */
    public static String validateUser(String login, String password, String email, int age) {
        StringBuilder errors = new StringBuilder();

        if (!isValidAge(age)) {
            appendError(errors, "age must be 18-120");
        }

        if (!isValidLogin(login)) {
            appendError(errors, "invalid login");
        }

        if (!isValidPassword(password)) {
            appendError(errors, "weak password");
        }

        if (!isValidEmail(email)) {
            appendError(errors, "invalid email");
        }

        return errors.isEmpty() ? "OK" : errors.toString();
    }

    private static void appendError(StringBuilder errors, String message) {
        if (!errors.isEmpty()) {
            errors.append(", ");
        }
        errors.append(message);
    }

    public static boolean isValidAge(int age) {
        return age >= 18 && age <= 120;
    }

    public static boolean isValidLogin(String login) {
        if (login == null
                || login.isEmpty()
                || login.length() < 3
                || login.length() > 20) {
            return false;
        }

        if (Character.isDigit(login.charAt(0))) {
            return false;
        }

        for (int i = 0; i < login.length(); i++) {
            char c = login.charAt(i);
            if (!((c >= '0' && c <= '9')
                    || (c >= 'a' && c <= 'z')
                    || (c >= 'A' && c <= 'Z')
                    || c == '_')) {
                return false;
            }
        }

        String lower = login.toLowerCase();
        for (String reserved : RESERVED_LOGINS) {
            if (lower.equals(reserved)) {
                return false;
            }
        }
        return true;
    }

    // Null and empty are treated as part of a contract
    public static boolean isValidPassword(String password) {
        return (password != null
                && !password.isEmpty()
                && password.matches(PASSWORD_PATTERN));
    }

    public static boolean isValidEmail(String email) {
        return (email != null
                && !email.isEmpty()
                && email.matches(EMAIL_PATTERN));
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Enter user info ===");

            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Login: ");
            String login = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.println("> " + validateUser(login, password, email, age));
        } catch (NumberFormatException e) {
            System.err.println("-> ERROR: Age must be a number");
        }
    }
}