package pack;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {

	public static void initCheckerboard(int[][] graph, int length, int size) {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				graph[i][j] = -1;

		int half = (length + 1) / 2;
		int cl = half, icl, i = 0, cr = 1;
		while (cr < length) {

			if (cr < half)
				icl = cl;
			else
				icl = cl - 1;

			System.out.println(cl + "  " + icl);

			if (cr < half) {
				graph[i][i + icl] = graph[i + icl][i] = 1;
			}
			graph[i][i + icl + 1] = graph[i + icl + 1][i] = 1;
			i++;

			for (int x = 1; x < cl - 1; x++) {
				graph[i][i + icl] = graph[i + icl][i] = graph[i][i + icl + 1] = graph[i
						+ icl + 1][i] = 1;
				i++;
			}

			graph[i][i + icl] = graph[i + icl][i] = 1;
			if (cr < half) {
				graph[i][i + icl + 1] = graph[i + icl + 1][i] = 1;
			}
			i++;

			if (cr < half)
				cl++;
			else
				cl--;
			cr++;
		}
	}

	public static void main(String[] args) throws Exception {

		Scanner scan = new Scanner(new File("input.in"));
		PrintWriter out = new PrintWriter(new File("output.out"));
		Scanner scanLine;

		ArrayList<Integer> checkerLocations = new ArrayList<Integer>(), checkerCosts = new ArrayList<Integer>();

		int T = scan.nextInt(), length, size, graph[][];
		scan.nextLine();

		for (int cT = 0; cT < T; cT++) {

			checkerLocations.clear();
			checkerCosts.clear();

			scanLine = new Scanner(scan.nextLine());
			while (scanLine.hasNext())
				checkerLocations.add(scanLine.nextInt() - 1);
			scanLine.close();

			scanLine = new Scanner(scan.nextLine());
			while (scanLine.hasNext())
				checkerCosts.add(scanLine.nextInt());
			scanLine.close();

			length = 75;//checkerLocations.size();

			size = ((length * (length + 1)) - ((length * length - 1) / 4))
					- length;

			graph = new int[size][size];

			initCheckerboard(graph, length, size);

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (graph[i][j] == 1)
						System.out.print(" 1");
					else
						System.out.print("-1");
				}
				System.out.println();
			}
		}
		out.close();
		scan.close();
	}
}
