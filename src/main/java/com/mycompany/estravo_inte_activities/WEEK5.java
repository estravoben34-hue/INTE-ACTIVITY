/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estravo_inte_activities;

import java.util.Arrays;
import java.util.Scanner;

/**
 * WEEK 5 - ARRAYS
 *
 * @author benes
 */
public class WEEK5 {

    /* Used by the Expert level. Two scores are the same if they
       are closer than this tiny number. */
    static final double EPSILON = 0.0001;

    /* A Student has a name and an array of quiz scores. */
    static class Student {
        private final String name;
        private final int[] quizzes;

        public Student(String name, int[] quizzes) {
            this.name = name;
            this.quizzes = quizzes;
        }

        public String getName() {
            return name;
        }

        public int[] getQuizzes() {
            return quizzes;
        }

        public double getAverage() {
            int sum = 0;
            for (int i = 0; i < quizzes.length; i++) {
                sum += quizzes[i];
            }
            return (double) sum / quizzes.length;
        }
    }

    /* BASIC: Favorite Things Array */
    public static void basic() {
        String[] favorites = {"Ramen", "Sport", "Coffee", "Night", "Games"};

        System.out.println("My Favorite Things:");

        /* The loop prints each item with a number. */
        for (int i = 0; i < favorites.length; i++) {
            System.out.println((i + 1) + ". " + favorites[i]);
        }

        System.out.println("Total favorites: " + favorites.length);
    }

    /* INTERMEDIATE: Class Statistics Calculator */
    public static void intermediate() {
        int[] scores = {85, 92, 78, 65, 90, 88, 73, 95, 81, 70};

        int sum = 0;
        int highest = scores[0];
        int lowest = scores[0];

        /* One loop adds every score and checks for a new highest or lowest. */
        for (int i = 0; i < scores.length; i++) {
            sum += scores[i];

            if (scores[i] > highest) {
                highest = scores[i];
            }

            if (scores[i] < lowest) {
                lowest = scores[i];
            }
        }

        /* The average is a double so the decimals are not cut off. */
        double average = (double) sum / scores.length;

        System.out.println("Scores: " + scores.length + " students");
        System.out.println("Sum: " + sum);
        System.out.printf("Average: %.2f%n", average);
        System.out.println("Highest score: " + highest);
        System.out.println("Lowest score: " + lowest);
    }

    /* ADVANCED: Search & Sort Mini-Tool */
    public static void advanced(Scanner input) {
        int[] scores = {85, 92, 78, 65, 99, 54, 88, 73, 95, 60};

        System.out.print("Enter a score to search for: ");
        int target = input.nextInt();
        input.nextLine(); /* clears the Enter key left behind */

        int foundIndex = -1;

        /* Linear search: check each score one by one. */
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == target) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex != -1) {
            System.out.println("Found " + target + " at index " + foundIndex);
        } else {
            System.out.println(target + " was not found.");
        }

        /* Why sort a copy: Arrays.sort() changes the array it is given.
           If we sorted the original, the original order would be lost
           and the index from the search would no longer match the data.
           Sorting a copy keeps the original safe. */
        int[] sortedScores = Arrays.copyOf(scores, scores.length);
        Arrays.sort(sortedScores);

        System.out.println();
        System.out.println("Original: " + Arrays.toString(scores));
        System.out.println("Sorted:   " + Arrays.toString(sortedScores));
    }

    /* EXPERT: Class Ranking System */
    public static void expert(Scanner input) {
        /* Raw data: one name and one row of quiz scores per student. */
        String[] names = {"Karl", "Jan", "Dan", "Maria", "Natalia", "Alyssa", "Kaeley"};
        int[][] rawQuizzes = {
            { 90,  85,  95,  90},
            { 78,  82,  80,  80},
            { 88,  92,  85,  95},
            { 70,  65,  80,  75},
            {100,  95,  98,  91},
            { 60,  75,  70,  55},
            { 82,  78,  80,  80}
        };

        /* Build the Student objects from the raw data. */
        Student[] students = new Student[names.length];
        for (int i = 0; i < names.length; i++) {
            students[i] = new Student(names[i], rawQuizzes[i]);
        }

        /* Copy the array, then sort the copy (highest average first). */
        Student[] ranked = new Student[students.length];
        for (int i = 0; i < students.length; i++) {
            ranked[i] = students[i];
        }
        insertionSortByAverage(ranked);

        int[] ranks = computeRanks(ranked);

        printLeaderboard(ranked, ranks);

        System.out.print("\nEnter a student name to look up: ");
        String target = input.nextLine().trim();

        int rank = searchRank(ranked, ranks, target);
        if (rank == -1) {
            System.out.println("\"" + target + "\" was not found.");
        } else {
            System.out.println(target + " is ranked #" + rank + " out of " + ranked.length + ".");
        }
    }

    /* Two averages are the same if the difference is tiny. */
    static boolean sameAverage(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }

    /* OWN SORT: insertion sort, highest average first.
       No Arrays.sort() is used. */
    static void insertionSortByAverage(Student[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Student key = arr[i];
            double keyAvg = key.getAverage();
            int j = i - 1;
            while (j >= 0 && arr[j].getAverage() < keyAvg
                    && !sameAverage(arr[j].getAverage(), keyAvg)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /* TIES: a student with the same average as the one above
       gets the same rank. Otherwise the rank is the position. */
    static int[] computeRanks(Student[] sorted) {
        int[] ranks = new int[sorted.length];
        for (int i = 0; i < sorted.length; i++) {
            if (i > 0 && sameAverage(sorted[i].getAverage(), sorted[i - 1].getAverage())) {
                ranks[i] = ranks[i - 1];
            } else {
                ranks[i] = i + 1;
            }
        }
        return ranks;
    }

    /* OWN LINEAR SEARCH: checks each student by name
       and returns that student's rank, or -1 if not found. */
    static int searchRank(Student[] sorted, int[] ranks, String name) {
        for (int i = 0; i < sorted.length; i++) {
            if (sorted[i].getName().equalsIgnoreCase(name)) {
                return ranks[i];
            }
        }
        return -1;
    }

    /* Checks if a student shares a rank with the one above or below. */
    static boolean isTied(int[] ranks, int i) {
        boolean sameAsAbove = i > 0 && ranks[i] == ranks[i - 1];
        boolean sameAsBelow = i < ranks.length - 1 && ranks[i] == ranks[i + 1];
        return sameAsAbove || sameAsBelow;
    }

    /* Prints the leaderboard. Tied students show T- before the rank. */
    static void printLeaderboard(Student[] sorted, int[] ranks) {
        System.out.println("========== CLASS LEADERBOARD ==========");
        System.out.printf("%-6s %-8s %-18s %s%n", "Rank", "Name", "Quizzes", "Average");
        System.out.println("---------------------------------------");

        for (int i = 0; i < sorted.length; i++) {
            String rankText = (isTied(ranks, i) ? "T-" : "") + ranks[i];

            String quizText = "";
            int[] q = sorted[i].getQuizzes();
            for (int k = 0; k < q.length; k++) {
                quizText += q[k] + " ";
            }

            System.out.printf("%-6s %-8s %-18s %.2f%n",
                    rankText, sorted[i].getName(), quizText.trim(), sorted[i].getAverage());
        }
        System.out.println("---------------------------------------");
        System.out.println("T = tied rank");
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("---- BASIC ----");
            basic();

            System.out.println("\n---- INTERMEDIATE ----");
            intermediate();

            System.out.println("\n---- ADVANCED ----");
            advanced(input);

            System.out.println("\n---- EXPERT ----");
            expert(input);
        }
    }
}