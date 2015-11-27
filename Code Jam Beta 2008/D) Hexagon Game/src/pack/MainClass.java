package pack;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {

	public static void addLink(Tile t1, Tile t2) {
		t1.addNeighbour(t2);
		t2.addNeighbour(t1);
	}

	public static void initCheckerboard(Tile[] tiles, int length, int size,
			int[][] solvedState) {

		int half = (length + 1) / 2;
		int cl = half, icl, i = 0, cr = 1, cc;
		while (cr <= length) {

			cc = 0;
			if (cr < half)
				icl = cl;
			else
				icl = cl - 1;

			addLink(tiles[i], tiles[i + 1]);
			if (cr < half) {
				addLink(tiles[i], tiles[i + icl]);
			}
			if (cr != length)
				addLink(tiles[i], tiles[i + icl + 1]);
			tiles[i].setCartesianCoordinates((length - cl) * 2, Math.sqrt(3)
					* cr);
			i++;
			cc++;

			for (int x = 1; x < cl - 1; x++) {

				addLink(tiles[i], tiles[i + 1]);

				if (cr != length) {
					addLink(tiles[i], tiles[i + icl]);
					addLink(tiles[i], tiles[i + icl + 1]);
				}

				tiles[i].setCartesianCoordinates(2 * (length - cl + cc),
						Math.sqrt(3) * cr);

				i++;
				cc++;
			}

			if (cr != length)
				addLink(tiles[i], tiles[i + icl]);
			if (cr < half) {
				addLink(tiles[i], tiles[i + icl + 1]);
			}
			tiles[i].setCartesianCoordinates(2 * (length - cl + cc),
					Math.sqrt(3) * cr);

			i++;

			if (cr < half)
				cl++;
			else
				cl--;
			cr++;
		}
	}

	public static void main(String[] args) throws Exception {

		Scanner scan = new Scanner(new File("input.in"));
		PrintWriter out = new PrintWriter(new File("output.out"));
		Scanner scanLine;

		ArrayList<Integer> checkerLocations = new ArrayList<Integer>(), checkerCosts = new ArrayList<Integer>();

		int T = scan.nextInt(), length, size, solvedState[][];
		scan.nextLine();

		for (int cT = 0; cT < T; cT++) {

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

			length = 5;

			solvedState = new int[3][length];

			size = ((length * (length + 1)) - ((length * length - 1) / 4))
					- length;
			Tile[] tiles = new Tile[size];
			for (int i = 0; i < size; i++)
				tiles[i] = new Tile();
			initCheckerboard(tiles, length, size, solvedState);

			for (int i = 0; i < size; i++)
				System.out.println(tiles[i]);
		}
		out.close();
		scan.close();
	}
}
