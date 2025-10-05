package starPatterns;

import java.util.Scanner;

public class Pattern7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int nst = n;
        // rows

        for (int row = 1; row <= n; row++) {
            // work done
            if (row == 1 || row == n) {
                for (int cst = 1; cst <= nst; cst++) {
                    System.out.print("x ");
                }
            } else {
                for (int cst = 1; cst <= n; cst++) {
                    if (cst == 1 || cst == n) {
                        System.out.print("x ");
                    } else {
                        System.out.print("  ");
                    }

                }

            }

            System.out.println();

        }
    }
}