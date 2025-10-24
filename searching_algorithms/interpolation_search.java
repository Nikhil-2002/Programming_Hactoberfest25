public class InterpolationSearch {
    public static int interpolationSearch(int[] arr, int key) {
        int low = 0, high = arr.length - 1;

        while (low <= high && key >= arr[low] && key <= arr[high]) {
            if (low == high) {
                return (arr[low] == key) ? low : -1;
            }

            int pos = low + ((key - arr[low]) * (high - low)) / (arr[high] - arr[low]);

            if (arr[pos] == key)
                return pos;

            if (arr[pos] < key)
                low = pos + 1;
            else
                high = pos - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        int key = 30;
        int result = interpolationSearch(arr, key);
        System.out.println(result == -1 ? "Not found" : "Found at index " + result);
    }
}
