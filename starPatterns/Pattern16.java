package starPatterns;

import java.util.Scanner;

/*

        x x x x x
      x x x x
    x x x
  x x
x
  x x 
    x x x
      x x x x
        x x x x x


 */
public class Pattern16 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int nr = 2 * n - 1;
    int nst = n;
    int nsp = n - 1;

    // rows
    for (int row = 1; row <= nr; row++) {

      // space work
      for (int csp = 1; csp <= nsp; csp++) {
        System.out.print("  ");
      }

      // star work
      for (int cst = 1; cst <= nst; cst++) {
        System.out.print("x ");
      }

      // prearation
      System.out.println();
      if (row <= nr / 2) {
        nst--;
        nsp--;
      } else {
        nst++;
        nsp++;
      }
    }
  }
}