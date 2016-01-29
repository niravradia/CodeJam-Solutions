package pack;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainClass {

	public static int n, k, edges[][], degree[];
	public static ArrayListI[] vtoe;

	static class ArrayListI {
		private ArrayList<Integer> list;

		public ArrayListI() {
			list = new ArrayList<Integer>();
		}

		public int size() {
			return list.size();
		}

		public int get(int i) {
			return list.get(i);
		}

		public void add(int i) {
			list.add(i);
		}

	}

	public static int factorial(int i) {
		return i == 0 ? 1 : i * factorial(i - 1);
	}

	public static int countOnes(int i) {
		int c = 0;
		while (i > 0) {
			if ((i & 1) > 0)
				c++;
			i >>>= 1;
		}
		return c;
	}

	public static int getPathCount(int s) {

		int solution = 0;

		System.out.println("s = " + s);

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

		int startingEdge, currentEdge, previousEdge, traversedVertex = -1;
		boolean cycleDetected = false;
		int cycleLength = 1;
		outer: for (int i = 0; i < l; i++)
			if (!checkedForCycle[i]) {
				startingEdge = currentEdge = previousEdge = i;
				cycleLength = 1;
				checkedForCycle[i] = true;

				int v = 0;
				while (degree[edges[currentEdge][traversedVertex == -1
						|| traversedVertex != edges[currentEdge][0] ? 0 : 1]] == 2) {

					v = (traversedVertex == -1 || traversedVertex != edges[currentEdge][0]) ? 0
							: 1;
					
					System.out.println(currentEdge +" from "+ startingEdge+" vertex "+v+"  travV"+ traversedVertex);
					for (int ii = 0; ii < vtoe[edges[currentEdge][v]].size(); ii++) {
						if (vtoe[edges[currentEdge][v]].get(ii) != currentEdge
								&& activeEdgeList
										.indexOf(vtoe[edges[currentEdge][v]]
												.get(ii)) > -1) {
							previousEdge = currentEdge;
							traversedVertex = edges[currentEdge][v];
							currentEdge = vtoe[edges[currentEdge][v]].get(ii);
							cycleLength++;
							checkedForCycle[activeEdgeList.indexOf(currentEdge)] = true;
							if (currentEdge == startingEdge) {
								cycleDetected = true;
								break outer;
							}
						}
					}
				}
			}

		if (cycleDetected) {
			if (cycleLength < n)
				return 0;
			else
				return 1;
		}

		int countVertices = 0;
		double countGroupsDouble = 0;
		for (int i = 0; i < n; i++)
			if (degree[i] == 0)
				countVertices++;
			else if (degree[i] == 1)
				countGroupsDouble += 0.5;

		int countGroups = (int) countGroupsDouble;

		solution = factorial(countGroups + countVertices - 1)
				* (1 << countGroups) / 2;

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
			vtoe = new ArrayListI[n];
			for (int i = 0; i < n; i++)
				vtoe[i] = new ArrayListI();
			edges = new int[k][2];
			count = new int[1 << k];

			for (int i = 0; i < k; i++) {
				edges[i][0] = scan.nextInt() - 1;
				edges[i][1] = scan.nextInt() - 1;

				vtoe[edges[i][0]].add(i);
				vtoe[edges[i][1]].add(i);
			}

			for (int i = 1; i < 1 << k; i++)
				count[i] = getPathCount(i);

			int solution = 0;
			for (int i = 1; i < 1 << k; i++)
				solution += Math.pow(-1, 1 + countOnes(i)) * count[i];

			System.out.println("Case #" + cT + ": "
					+ ((factorial(n - 1)) / 2 - solution));
		}
		scan.close();
		out.close();
	}
}
