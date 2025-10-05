import java.util.Scanner;

public class SmallestElementFinder {

    public static int findSmallestElement(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        int smallest = arr[0];

        for (int num : arr) {
            if (num < smallest) {
                smallest = num;
            }
        }

        return smallest;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        int[] array = new int[size];

        if (size <= 0) {
            System.out.println("Array size must be greater than 0.");
            return;
        }

        System.out.println("Enter elements of the array:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        scanner.close();

        try {
            int smallestElement = findSmallestElement(array);
            System.out.println("The smallest element in the array is: " + smallestElement);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}