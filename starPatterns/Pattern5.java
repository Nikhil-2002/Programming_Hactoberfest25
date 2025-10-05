package starPatterns;

import java.util.Scanner;

public class Pattern5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int nsp = 0;
		int nst = n;

		// rows
		int row = 1;
		while (row <= n) {

			// star work
			int cst = 1;
			while (cst <= nst) {
				System.out.print("x");
				cst++;
			}

			// space work

			int csp = 1;
			while (csp <= nsp) {
				System.out.print(" ");
				csp++;
			}

			// pre
			System.out.println();
			nsp++;
			nst--;
			row++;
		}

	}

}