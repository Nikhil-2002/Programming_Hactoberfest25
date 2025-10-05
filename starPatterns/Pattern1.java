package starPatterns;

import java.util.Scanner;

/*

x x x x x 
x x x x x 
x x x x x 
x x x x x 
x x x x x 

*/

public class Pattern1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int row = 1;

		while (row <= n) {
			// work
			for (int col = 1; col <= n; col++) {
				System.out.print("x ");
			}
			// prepartion

			System.out.println();
			row++;
		}

		sc.close();
	}

}