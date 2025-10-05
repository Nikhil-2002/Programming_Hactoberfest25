package starPatterns;

import java.util.Scanner;

/*
      x
    x x x
  x x x x x
x x x x x x x
  x x x x x
    x x x
      x

 */

public class Pattern18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();// 7

        int nst = 1;
        int nsp = n / 2;

        // row
        for (int row = 1; row <= n; row++) {

            // space
            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("  ");
            }
            // star
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print("x ");
            }

            // preparation
            System.out.println();
            if (row <= n / 2) {
                nsp--;
                nst += 2;
            } else {
                nsp++;
                nst -= 2;
            }
        }
    }
}