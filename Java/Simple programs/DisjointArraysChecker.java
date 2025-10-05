import java.util.Scanner;
import java.util.HashSet;

public class DisjointArraysChecker {

    public static boolean areDisjoint(int[] arr1, int[] arr2) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : arr1) {
            set.add(num);
        }

        for (int num : arr2) {
            if (set.contains(num)) {
                return false; // Common element found, arrays are not disjoint
            }
        }

        return true; // No common elements found, arrays are disjoint
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the first array: ");
        int size1 = scanner.nextInt();
        int[] array1 = new int[size1];

        System.out.println("Enter elements of the first array:");
        for (int i = 0; i < size1; i++) {
            array1[i] = scanner.nextInt();
        }

        System.out.print("Enter the size of the second array: ");
        int size2 = scanner.nextInt();
        int[] array2 = new int[size2];

        System.out.println("Enter elements of the second array:");
        for (int i = 0; i < size2; i++) {
            array2[i] = scanner.nextInt();
        }

        scanner.close();

        if (areDisjoint(array1, array2)) {
            System.out.println("The arrays are disjoint.");
        } else {
            System.out.println("The arrays are not disjoint.");
        }
    }
}
