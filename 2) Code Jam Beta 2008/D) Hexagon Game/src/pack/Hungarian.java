package pack;

import java.util.ArrayList;
import java.util.HashSet;

public class Hungarian {

	public static int[][] graph;
	public static int n;
	public static int[][] maxFlowCapacity, residualCapacity;
	public static ArrayList<Integer> augmentingPath = new ArrayList<Integer>();
	public static HashSet<Integer> minVertexCover = new HashSet<Integer>();
	public static ArrayList<Integer> maxMatchingLeftVertex = new ArrayList<Integer>();
	public static ArrayList<Integer> maxMatchingRightVertex = new ArrayList<Integer>();
	public static int augmentingCost;

	public static boolean findAugmentingPath(int i) {
		if (i == 2 * n + 1) {
			return true;
		} else
			for (int c = 0; c < 2 * n + 2; c++)
				if (c != i && !augmentingPath.contains(c)) {
					if ((i < c)
							&& (residualCapacity[i][c] != 0)
							|| (i > c)
							&& (maxFlowCapacity[c][i] - residualCapacity[c][i] > 0)) {
						augmentingPath.add(c);
						if (findAugmentingPath(c)) {
							if ((i < c))
								augmentingCost = augmentingCost > residualCapacity[i][c] ? residualCapacity[i][c]
										: augmentingCost;
							else
								augmentingCost = augmentingCost > maxFlowCapacity[c][i]
										- residualCapacity[c][i] ? maxFlowCapacity[c][i]
										- residualCapacity[c][i]
										: augmentingCost;
							return true;
						}
						augmentingPath.remove(augmentingPath.size() - 1);
					}
				}
		return false;
	}

	public static int maxMatching() {
		if (maxFlowCapacity == null) {
			maxFlowCapacity = new int[2 * n + 2][2 * n + 2];
			for (int i = 1; i <= n; i++) {
				maxFlowCapacity[0][i] = 1;
				maxFlowCapacity[n + i][2 * n + 1] = 1;
			}
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (graph[i][j] == 0)
						maxFlowCapacity[i + 1][n + j + 1] = 1;
			residualCapacity = maxFlowCapacity.clone();
		}
		augmentingPath.clear();
		augmentingPath.add(0);
		augmentingCost = 100;
		int p = 0, c;
		while (findAugmentingPath(0)) {
			for (int i = 1; i < augmentingPath.size(); i++) {
				c = augmentingPath.get(i);
				if (p < c) {
					residualCapacity[p][c] -= augmentingCost;
				} else {
					residualCapacity[c][p] += augmentingCost;
				}
				p = c;
			}
		}

		int s = 0;
		for (int i = 1; i <= n; i++)
			s += residualCapacity[n + i][2 * n + 1];
		return n - s;
	}

	public static int hungarian4() {
		int s = 0;

		for (int i = 0; i < n; i++) {
			int min = graph[i][0];
			for (int j = 1; j < n; j++)
				if (graph[i][j] < min)
					min = graph[i][j];
			for (int j = 0; j < n; j++)
				graph[i][j] -= min;
		}
		for (int i = 0; i < n; i++) {
			int min = graph[0][i];
			for (int j = 1; j < n; j++)
				if (graph[j][i] < min)
					min = graph[j][i];
			for (int j = 0; j < n; j++)
				graph[j][i] -= min;
		}

		maxFlowCapacity = residualCapacity = null;

		maxMatchingLeftVertex.clear();
		maxMatchingRightVertex.clear();
		minVertexCover.clear();

		HashSet<Integer> L = new HashSet<Integer>(), R = new HashSet<Integer>(), Z = new HashSet<Integer>();

		for (int i = 0; i < n; i++) {
			L.add(i + 1);
			R.add(n + i + 1);
			Z.add(i + 1);
			Z.add(n + i + 1);
		}
		while (maxMatching() < n) {
			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= n; j++)
					if (maxFlowCapacity[i][n + j] != 0
							&& residualCapacity[i][n + j] == 0) {
						maxMatchingLeftVertex.add(i);
						maxMatchingRightVertex.add(n + j);
					}
			for (int i = 0; i < maxMatchingLeftVertex.size(); i++) {
				Z.remove(new Integer(maxMatchingLeftVertex.get(i)));
				Z.remove(new Integer(maxMatchingRightVertex.get(i)));
			}
			
		}

		s = 0;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				if (maxFlowCapacity[i][n + j] != 0
						&& residualCapacity[i][n + j] == 0)
					s += graph[i - 1][j - 1];

		return s;
	}
}
