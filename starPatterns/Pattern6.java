package starPatterns;

import java.util.Scanner;

public class Pattern6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int nst = n;
        int nsp = 0;

        // rows
        int rows = 1;
        while (rows <= n) {

            // spaces work
            if (nsp >= 2) {
                int csp = 1;
                while (csp <= nsp) {
                    System.out.print(" ");
                    csp++;
                }
            }

            // star work
            int cst = 1;
            while (cst <= nst) {
                System.out.print("x");
                cst++;
            }

            System.out.println();
            nst--;
            nsp += 2;
            rows++;

        }
    }
}