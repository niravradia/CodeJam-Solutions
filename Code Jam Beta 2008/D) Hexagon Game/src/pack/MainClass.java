package pack;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {

	public static void initCheckerboard(int[][] graph, int size) {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				graph[i][j] = -1;

		int half = (size + 1) / 2;
		int cl = half, i = 0;
		while (cl >= half) {

			if (cl < size)
				cl++;
			else
				cl--;
		}
	}

	public static void main(String[] args) throws Exception {

		Scanner scan = new Scanner(new File("input.in"));
		PrintWriter out = new PrintWriter(new File("output.out"));
		Scanner scanLine;

		ArrayList<Integer> checkerLocations = new ArrayList<Integer>(), checkerCosts = new ArrayList<Integer>();

		int T = scan.nextInt(), length, size, graph[][];

		for (int cT = 0; cT < T; cT++) {

			checkerLocations.clear();
			checkerCosts.clear();

			scanLine = new Scanner(scan.nextLine());
			while (scanLine.hasNext())
				checkerLocations.add(scanLine.nextInt());
			scanLine.close();

			scanLine = new Scanner(scan.nextLine());
			while (scanLine.hasNext())
				checkerCosts.add(scanLine.nextInt());
			scanLine.close();

			length = checkerLocations.size();

			size = ((length * (length + 1)) - ((length * length - 1) / 4))
					- length;

			graph = new int[size][size];

			initCheckerboard(graph, size);
		}
		out.close();
		scan.close();
	}
}
