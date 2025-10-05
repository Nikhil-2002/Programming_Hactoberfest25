package starPatterns;

import java.util.Scanner;

/*
        x
      x   x
    x   x   x
  x   x   x   x
x   x   x   x   x

 */
public class Pattern11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int nst = 1;
        int nsp = n - 1;

        // rows
        int row = 1;
        while (row <= n) {

            // space work
            int csp = 1;
            while (csp <= nsp) {
                System.out.print("  ");
                csp++;
            }

            // star work
            int cst = 1;
            while (cst <= nst) {
                // checking the even position
                if (cst % 2 == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print("x ");
                }
                cst++;
            }

            // preparation
            System.out.println();
            nsp--;
            nst += 2;
            row++;
        }
    }
}