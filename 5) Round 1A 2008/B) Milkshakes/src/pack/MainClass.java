package pack;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class MainClass {

	public static int getMaltingRequirement(int n, int m, int prefList[][],
			int currentState[]) {
		outer: for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++)
				if (prefList[i][j] == currentState[j])
					continue outer;
			for (int j = 0; j < n; j++)
				if (prefList[i][j] == 1 && currentState[j] == 0)
					return j;
			return -2;
		}

		return -1;
	}

	public static void main(String[] args) throws Exception {

		Scanner scan = new Scanner(new File("input.in"));
		PrintWriter out = new PrintWriter(new File("output.out"));

		int T = scan.nextInt();

		int n, m;

		for (int cT = 1; cT <= T; cT++) {

			n = scan.nextInt();
			m = scan.nextInt();

			int[][] prefList = new int[m][n];
			for (int i = 0; i < m; i++)
				for (int j = 0; j < n; j++)
					prefList[i][j] = -1;

			int index, value, length;

			for (int i = 0; i < m; i++) {
				length = scan.nextInt();
				for (int j = 0; j < length; j++) {
					index = scan.nextInt() - 1;
					value = scan.nextInt();
					prefList[i][index] = value;
				}
			}

			int[] currentState = new int[n];

			int req;
			while ((req = getMaltingRequirement(n, m, prefList, currentState)) > -1) {
				currentState[req] = 1;
			}

			out.print("Case #" + cT + ": ");
			if (req == -2)
				out.print("IMPOSSIBLE");
			else
				for (int i = 0; i < n; i++)
					out.print(currentState[i] + " ");
			out.println();

		}

		scan.close();
		out.close();

	}

}
