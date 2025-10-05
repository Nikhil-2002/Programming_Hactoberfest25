package starPatterns;

import java.util.Scanner;

/*

x x x x x x x x x
x x x x   x x x x
x x x       x x x
x x           x x
x               x

*/

public class Pattern22 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int nst = n;
        int nsp = -1;

        // rows
        int row = 1;
        while (row <= n) {

            /* work done */

            // star
            int cst = 1;
            if (row == 1) {
                cst = 2;
            }
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

            while (cstt <= nst) {
                System.out.print("x ");
                cstt++;
            }

            /* preparation */
            System.out.println();
            nst--;
            nsp += 2;
            row++;

        }

    }

}