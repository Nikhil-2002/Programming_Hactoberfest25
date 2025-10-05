package starPatterns;

import java.util.Scanner;

/*

x x x x x x x
x x x   x x x
x x       x x
x           x
x x       x x
x x x   x x x
x x x x x x x
    

 */

public class Pattern19 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();// 7

        int nst = n / 2 + 1;
        int nsp = -1;

        // row
        for (int row = 1; row <= n; row++) {

            // star
            if (row == 1 | row == n) {

                for (int cst = 1; cst <= n; cst++) {
                    System.out.print("x ");
                }
            } else {

                // star
                for (int cst = 1; cst <= nst; cst++) {
                    System.out.print("x ");
                }
                // space
                for (int csp = 1; csp <= nsp; csp++) {
                    System.out.print("  ");
                }

                // star
                for (int cst = 1; cst <= nst; cst++) {
                    System.out.print("x ");
                }
            }

            // preparation
            System.out.println();
            if (row <= n / 2) {
                nsp += 2;
                nst--;
            } else {
                nsp -= 2;
                nst++;
            }
        }
    }
}