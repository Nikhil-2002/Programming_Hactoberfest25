package SearchingAlgorithms;

public class ExponentialSearch {
    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        int target = 14;
        int result = exponentialSearch(arr, target);

        if (result == -1)
            System.out.println("Element not found");
        else
            System.out.println("Element found at index: " + result);
    }

    static int exponentialSearch(int[] arr, int target) {
        int n = arr.length;

        // If the target is at first index
        if (arr[0] == target)
            return 0;

        // Find range for binary search by repeated doubling
        int i = 1;
        while (i < n && arr[i] <= target)
            i = i * 2;

        // Call binary search for the found range
        return binarySearch(arr, i / 2, Math.min(i, n - 1), target);
    }

    static int binarySearch(int[] arr, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }
}
