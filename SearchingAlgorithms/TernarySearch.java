package SearchingAlgorithms;

public class TernarySearch {
    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        int target = 16;
        int result = ternarySearch(arr, 0, arr.length - 1, target);

        if (result == -1)
            System.out.println("Element not found");
        else
            System.out.println("Element found at index: " + result);
    }

    static int ternarySearch(int[] arr, int left, int right, int target) {
        if (right >= left) {
            // Split the range into 3 parts
            int mid1 = left + (right - left) / 3;
            int mid2 = right - (right - left) / 3;

            // Check if target is at any mid
            if (arr[mid1] == target)
                return mid1;
            if (arr[mid2] == target)
                return mid2;

            // Recur in left or right part
            if (target < arr[mid1])
                return ternarySearch(arr, left, mid1 - 1, target);
            else if (target > arr[mid2])
                return ternarySearch(arr, mid2 + 1, right, target);
            else
                return ternarySearch(arr, mid1 + 1, mid2 - 1, target);
        }
        return -1;
    }
}
