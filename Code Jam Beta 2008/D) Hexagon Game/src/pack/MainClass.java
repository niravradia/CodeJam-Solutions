package pack;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainClass {

	public static Tile[] tiles;

	public static void addLink(Tile t1, Tile t2) {
		t1.addNeighbour(t2);
		t2.addNeighbour(t1);
	}

	public static void initCheckerboard(Tile[] tiles, int length, int size,
			int[][] solvedState) {

		int half = (length + 1) / 2;
		int cl = half, icl, i = 0, cr = 1, cc;
		int start, end;
		while (cr <= length) {

			cc = 0;
			if (cr < half)
				icl = cl;
			else
				icl = cl - 1;

			start = i;
			end = start + cl - 1;

			if (cr < half) {
				solvedState[1][cr - 1] = end - length / 2;
				solvedState[2][cr - 1] = start + length / 2;
			} else {
				solvedState[1][cr - 1] = start + length / 2;
				solvedState[2][cr - 1] = end - length / 2;
			}

			addLink(tiles[i], tiles[i + 1]);
			if (cr < half) {
				addLink(tiles[i], tiles[i + icl]);
			}
			if (cr != length)
				addLink(tiles[i], tiles[i + icl + 1]);
			tiles[i].setCartesianCoordinates(length - cl, Math.sqrt(3) * cr);

			if (cr == half)
				solvedState[0][0] = i;

			i++;
			cc++;

			for (int x = 1; x < cl - 1; x++) {

				addLink(tiles[i], tiles[i + 1]);

				if (cr != length) {
					addLink(tiles[i], tiles[i + icl]);
					addLink(tiles[i], tiles[i + icl + 1]);
				}

				tiles[i].setCartesianCoordinates((length - cl + 2 * cc),
						Math.sqrt(3) * cr);
				if (cr == half)
					solvedState[0][x] = i;

				i++;
				cc++;
			}

			if (cr != length)
				addLink(tiles[i], tiles[i + icl]);
			if (cr < half) {
				addLink(tiles[i], tiles[i + icl + 1]);
			}
			tiles[i].setCartesianCoordinates((length - cl + 2 * cc),
					Math.sqrt(3) * cr);

			if (cr == half)
				solvedState[0][length - 1] = i;
			i++;

			if (cr < half)
				cl++;
			else
				cl--;
			cr++;
		}
	}

	public static void updateStatus(int[] cLoc, int cost[], int solvedLoc[],
			int length, boolean[] settled, int countSettled, int[] highestClaim) {
		ArrayList<Integer> mint = new ArrayList<Integer>();
		Arrays.fill(highestClaim, -1);
		for (int x = countSettled; x < length; x++) {
			int min = -1;
			mint.clear();
			for (int t = 0; t < length; t++)
				if (!settled[t]) {
					if (min == -1
							|| min > tiles[cLoc[x]].steps(tiles[solvedLoc[t]])) {
						min = tiles[cLoc[x]].steps(tiles[solvedLoc[t]]);
						mint.clear();
						mint.add(t);
					} else if (min == tiles[cLoc[x]].steps(tiles[solvedLoc[t]]))
						mint.add(t);
				}
			for (int ti = 0; ti < mint.size(); ti++) {
				if (highestClaim[mint.get(ti)] < cost[x])
					highestClaim[mint.get(ti)] = cost[x];
			}
		}

	}

	public static int minCost(int[] cLoc, int cost[], int solvedLoc[],
			int length) {
		int s = 0;
		boolean settled[] = new boolean[length];
		Arrays.fill(settled, false);
		int highestClaim[] = new int[length];
		int min = -1, solutionLoc = -1;
		ArrayList<Integer> closest = new ArrayList<Integer>();

		for (int x = 0; x < length; x++) {
			min = -1;
			closest.clear();
			updateStatus(cLoc, cost, solvedLoc, length, settled, x + 1,
					highestClaim);
			for (int t = 0; t < length; t++)
				if (!settled[t]) {
					if (min == -1
							|| min > tiles[cLoc[x]].steps(tiles[solvedLoc[t]])) {
						min = tiles[cLoc[x]].steps(tiles[solvedLoc[t]]);
						closest.clear();
						closest.add(t);
					} else if (min == tiles[cLoc[x]].steps(tiles[solvedLoc[t]]))
						closest.add(t);
				}
			min = -1;
			for (int ti = 0; ti < closest.size(); ti++)
				if (min == -1
						|| min > tiles[cLoc[x]].steps(tiles[solvedLoc[closest
								.get(ti)]])) {
					min = tiles[cLoc[x]]
							.steps(tiles[solvedLoc[closest.get(ti)]]);
					solutionLoc = closest.get(ti);
				}
			s += min * cost[x];
			settled[solutionLoc] = true;
		}
		return s;
	}

	public static void main(String[] args) throws Exception {

		Scanner scan = new Scanner(new File("input.in"));
		PrintWriter out = new PrintWriter(new File("output.out"));
		Scanner scanLine;

		ArrayList<Integer> checkerLocations = new ArrayList<Integer>(), checkerCosts = new ArrayList<Integer>();

		int T = scan.nextInt(), length, size, solvedState[][];
		scan.nextLine();

		for (int cT = 1; cT <= T; cT++) {

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

			length = checkerLocations.size();

			solvedState = new int[3][length];

			size = ((length * (length + 1)) - ((length * length - 1) / 4))
					- length;
			tiles = new Tile[size];
			for (int i = 0; i < size; i++)
				tiles[i] = new Tile();
			initCheckerboard(tiles, length, size, solvedState);

			int t;
			for (int i = 0; i < checkerCosts.size(); i++)
				for (int j = i + 1; j < checkerCosts.size(); j++)
					if (checkerCosts.get(j) > checkerCosts.get(i)) {
						t = checkerCosts.get(j);
						checkerCosts.set(j, checkerCosts.get(i));
						checkerCosts.set(i, t);

						t = checkerLocations.get(j);
						checkerLocations.set(j, checkerLocations.get(i));
						checkerLocations.set(i, t);
					}
			int[] locs = new int[checkerLocations.size()], costs = new int[checkerLocations
					.size()];
			for (int i = 0; i < checkerLocations.size(); i++) {
				locs[i] = checkerLocations.get(i);
				costs[i] = checkerCosts.get(i);
			}

			/*
			 * for (int i = 0; i < size; i++) System.out.println(tiles[i]);
			 * System.out.println(Arrays.toString(solvedState[0]));
			 * System.out.println(Arrays.toString(solvedState[1]));
			 * System.out.println(Arrays.toString(solvedState[2]));
			 */
			int minCost[] = new int[3];
			for (int i = 0; i < 3; i++)
				minCost[i] = minCost(locs, costs, solvedState[i], length);
			out.print("Case #" + cT + ": ");
			if (minCost[0] < minCost[1] && minCost[0] < minCost[2])
				out.println(minCost[0]);
			else if (minCost[1] < minCost[2])
				out.println(minCost[1]);
			else
				out.println(minCost[2]);
		}
		out.close();
		scan.close();
	}
}
