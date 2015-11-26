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

	public static void initCheckerboard(Tile[] tiles, int length, int size) {

		int half = (length + 1) / 2;
		int cl = half, icl, i = 0, cr = 1;
		while (cr < length) {

			if (cr < half)
				icl = cl;
			else
				icl = cl - 1;

			if (cr < half) {
				addLink(tiles[i], tiles[i + icl]);
			}
			addLink(tiles[i], tiles[i + icl + 1]);
			i++;

			for (int x = 1; x < cl - 1; x++) {
				addLink(tiles[i], tiles[i + icl]);
				addLink(tiles[i], tiles[i + icl + 1]);
				i++;
			}

			addLink(tiles[i], tiles[i + icl]);
			if (cr < half) {
				addLink(tiles[i], tiles[i + icl + 1]);
			}
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

		int T = scan.nextInt(), length, size;
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

			size = ((length * (length + 1)) - ((length * length - 1) / 4))
					- length;
			Tile[] tiles = new Tile[size];
			for (int i = 0; i < size; i++)
				tiles[i] = new Tile();
			initCheckerboard(tiles, length, size);
		}
		out.close();
		scan.close();
	}
}
