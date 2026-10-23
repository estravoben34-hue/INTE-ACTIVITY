/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estravo_inte_activities;

import java.util.Scanner;

/**
 * WEEK 2 - OPERATORS & EXPRESSIONS
 *
 * @author benes
 */
public class WEEK2 {

    /* BASIC: Four-Function Calculator */
    public static void basic() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter first integer: ");
            int a = input.nextInt();

            System.out.print("Enter second integer: ");
            int b = input.nextInt();

            System.out.println();
            
            /* Negative operands:
   Addition (+):       -7 + 2 = -5. Works normally.
   Subtraction (-):    -7 - 2 = -9. Works normally.
   Multiplication (*): -7 * 2 = -14. Works normally, a negative times a positive is negative.
   Division (/):       -7 / 2 = -3. Behaves differently: the decimal is chopped off
                       toward zero, so it is -3, not -4.
   Modulus (%):        -7 % 2 = -1. Behaves differently: the answer takes the sign of
                       the first number. */            
            System.out.println("Results for a = " + a + ", b = " + b);
            System.out.println("-----------------------------");
            System.out.println("Addition:       " + a + " + " + b + " = " + (a + b));
            System.out.println("Subtraction:    " + a + " - " + b + " = " + (a - b));
            System.out.println("Multiplication: " + a + " * " + b + " = " + (a * b));

            if (b != 0) {
                System.out.println("Division:       " + a + " / " + b + " = " + (a / b));
                System.out.println("Modulus:        " + a + " % " + b + " = " + (a % b));
            } else {
                System.out.println("Division:       undefined (cannot divide by zero)");
                System.out.println("Modulus:        undefined (cannot divide by zero)");
            }
        }
    }

    /* INTERMEDIATE: Grade Threshold Checker */
    public static void intermediate() {
        double average = 90;
        int absences = 6;
        boolean pass = (average >= 75 && absences <= 3) || (average >= 90);

        System.out.println("Average Grade: " + average);
        System.out.println("Number of Absences: " + absences);

        if (pass) {
            System.out.println("Result: PASS");
            if (average >= 90) {
                System.out.println("Reason: Your average is 90 or above, so the absences do not matter.");
            } else {
                System.out.println("Reason: You meet the grade and the attendance requirements.");
            }
        } else {
            System.out.println("Result: FAIL");
            System.out.println("Reason: Your grade is below 90 and you do not meet both regular requirements.");
        }
    }

    /* ADVANCED: Compound Growth & Increment Tracker */
    public static void advanced() {
        double balance = 2000.00;
        System.out.println("Starting balance: " + balance);

        balance += 200.00;
        System.out.println("After deposit (+= 200):      " + balance);

        balance *= 1.05;
        System.out.println("After 5% interest (*= 1.05): " + balance);

        balance += 150.00;
        System.out.println("After deposit (+= 150):      " + balance);

        balance *= 1.10;
        System.out.println("After 10% growth (*= 1.10):  " + balance);

        System.out.println();

        /* Postfix count++ gives back the OLD value first, then adds 1.
           So println shows 5, and count becomes 6 only afterward. */
        int count = 5;
        System.out.println("Postfix  count++ prints: " + count++);
        System.out.println("count immediately after: " + count);

        System.out.println();

        /* Prefix ++count adds 1 FIRST, then gives back the NEW value.
           So println shows 6, and count is already 6. That is why the
           first printed value differs: postfix prints 5, prefix prints 6. */
        count = 5;
        System.out.println("Prefix   ++count prints: " + ++count);
        System.out.println("count immediately after: " + count);
    }

    /* EXPERT: Precedence Puzzle Solver */
    public static void expert() {
        int a = 4, b = 3, c = 6;

        /* Puzzle 1 prediction: true.
           b * c = 18 happens first, so a + 18 = 22, which is > 10 (true).
           c - a = 2, so == 2 is true. true && true gives true. */
        System.out.println("1) a + b * c > 10 && c - a == 2  ->  "
                + (a + b * c > 10 && c - a == 2));

        /* Puzzle 2 prediction: r = 19, x = 7.
           x++ gives 5 (then x is 6), ++x makes x 7 and gives 7.
           ++x * 2 is 14, and 5 + 14 = 19. */
        int x = 5;
        int r = x++ + ++x * 2;
        System.out.println("2) x++ + ++x * 2 -> r = " + r + ", x = " + x);

        /* Puzzle 3 prediction: true.
           && is checked before ||, so false && false is false first.
           Then true || false gives true. */
        System.out.println("3) true || false && false -> " + (true || false && false));
    }

    public static void main(String[] args) {
        System.out.println("---- BASIC ----");
        basic();

        System.out.println("\n---- INTERMEDIATE ----");
        intermediate();

        System.out.println("\n---- ADVANCED ----");
        advanced();

        System.out.println("\n---- EXPERT ----");
        expert();
    }
}