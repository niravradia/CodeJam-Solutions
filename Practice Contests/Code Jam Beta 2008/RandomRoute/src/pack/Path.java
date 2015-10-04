package pack;

import java.util.ArrayList;

public class Path {
	int cost;
	int totalPaths;
	ArrayList<Integer> reachedFrom;

	public Path() {
		cost = -1;
		totalPaths = 0;
		reachedFrom = new ArrayList<Integer>();
	}
}
