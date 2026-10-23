/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.estravo_inte_activities;

/*
 * WEEK 1 - DATA TYPES & VARIABLES
 *
 * @author benes
 */
public class WEEK1 {

    /* BASIC: Personal Profile Printer */
    public static void basic() {
        String fullName = "Ben G Estravo";
        int age = 19;
        double height = 167.64;
        char initial = 'B';
        boolean isStudent = true;

        System.out.println("Personal Profile");
        System.out.println("Full Name: " + fullName);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height + " cm");
        System.out.println("Initial: " + initial);
        System.out.println("Student Status: " + isStudent);
    }

    /* INTERMEDIATE: Type-Safe Shopping Calculator */
    public static void intermediate() {
        String storeName = "Sari Saro Store";
        int quantity = 3;
        double unitPrice = 547.50;

        /* totalCost is a double, not an int, because prices have decimals.
           An int would cut off the decimal part and give the wrong total. */
        double totalCost = quantity * unitPrice;

        System.out.println("Shopping Summary");
        System.out.println("Store Name: " + storeName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Unit Price: Pesos " + unitPrice);
        System.out.println("Total Cost: Pesos " + totalCost);
    }

    /* ADVANCED: Unit Casting Converter */
    public static void advanced() {
        double centimeters = 275.5;

        int meters = (int) (centimeters / 100);
        double remainingCentimeters = centimeters - (meters * 100);

        System.out.println("Centimeters: " + centimeters);
        System.out.println("Whole Meters: " + meters);
        System.out.println("Remaining Centimeters: " + remainingCentimeters);

        /* Implicit widening: int to double.
           No cast is needed because a double can hold every int value,
           so nothing is lost and Java converts it automatically. */
        int number = 25;
        double widenedNumber = number;

        System.out.println("Implicit Widening: " + widenedNumber);

        /* Explicit narrowing: double to int.
           A cast is required because data can be lost. The decimal part
           (.75) is cut off, so 12.75 becomes 12 (it is not rounded). */
        double decimalNumber = 12.75;
        int narrowedNumber = (int) decimalNumber;

        System.out.println("Explicit Narrowing: " + narrowedNumber);
    }

    /* EXPERT: Overflow & Reference Investigator */
    public static void expert() {
        /* A byte can only hold -128 to 127. Adding 1 to 127 overflows
           and wraps around to -128. */
        byte number = 127;
        number++;
        System.out.println("Byte after overflow: " + number);

        String str1 = new String("Hello");
        String str2 = new String("Hello");
        String str3 = "Hello";

        System.out.println("\nString Comparisons:");

        /* false: "new" always creates a separate object in memory,
           so str1 and str2 are two different objects (different references). */
        System.out.println("str1 == str2: " + (str1 == str2));

        /* true: .equals() compares the text, and both are "Hello". */
        System.out.println("str1.equals(str2): " + str1.equals(str2));

        /* false: str1 is a new object, while str3 points to the shared
           "Hello" in the string pool, so the references are different. */
        System.out.println("str1 == str3: " + (str1 == str3));

        /* true: same text. */
        System.out.println("str1.equals(str3): " + str1.equals(str3));

        /* false: str2 is also a new object, not the pooled literal. */
        System.out.println("str2 == str3: " + (str2 == str3));

        /* true: same text. */
        System.out.println("str2.equals(str3): " + str2.equals(str3));

        /* array2 does not copy the array. It points to the same array
           as array1, so changing it through one is visible through the other. */
        int[] array1 = {10, 20, 30};
        int[] array2 = array1;

        array2[0] = 99;

        System.out.println("\nArray Reference Sharing:");
        System.out.println("array1[0]: " + array1[0]);
        System.out.println("array2[0]: " + array2[0]);
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