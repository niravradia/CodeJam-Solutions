package pack;

import java.util.ArrayList;

public class Path {
	int cost;
	int totalPaths;
	ArrayList<Integer> preCityIndex;
	ArrayList<Integer> preRoadIndex;

	public Path() {
		cost = -1;
		totalPaths = 0;
		preCityIndex = new ArrayList<Integer>();
		preRoadIndex = new ArrayList<Integer>();
	}

	@Override
	public String toString() {
		return totalPaths + " " + preCityIndex.toString() + " "
				+ preRoadIndex.toString() + " " + cost;
	}
}
