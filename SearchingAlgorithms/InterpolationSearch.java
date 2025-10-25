package SearchingAlgorithms;
public class InterpolationSearch {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int target = 70;
        int result = interpolationSearch(arr, target);

        if (result == -1)
            System.out.println("Element not found");
        else
            System.out.println("Element found at index: " + result);
    }

    static int interpolationSearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high && target >= arr[low] && target <= arr[high]) {
            // Avoid division by zero
            if (arr[low] == arr[high]) {
                if (arr[low] == target)
                    return low;
                else
                    return -1;
            }

            // Estimate position using interpolation formula
            int pos = low + ((target - arr[low]) * (high - low)) / (arr[high] - arr[low]);

            if (arr[pos] == target)
                return pos;
            if (arr[pos] < target)
                low = pos + 1;
            else
                high = pos - 1;
        }
        return -1;
    }
}
