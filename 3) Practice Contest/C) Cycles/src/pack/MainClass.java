package pack;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainClass {

	public static int n, k, edges[][], degree[];
	public static ArrayList<Integer> vtoe[];

	public static int getPathCount(int s) {
		int solution = 0;

		int l = 0, st = s;
		while (st > 0) {
			l += st & 1;
			st = st >> 1;
		}
		int activeEdge[] = new int[l];
		int c = 0;
		for (int i = 1, index = 0; i <= s; i = i << 1, index++)
			if ((i & s) > 0) {
				activeEdge[c++] = index;
			}

		Arrays.fill(degree, 0);
		for (int i = 0; i < l; i++) {
			if (++degree[edges[activeEdge[i]][0]] > 2
					|| ++degree[edges[activeEdge[i]][1]] > 2)
				return 0;
		}

		boolean checkedForCycle[] = new boolean[l];
		ArrayList<Integer> activeEdgeList = new ArrayList<Integer>();
		for (int ii = 0; ii < activeEdge.length; ii++)
			activeEdgeList.add(activeEdge[ii]);

		int startingEdge, currentEdge;
		boolean cycleDetected = false;
		int cycleLength;
		for (int i = 0; i < l; i++)
			if (!checkedForCycle[i]) {
				startingEdge = currentEdge = i;
				cycleLength = 1;
				checkedForCycle[i] = true;
				while (degree[edges[activeEdge[i]][0]] == 2) {
					for (int ii = 0; ii < vtoe[edges[activeEdge[i]][0]].size(); ii++) {
						if (vtoe[edges[activeEdge[i]][0]].get(ii) != currentEdge
								&& activeEdgeList
										.indexOf(vtoe[edges[activeEdge[i]][0]]
												.get(ii)) > -1) {
							currentEdge = vtoe[edges[activeEdge[i]][0]].get(ii);
							cycleLength++;
							checkedForCycle[i] = true;
							if (currentEdge == startingEdge) {
								cycleDetected = true;
								break;
							}
						}
					}
				}
			}

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
			for (int i = 0; i < n; i++)
				vtoe[i] = new ArrayList<Integer>();
			edges = new int[k][2];
			count = new int[1 << k];

			for (int i = 0; i < k; i++) {
				edges[i][0] = scan.nextInt() - 1;
				edges[i][1] = scan.nextInt() - 1;

				vtoe[edges[i][0]].add(i);
				vtoe[edges[i][1]].add(i);
			}
			Arrays.fill(vtoe, -1);
			for (int i = 1; i < 1 << k; i++)
				count[i] = getPathCount(i);
		}
		scan.close();
		out.close();
	}
}
