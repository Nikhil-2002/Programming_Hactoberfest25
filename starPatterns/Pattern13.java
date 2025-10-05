package starPatterns;

import java.util.Scanner;
/*

x
x x
x x x
x x x x
x x x x x
x x x x
x x x
x x
x

 */

public class Pattern13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int nst = 1;// no of stars
        int nr = 2 * n - 1;

        // rows
        int row = 1;
        while (row <= nr) {

            // work done
            int cst = 1;
            while (cst <= nst) {
                System.out.print("x ");
                cst++;
            }

            // preparation
            System.out.println();
            if (row <= nr / 2) {
                nst++;
            } else {
                nst--;
            }
            row++;
        }
    }
}