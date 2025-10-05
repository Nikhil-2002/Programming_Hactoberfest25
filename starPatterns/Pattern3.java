/**
 * 
 */
package starPatterns;

import java.util.Scanner;

public class Pattern3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int nst = n; // no of star present in first row
		int row = 1;

		// no of row
		while (row <= n) {

			// work done

			int cst = 1; // counter of stars that need to bee print in a row
			while (cst <= nst) {
				System.out.print("x");
				cst++;
			}

			// pre
			System.out.println();
			// System.out.print("\t");
			nst--;
			row++;
		}

	}

}