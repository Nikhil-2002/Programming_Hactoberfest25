package starPatterns;

import java.util.Scanner;

/*

x               x
x x           x x
x x x       x x x
x x x x   x x x x
x x x x x x x x x


 */

public class Pattern21 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int nst = 1;
        int nsp = 2 * n - 3;

        // rows
        int row = 1;
        while (row <= n) {

            /* work done */

            // star
            int cst = 1;
            while (cst <= nst) {
                System.out.print("x ");
                cst++;
            }

            // space
            int csp = 1;
            while (csp <= nsp) {
                System.out.print("  ");
                csp++;
            }

            // star

            int cstt = 1;
            if (row == n) {
                cstt = 2;
            }
            while (cstt <= nst) {
                System.out.print("x ");
                cstt++;
            }

            /* preparation */
            System.out.println();
            nst++;
            nsp -= 2;
            row++;

        }

    }

}