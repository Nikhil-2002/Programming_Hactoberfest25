package starPatterns;

import java.util.Scanner;

public class Pattern10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int nst = n + 4;
        int nsp = 0;

        // rows
        int row = 1;
        while (row <= n) {

            // space work
            int csp = 1;
            while (csp <= nsp) {
                System.out.print(" ");
                csp++;
            }

            // star work
            int cst = 1;
            while (cst <= nst) {
                System.out.print("x");
                cst++;
            }

            // preparation
            System.out.println();
            nsp++;
            nst -= 2;
            row++;
        }
    }
}