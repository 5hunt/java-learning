package com.shunt.learning.phase0.week2.conditions;

/**
 * Task 2: Payment validator with short-circuit evaluation.
 * Optimization principle: cheap checks first → expensive checks last.
 * Safety principle: null check always first to prevent NPE.
 */
public class Task2PaymentValidator {

    /**
     * Validates whether a payment can be processed.
     * Short-circuit evaluation guarantees:
     * - No NullPointerException (sender != null checked first)
     * - No unnecessary method calls (expensive checks skipped on first failure)
     *
     * Check order rationale:
     * 1. Null check (safety prerequisite)
     * 2. Amount > 0 (cheap arithmetic, catches invalid inputs early)
     * 3. Daily limit (cheap arithmetic, business rule)
     * 4. Account active (method call)
     * 5. Sufficient balance (method call + comparison, most expensive)
     *
     * @param sender     account initiating payment (may be null)
     * @param amount     transaction amount
     * @param dailyLimit maximum allowed amount per day
     * @return true if payment can be processed, false otherwise
     */
    public static boolean canProcessPayment(Account sender, double amount, double dailyLimit) {
        return sender != null
                && amount > 0
                && amount <= dailyLimit
                && sender.isActive()
                && sender.getBalance() >= amount;
    }

    public static void main(String[] args) {
        Account a = new Account(true, 1000.0);
        System.out.println(canProcessPayment(a, 500.0, 1000.0));            // true (correct operation)

        Account b = new Account(true, 1000.0);
        System.out.println(canProcessPayment(b, 1500.0, 2000.0));           // false (insufficient funds)

        Account c = new Account(false, 1000.0);
        System.out.println(canProcessPayment(c, 500.0, 1000.0));            // false (inactive user)

        Account d = new Account(true, 1000.0);
        System.out.println(canProcessPayment(d, -100.0, 1000.0));           // false (negative amount)

        Account e = new Account(true, 1000.0);
        System.out.println(canProcessPayment(e, 1000.0, 500.0));            // false (limit exceeded)

        System.out.println(canProcessPayment(null, 500.0, 1000.0));  // false (null sender)
    }

}

/**
 * Domain model for user account (simplified for task).
 * In production: would be separate file with validation, events, etc.
 */
class Account {
    private boolean active;
    private double balance;

    public Account(boolean active, double balance) {
        this.active = active;
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public double getBalance() {
        return balance;
    }
}
