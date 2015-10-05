package pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;

public class MainClass {

	public static void traverse(Path[] shortestPaths,
			RoadList[][] referenceGraph, int currentIndex, BigDecimal value,
			LinkedList<Road> roads, int sourceIndex) {

		if (currentIndex == sourceIndex) {
			for (int z = 0; z < roads.size(); z++) {
				roads.get(z).probability = roads.get(z).probability.add(value);
			}
			return;
		}

		for (int t = 0; t < shortestPaths[currentIndex].preCityIndex.size(); t++) {
			roads.add(referenceGraph[shortestPaths[currentIndex].preCityIndex
					.get(t)][currentIndex].list
					.get(shortestPaths[currentIndex].preRoadIndex.get(t)));
			traverse(shortestPaths, referenceGraph,
					shortestPaths[currentIndex].preCityIndex.get(t), value,
					roads, sourceIndex);
			roads.remove(roads.size() - 1);
		}
	}

	public static void main(String[] args) {
		File fin = new File("input.in");
		File fout = new File("output.out");
		Scanner scan = null;
		// try {
		// System.setOut(new PrintStream(fout));
		// } catch (FileNotFoundException e1) {
		// e1.printStackTrace();
		// }

		TreeSet<City> cityList = new TreeSet<City>();
		ArrayList<City> cityListArray = new ArrayList<City>();
		ArrayList<Road> roadListArray = new ArrayList<Road>();

		RoadList referenceGraph[][];
		Path shortestPaths[];
		City startingCity;
		Road currentRoad;

		City src, dest, current;
		int cost;

		int T;

		int numRoad;
		int reachableCitiesCount;
		BigDecimal pCity, pRoute;

		Iterator<City> iteratorC;
		Iterator<Road> iteratorR;
		try {
			scan = new Scanner(fin);
			T = scan.nextInt();
			for (int i = 0; i < T; i++) {
				cityListArray.clear();
				roadListArray.clear();
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

					roadListArray.add(new Road(src, dest, cost));
				}

				referenceGraph = new RoadList[cityList.size()][cityList.size()];
				for (int w = 0; w < cityList.size(); w++)
					for (int x = 0; x < cityList.size(); x++) {
						referenceGraph[w][x] = new RoadList();
					}
				cityListArray = new ArrayList<City>(cityList);

				iteratorR = roadListArray.iterator();

				while (iteratorR.hasNext()) {
					currentRoad = iteratorR.next();
					referenceGraph[cityListArray.indexOf(currentRoad.src)][cityListArray
							.indexOf(currentRoad.dest)].list.add(currentRoad);
				}

				shortestPaths = new Path[cityListArray.size()];

				for (int z = 0; z < cityListArray.size(); z++)
					shortestPaths[z] = new Path();

				shortestPaths[cityListArray.indexOf(startingCity)].cost = 0;
				shortestPaths[cityListArray.indexOf(startingCity)].preCityIndex
						.add(cityListArray.indexOf(startingCity));
				shortestPaths[cityListArray.indexOf(startingCity)].preRoadIndex
						.add(-1);
				shortestPaths[cityListArray.indexOf(startingCity)].totalPaths = 1;

				boolean altered = true;
				while (altered) {
					altered = false;

					for (int c = 0; c < cityListArray.size(); c++)
						if (shortestPaths[c].cost != 0) {
							for (int p = 0; p < cityListArray.size(); p++)
								for (int roadIndex = 0; roadIndex < referenceGraph[p][c].list
										.size(); roadIndex++)
									if (shortestPaths[p].cost != -1) {
										if (shortestPaths[c].cost == -1
												|| shortestPaths[c].cost > (referenceGraph[p][c].list
														.get(roadIndex).cost + shortestPaths[p].cost)) {
											altered = true;
											shortestPaths[c].cost = referenceGraph[p][c].list
													.get(roadIndex).cost
													+ shortestPaths[p].cost;
											shortestPaths[c].preCityIndex
													.clear();
											shortestPaths[c].preRoadIndex
													.clear();
											shortestPaths[c].totalPaths = shortestPaths[p].totalPaths;
											shortestPaths[c].preCityIndex
													.add(p);
											shortestPaths[c].preRoadIndex
													.add(roadIndex);
										} else if (altered
												&& shortestPaths[c].cost == (referenceGraph[p][c].list
														.get(roadIndex).cost + shortestPaths[p].cost)) {
											boolean redundent = false;
											for (int r = 0; r < shortestPaths[c].preCityIndex
													.size(); r++)
												if (shortestPaths[c].preCityIndex
														.get(r) == p
														&& shortestPaths[c].preRoadIndex
																.get(r) == roadIndex) {
													redundent = true;
													break;
												}
											if (!redundent) {
												shortestPaths[c].totalPaths += shortestPaths[p].totalPaths;

												shortestPaths[c].preCityIndex
														.add(p);
												shortestPaths[c].preRoadIndex
														.add(roadIndex);
											}
										}
									}
						}
				}

				reachableCitiesCount = 0;
				for (int l = 0; l < cityListArray.size(); l++) {
					if (shortestPaths[l].cost != -1
							&& shortestPaths[l].cost != 0)
						reachableCitiesCount++;
				}
				pCity = new BigDecimal(1).divide(new BigDecimal(
						reachableCitiesCount), 9, RoundingMode.HALF_UP);

				for (int l = 0; l < cityListArray.size(); l++)
					if (shortestPaths[l].cost != -1
							&& shortestPaths[l].cost != 0) {
						pRoute = pCity.divide(new BigDecimal(
								shortestPaths[l].totalPaths), 9,
								RoundingMode.HALF_UP);

						traverse(shortestPaths, referenceGraph, l, pRoute,
								new LinkedList<Road>(),
								cityListArray.indexOf(startingCity));
					}

				System.out.print("Case #" + (i + 1) + ":");
				for (int z = 0; z < roadListArray.size(); z++) {
					System.out.print(" ");
					System.out.print(String.format("%.7f",
							roadListArray.get(z).probability));
				}
				System.out.print("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scan.close();
			System.out.close();
		}
	}
}
