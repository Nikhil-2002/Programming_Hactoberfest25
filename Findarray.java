/*
 * FindInArray.java
 * ----------------
 * Simple Java program to find a given number in an array.
 *
 * Features:
 *  - Takes user input for array size and elements
 *  - Searches for a target number (linear search)
 *  - Prints all positions where the number occurs
 *  - Clean and easy to understand — great Hacktoberfest contribution
 *
 * Author: <Your Name>
 * Hacktoberfest 2025 Contribution
 * License: MIT
 */

import java.util.*;

public class FindInArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("🔍 Find Number in Array - Hacktoberfest 2025 Edition 🔍");
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Array size must be positive!");
            return;
        }

        int[] arr = new int[n];
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter the number to find: ");
        int target = sc.nextInt();

        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] == target) {
                positions.add(i); // store index
            }
        }

        if (positions.isEmpty()) {
            System.out.println("❌ Number " + target + " not found in the array.");
        } else {
            System.out.println("✅ Number " + target + " found at position(s): " + positions);
        }

        sc.close();
    }
}
