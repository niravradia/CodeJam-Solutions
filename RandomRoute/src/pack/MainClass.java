package pack;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class MainClass {

	public static void main(String[] args) {
		File fin = new File("input.in");
		Scanner scan = null;

		TreeSet<City> cityList = new TreeSet<City>();
		ArrayList<City> cityListArray = new ArrayList<City>();
		LinkedHashSet<Road> roadList = new LinkedHashSet<Road>();
		int graph[][];
		Road referenceGraph[][];
		Path shortestPaths[];
		City startingCity;
		Road currentRoad;

		City src, dest, current;
		int cost;

		int T;

		int numRoad;

		Iterator<City> iteratorC;
		Iterator<Road> iteratorR;
		try {
			scan = new Scanner(fin);
			T = scan.nextInt();
			for (int i = 0; i < T; i++) {
				cityListArray.clear();
				roadList.clear();
				cityList.clear();

				numRoad = scan.nextInt();
				startingCity = new City(scan.next());
				cityList.add(startingCity);

				for (int j = 0; j < numRoad; j++) {
					src = new City(scan.next());
					dest = new City(scan.next());
					cost = scan.nextInt();

					cityList.add(src);
					cityList.add(dest);

					iteratorC = cityList.iterator();
					while (iteratorC.hasNext()) {
						current = iteratorC.next();
						if (current.equals(src))
							src = current;
						if (current.equals(dest))
							dest = current;
					}

					roadList.add(new Road(src, dest, cost));
				}

				graph = new int[cityList.size()][cityList.size()];
				referenceGraph = new Road[cityList.size()][cityList.size()];
				for (int w = 0; w < cityList.size(); w++)
					for (int x = 0; x < cityList.size(); x++) {
						graph[w][x] = -1;
						referenceGraph[w][x] = null;
					}
				cityListArray = new ArrayList<City>(cityList);

				iteratorR = roadList.iterator();

				while (iteratorR.hasNext()) {
					currentRoad = iteratorR.next();
					graph[cityListArray.indexOf(currentRoad.src)][cityListArray
							.indexOf(currentRoad.dest)] = currentRoad.cost;
					referenceGraph[cityListArray.indexOf(currentRoad.src)][cityListArray
							.indexOf(currentRoad.dest)] = currentRoad;
				}

				shortestPaths = new Path[cityListArray.size()];

				for (int z = 0; z < cityListArray.size(); z++)
					shortestPaths[z] = new Path();

				shortestPaths[cityListArray.indexOf(startingCity)].cost = 0;

				boolean altered = true;
				while (altered) {
					altered = false;

					for (int c = 0; c < cityListArray.size(); c++)
						if (shortestPaths[c].cost != 0) {
							for (int p = 0; p < cityListArray.size(); p++)
								if (graph[p][c] != -1
										&& shortestPaths[p].cost != -1) {
									if (shortestPaths[c].cost == -1
											|| shortestPaths[c].cost > (graph[p][c] + shortestPaths[p].cost)) {
										altered = true;
										shortestPaths[c].cost = graph[p][c]
												+ shortestPaths[p].cost;
										shortestPaths[c].reachedFrom.clear();
										shortestPaths[c].reachedFrom.add(p);
									} else if (altered
											&& shortestPaths[c].cost == (graph[p][c] + shortestPaths[p].cost)) {
										shortestPaths[c].reachedFrom.add(p);
									}
								}
						}
				}

				for (int l = 0; l < cityListArray.size(); l++)
					System.out.println(shortestPaths[l].cost + "  "
							+ shortestPaths[l].reachedFrom.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scan.close();
		}
	}
}
