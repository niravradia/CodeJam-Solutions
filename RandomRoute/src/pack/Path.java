package pack;

import java.util.ArrayList;

public class Path {
	int cost;
	ArrayList<Integer> reachedFrom;

	public Path() {
		cost = -1;
		reachedFrom = new ArrayList<Integer>();
	}
}
