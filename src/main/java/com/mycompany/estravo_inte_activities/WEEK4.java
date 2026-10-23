/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estravo_inte_activities;

import java.util.Scanner;

/**
 * WEEK 4 - LOOPS & JUMP STATEMENTS
 *
 * @author benes
 */
public class WEEK4 {

    /* Settings for the warehouse (For the expert level). */
    static final int DAMAGED = -1; /*damage shelf */
    static final int CRITICAL_LEVEL = 5; /* below 5 items is critically low */
    static final int MAX_PER_SHELF = 100; /* maximum items for shelves */

   /* Rows are aisles, columns are shelves. */
    static int[][] warehouse = {
        {20, 15, -1,  8},
        { 3, 40, 25, -1},
        {12,  0, 30, 18}
    };

    /* BASIC: Number & Square Printer */
    public static void basic() {
        /* Tested with two limits by changing this variable:
           limit = 10 prints 1 to 10 (last line: Number: 10  Square: 100).
           limit = 20 prints 1 to 20 (last line: Number: 20  Square: 400). */
        int limit = 10;

        for (int i = 1; i <= limit; i++) {
            int square = i * i;
            System.out.println("Number: " + i + "  Square: " + square);
        }
    }

    /* INTERMEDIATE: Sum Until Sentinel Value */
    public static void intermediate(Scanner input) {
        int total = 0;

       /* Test input: 5, -3, 10, -1
           Total is 12. The -1 stops the loop and is not added. */
        System.out.print("Enter an integer (-1 to stop): ");
        int number = input.nextInt();

        while (number != -1) {
            total += number;
            System.out.print("Enter an integer (-1 to stop): ");
            number = input.nextInt();
        }

        System.out.println("Final total: " + total);
    }

    /* ADVANCED: Pattern Generator With Early Exit */
    public static void advanced(Scanner input) {
        System.out.print("Enter triangle size: ");
        int size = input.nextInt();

        int skipRow = 3;
        int stopCol = 10;

        /* Outer loop is the rows, inner loop is the numbers in a row. */
        for (int i = 1; i <= size; i++) {

            /* CONTINUE: skip row 3 and go to the next row. */
            if (i == skipRow) {
                continue;
            }

            for (int j = 1; j <= i; j++) {
               /* BREAK: stop the inner loop after 10. */
                if (j > stopCol) {
                    break;
                }

                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    /* EXPERT: Warehouse Inventory Scanner */
    public static void expert(Scanner input) {
        int choice;

        /* DO-WHILE: shows the menu again and again until the user picks 5. */
        do {
            System.out.println("\n=== WAREHOUSE INVENTORY ===");
            System.out.println("1. Show warehouse");
            System.out.println("2. Total inventory");
            System.out.println("3. Find first critically low shelf");
            System.out.println("4. Restock a shelf");
            System.out.println("5. Exit");
            choice = readInt(input, "Choose an option (1-5): ", 1, 5);

            switch (choice) {
                case 1:
                    showWarehouse();
                    break;
                case 2:
                    totalInventory();
                    break;
                case 3:
                    findFirstLow();
                    break;
                case 4:
                    restock(input);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
            }
        } while (choice != 5);
    }

    static void showWarehouse() {
        System.out.println("\n        Shelf1 Shelf2 Shelf3 Shelf4");
        for (int r = 0; r < warehouse.length; r++) {
            System.out.print("Aisle " + (r + 1) + ":");
            for (int c = 0; c < warehouse[r].length; c++) {
                if (warehouse[r][c] == DAMAGED) {
                    System.out.printf("%7s", "XX");
                } else {
                    System.out.printf("%7d", warehouse[r][c]);
                }
            }
            System.out.println();
        }
        System.out.println("(XX = damaged shelf)");
    }

    /* FOR + CONTINUE: adds up all the items. */
    static void totalInventory() {
        int total = 0;
        int damagedCount = 0;

        for (int r = 0; r < warehouse.length; r++) {
            for (int c = 0; c < warehouse[r].length; c++) {

                 /* CONTINUE: skip damaged shelves so they are not added. */
                if (warehouse[r][c] == DAMAGED) {
                    damagedCount++;
                    continue;
                }

                total += warehouse[r][c];
            }
        }

        System.out.println("Total inventory: " + total + " items");
        System.out.println("Damaged shelves skipped: " + damagedCount);
    }

    /* NESTED LOOPS + BREAK: finds the first shelf with less than 5 items. */
    static void findFirstLow() {
        int foundRow = -1;
        int foundCol = -1;

       /* "search" is a label so break can stop both loops at once. */
        search:
        for (int r = 0; r < warehouse.length; r++) {
            for (int c = 0; c < warehouse[r].length; c++) {

                /* Damaged shelves are skipped. */
                if (warehouse[r][c] == DAMAGED) {
                    continue;
                }

                /* BREAK: found the first low shelf, so stop searching. */
                if (warehouse[r][c] < CRITICAL_LEVEL) {
                    foundRow = r;
                    foundCol = c;
                    break search;
                }
            }
        }

        if (foundRow == -1) {
            System.out.println("No critically low shelves. Stock looks healthy.");
        } else {
            System.out.println("First critically low shelf: Aisle " + (foundRow + 1)
                    + ", Shelf " + (foundCol + 1)
                    + " (" + warehouse[foundRow][foundCol] + " items)");
        }
    }

    /* RETURN: stops the restock early when it cannot continue. */
    static void restock(Scanner input) {
        showWarehouse();

        int r = readInt(input, "Aisle number (1-" + warehouse.length + "): ",
                1, warehouse.length) - 1;
        int c = readInt(input, "Shelf number (1-" + warehouse[0].length + "): ",
                1, warehouse[0].length) - 1;

        /* RETURN: a damaged shelf cannot be restocked, so leave the method. */
        if (warehouse[r][c] == DAMAGED) {
            System.out.println("Cannot restock: that shelf is damaged.");
            return;
        }

         /* RETURN: a full shelf has no room, so leave the method. */
        int room = MAX_PER_SHELF - warehouse[r][c];
        if (room == 0) {
            System.out.println("Shelf is already full.");
            return;
        }

        int amount = readInt(input, "Items to add (1-" + room + "): ", 1, room);
        warehouse[r][c] += amount;

        System.out.println("Restocked Aisle " + (r + 1) + ", Shelf " + (c + 1)
                + ". New count: " + warehouse[r][c]);
    }

    /* WHILE + RETURN: keeps asking until the number is valid,
       then sends it back with return. */
    static int readInt(Scanner input, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);

            if (!input.hasNextInt()) {
                input.next();
                System.out.println("Invalid input. Please enter a whole number.");
                continue;
            }

            int value = input.nextInt();

            if (value < min || value > max) {
                System.out.println("Out of range. Enter a number from " + min + " to " + max + ".");
                continue;
            }

            return value;
        }
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("---- BASIC ----");
            basic();

            System.out.println("\n---- INTERMEDIATE ----");
            intermediate(input);

            System.out.println("\n---- ADVANCED ----");
            advanced(input);

            System.out.println("\n---- EXPERT ----");
            expert(input);
        }
    }
}