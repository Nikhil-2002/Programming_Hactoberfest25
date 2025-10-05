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

public class Pattern14 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int nr = 2 * n - 1;
    int nst = 1;
    int nsp = n - 1;

    // rows
    int row = 1;
    while (row <= nr) {

      // space work
      int csp = 1;
      while (csp <= nsp) {
        System.out.print("  ");
        csp++;
      }
      // star work

      int cst = 1;
      while (cst <= nst) {
        System.out.print("*");
        cst++;
      }

      // prepartion
      System.out.println();
      if (row <= nr / 2) {
        nst++;
        nsp--;
      } else {
        nst--;
        nsp++;
      }
      row++;

    }
  }
}