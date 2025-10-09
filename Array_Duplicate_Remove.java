import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Array_Duplicate_Remove {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // Testcases
        while (t-- > 0) {
            int n = sc.nextInt(); // Array length
            int[] ar = new int[n];
            for (int i = 0; i < n; i++) {
                ar[i] = sc.nextInt(); // User provides values
            }
            int[] new_ar = removeDuplicates(ar);
            for (int e : new_ar) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
        sc.close();
    }

    public static int[] removeDuplicates(int[] ar) {
        HashSet<Integer> checkSet = new HashSet<>();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int e : ar) {
            if (!checkSet.contains(e)) {
                checkSet.add(e);
                arr.add(e);
            }
        }
        int[] result = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            result[i] = arr.get(i);
        }
        return result;
    }
}