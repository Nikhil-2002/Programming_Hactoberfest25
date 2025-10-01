import java.util.*;


public class Rotate_array {
    
    public static void rotate(int[] arr, int d) {
        if (arr == null || arr.length == 0 || d % arr.length == 0) {
            return;
        }

        int n = arr.length;
        d = d % n; 

        reverse(arr, 0, n - 1);
        reverse(arr, 0, n - d - 1);
        reverse(arr, n - d, n - 1);
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the number of elements in the array:");
        int n = scanner.nextInt();
        
        int[] arr = new int[n];
        
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        
        System.out.println("Enter the number of positions to rotate (d):");
        int d = scanner.nextInt();
        
        System.out.print("Original Array: ");
        printArray(arr);
        
        rotate(arr, d);
        
        System.out.print("Rotated Array: ");
        printArray(arr);
        
        scanner.close();
    }
}
    

