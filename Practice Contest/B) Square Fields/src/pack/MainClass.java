package pack;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class MainClass {

	public static int[] px, py;

	public static int square(int b) {
		int xmin = -1, ymin = -1, xmax = -1, ymax = -1;
		for (int i = 0; i < px.length; i++)
			if (((1 << i) & b) > 0) {
				if (xmin > px[i] || xmin == -1)
					xmin = px[i];
				if (xmax < px[i] || xmax == -1)
					xmax = px[i];
				if (ymin > py[i] || ymin == -1)
					ymin = py[i];
				if (ymax < py[i] || ymax == -1)
					ymax = py[i];
			}
		return ((ymax - ymin) > (xmax - xmin) ? (ymax - ymin) : (xmax - xmin));
	}

	public static boolean isSubset(int sub, int sup) {
		for (int i = 1; i < (1 << 15); i <<= 1) {
			if ((i & sub) > (i & sup))
				return false;
		}
		return true;
	}

	public static int recur(int[][] table, int i, int j, int c) {
		if (table[i][j] != -1)
			return table[i][j];
		if (j == 0 && i > 0)
			return -1;
		int s = -1, temps;

		if (i >= (1 << c)) {
			for (int subsetI = 1; subsetI <= i; subsetI++)
				if (isSubset(subsetI, i)) {
					temps = recur(table, i - subsetI, j - 1, c - 1);
					if (temps != -1 && temps < square(subsetI))
						temps = square(subsetI);
					if ((s > temps || s == -1) && temps != -1)
						s = temps;
				}
			return table[i][j] = s;
		} else
			return recur(table, i, j, c - 1);
	}

	public static int countOnes(int n) {
		int s = 0;
		for (int i = 1; i <= n; i <<= 1)
			if ((n & i) > 0)
				s++;
		return s;
	}

	public static int minLength(int n, int k) {
		int[][] table = new int[1 << n][k + 1];
		for (int i = 0; i < (1 << n); i++) {
			for (int j = 0; j <= k; j++)
				if (countOnes(i) <= j)
					table[i][j] = 0;
				else
					table[i][j] = -1;
		}
		return recur(table, (1 << n) - 1, k, n - 1);
	}

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(new File("input.in"));
		PrintWriter out = new PrintWriter(new File("output.out"));

		int T = scan.nextInt();
		int n, k;
		for (int cT = 1; cT <= T; cT++) {
			n = scan.nextInt();
			k = scan.nextInt();

			px = new int[n];
			py = new int[n];
			for (int i = 0; i < n; i++) {
				px[i] = scan.nextInt();
				py[i] = scan.nextInt();
			}
			out.println("Case #" + cT + ": " + minLength(n, k));
		}
		scan.close();
		out.close();
	}
}
