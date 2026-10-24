/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estravo_inte_activities;

import java.util.Scanner;

/**
 * WEEK 3 - SELECTION STATEMENTS
 *
 * @author benes
 */
public class WEEK3 {

    /* BASIC: Simple Grade Classifier */
    public static void basic() {
        /* Tested with two different scores by changing this variable:
           Test 1: score = 70 -> prints PASSED
           Test 2: score = 45 -> prints FAILED */
        int score = 70;

        if (score >= 60) {
            System.out.println("Score: " + score);
            System.out.println("PASSED");
        } else {
            System.out.println("Score: " + score);
            System.out.println("FAILED");
        }
    }

    /* INTERMEDIATE: Multi-Tier Discount System */
    public static void intermediate() {
        double purchaseAmount = 250;
        double discountPercent;
        String tier;

         /* Biggest amount is checked first, so every tier can be reached.
           If the order was reversed, the first tier would catch everything. */
        if (purchaseAmount >= 500) {
            discountPercent = 15;
            tier = "Platinum";
        } else if (purchaseAmount >= 200) {
            discountPercent = 10;
            tier = "Gold";
        } else if (purchaseAmount >= 100) {
            discountPercent = 5;
            tier = "Standard";
        } else {
            discountPercent = 0;
            tier = "Regular";
        }

        double finalPrice = purchaseAmount - (purchaseAmount * discountPercent / 100);

        System.out.println("Purchase amount: Pesos " + purchaseAmount);
        System.out.println("Discount tier: " + tier + " (" + discountPercent + "%)");
        System.out.printf("Final price: Pesos %.2f%n", finalPrice);
    }

    /* ADVANCED: Nested Login Validator */
    public static void advanced(Scanner input) {
        String correctUsername = "admin";
        String correctPassword = "pass123";
        boolean accountLocked = false;
        boolean loggedIn = false;

       /* How to test each result:
           Wrong username -> Incorrect username.
           Right username, wrong password -> Incorrect password.
           Right username and password, accountLocked = true -> account is locked.
           Right username and password, accountLocked = false -> Login successful. */
       while (!loggedIn) {
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();

         /* Check the username first, then the password,
            then the locked account. Each check only runs if the one before passed. */
        if (username.equals(correctUsername)) {
            if (password.equals(correctPassword)) {
                if (!accountLocked) {
                    System.out.println("Login successful. Welcome, " + username + "!");
                    loggedIn = true;
                } else {
                    System.out.println("Login failed: Your account is locked.");
                    break;
                }
            } else {
                System.out.println("Login failed: Incorrect password.");
            }
        } else {
            System.out.println("Login failed: Incorrect username.");
        }
        }
    }

    /* EXPERT: ATM Transaction Authorizer */
    public static void expert(Scanner input) {
        String correctUsername = "student";
        String correctPin = "1234";
        double balance = 12500.00;
        double withdrawnToday = 500.00;
        
        boolean authenticated = false;
        boolean approved = false;
        String denialReason = "";

        /*Step 1: Check the username and the 4-digit PIN.
           the code wont run unless both are correct.*/
        while (!authenticated) {
        System.out.print("Enter username: ");
        String userName = input.nextLine();
        System.out.print("Enter 4-digit PIN: ");
        String pin = input.nextLine();
            
        if (userName.equals(correctUsername)) {
            if (pin.length() == 4) {
                if (pin.equals(correctPin)) {
                    authenticated = true;
                } else {
                    denialReason = "Incorrect PIN";
                }
            } else {
                denialReason = "PIN must be exactly 4 digits.";
            }
        } else {
            denialReason = "Username not found.";
        }

        if (authenticated) {

            /*Step 2: Pick the account tier based on the balance.*/
            String tier;
            if (balance >= 50000) {
                tier = "Platinum";
            } else if (balance >= 10000) {
                tier = "Gold";
            } else if (balance >= 3000) {
                tier = "Silver";
            } else {
                tier = "Bronze";
            }

            /*Step 3: Use the tier to set the fee and the daily limit.*/
            double feePercent;
            double dailyLimit;
            switch (tier) {
                case "Bronze" -> {
                    feePercent = 2.0;
                    dailyLimit = 500;
                }
                case "Silver" -> {
                    feePercent = 1.5;
                    dailyLimit = 1000;
                }
                case "Gold" -> {
                    feePercent = 1.0;
                    dailyLimit = 2500;
                }
                case "Platinum" -> {
                    feePercent = 0.0;
                    dailyLimit = 5000;
                }
                default -> {
                    feePercent = 2.0;
                    dailyLimit = 0;
                }
            }

            System.out.println();
            System.out.println("Account tier: " + tier);
            System.out.printf("Balance: Php%.2f%n", balance);
            System.out.printf("Fee: %.1f%% | Daily limit: Php%.2f | Withdrawn today: Php%.2f%n",
                    feePercent, dailyLimit, withdrawnToday);
            System.out.print("Enter withdrawal amount: Php");
            double amount = input.nextDouble();

            double fee = amount * feePercent / 100.0;
            double totalCost = amount + fee;

            /*Step 4: Check the withdrawal.
              First the amount, then the balance, then the daily limit.*/
            if (amount > 0) {
                if (totalCost <= balance) {
                    if (withdrawnToday + amount <= dailyLimit) {
                        approved = true;
                    } else {
                        denialReason = String.format(
                                "Daily limit exceeded. Limit is Php%.2f, already withdrawn Php%.2f, remaining Php%.2f.",
                                dailyLimit, withdrawnToday, dailyLimit - withdrawnToday);
                    }
                } else {
                    denialReason = String.format(
                            "Insufficient balance. Amount plus fee is Php%.2f but balance is Php%.2f.",
                            totalCost, balance);
                }
            } else {
                denialReason = "Invalid amount. Withdrawal must be greater than Php0.";
            }

            /* FINAL DECISION: approve or deny, with the exact reason. */
            System.out.println();
            if (approved) {
                System.out.println("Status: APPROVED");
                System.out.printf("Withdrawn: Php%.2f%n", amount);
                System.out.printf("Fee (%.1f%%): Php%.2f%n", feePercent, fee);
                System.out.printf("New balance: Php%.2f%n", balance - totalCost);
            } else {
                System.out.println("Status: DENIED");
                System.out.println("Reason: " + denialReason);
            }
        } else {
            /* Authentication failed, so the withdrawal stages never run. */
            System.out.println();
            System.out.println("Status: DENIED");
            System.out.println("Reason: " + denialReason);
        }
        }
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("===== BASIC =====");
            basic();

            System.out.println("\n===== INTERMEDIATE =====");
            intermediate();

            System.out.println("\n===== ADVANCED =====");
            advanced(input);

            System.out.println("\n===== EXPERT =====");
            expert(input);
        }
    }
}