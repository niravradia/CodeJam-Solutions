package pack;

public class RecursiveApproach {

	public static int getSolution(int[][] solution, int[][] graph, int length,
			int totalStates, int current, int availableEdges) {
		if (current == -1)
			return 0;
		if (solution[current][availableEdges] != -1)
			return solution[current][availableEdges];
		int s = -1, ts;
		for (int ce = 0, ie = 1; ce < length; ce++, ie = ie << 1) {
			if ((ie & availableEdges) > 0) {
				ts = graph[current][ce]
						+ getSolution(solution, graph, length, totalStates,
								current - 1, availableEdges ^ ie);
				if (ts < s || s == -1)
					s = ts;
			}
		}
		solution[current][availableEdges] = s;
		return s;
	}

}
