//leetcode --> 496. Next Greater Element I
import java.util.*;

class NGE {
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> s = new Stack<>();
        for (int i : nums2) {
            while (!s.isEmpty() && s.peek() < i)
                map.put(s.pop(), i);
            s.push(i);
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.getOrDefault(nums1[i], -1);
        }
        return nums1;
    }

    public static void main(String[] args) {
        int[] arr1 = { 4, 1, 2 };
        int[] arr2 = { 1, 3, 4, 2 };
        int[] res = nextGreaterElement(arr1, arr2);
        for (int i : res)
            System.out.print(i + " ");
    }
}