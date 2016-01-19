package pack;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainClass {

	public static int n, k, edges[][], degree[];

	public static int getPathCount(int i) {
		int solution = 0;

		return solution;
	}

	public static void main(String[] args) throws Exception {

		Scanner scan = new Scanner(new File("input.in"));
		PrintWriter out = new PrintWriter(new File("output.out"));

		int T = scan.nextInt();
		int count[];
		for (int cT = 1; cT <= T; cT++) {
			n = scan.nextInt();
			k = scan.nextInt();

			degree = new int[n];
			edges = new int[k][2];
			count = new int[1 << k];

			for (int i = 0; i < k; i++) {
				edges[i][0] = scan.nextInt() - 1;
				edges[i][1] = scan.nextInt() - 1;
			}

			for (int i = 1; i < 1 << k; i++)
				count[i] = getPathCount(i);
		}

		scan.close();
		out.close();
	}

}
