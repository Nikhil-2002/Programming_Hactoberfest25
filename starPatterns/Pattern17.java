package starPatterns;

import java.util.Scanner;

/*

x x x   x x x
x x       x x
x           x

x           x
x x       x x
x x x   x x x


 */
public class Pattern17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();// 7
        int nst = n / 2; // 7/2 = 3
        int nsp = 1;

        // rows
        for (int row = 1; row <= n; row++) {

            // star work
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print("x ");
            }
            // space work
            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print("  ");
            }

            // star work
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print("x ");
            }

            // preparation
            System.out.println();
            if (row <= n / 2) {
                nst--;
                nsp += 2;
            } else {
                nst++;
                nsp -= 2;
            }

        }
    }
}