package pack;

public class Hungarian {

	public static int hungarian4(int[][] graph, int n) {
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

		return s;
	}
}
