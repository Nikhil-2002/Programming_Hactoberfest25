import java.util.Arrays;

public class ExponentialSearch {
    public static int binarySearch(int[] arr, int left, int right, int key) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == key)
                return mid;
            if (arr[mid] < key)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    public static int exponentialSearch(int[] arr, int key) {
        if (arr[0] == key)
            return 0;

        int i = 1;
        while (i < arr.length && arr[i] <= key)
            i = i * 2;

        return binarySearch(arr, i / 2, Math.min(i, arr.length - 1), key);
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 8, 16, 32, 64, 128};
        int key = 64;
        int result = exponentialSearch(arr, key);
        System.out.println(result == -1 ? "Not found" : "Found at index " + result);
    }
}
