package pack;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainClass {

	public static int clockToMinutes(String s) {
		int t = 0;
		t += 60 * Integer.parseInt(s.substring(0, 2));
		t += Integer.parseInt(s.substring(3, 5));
		return t;
	}

	public static void main(String[] args) throws Exception {

		Scanner scan = new Scanner(new File("input.in"));
		PrintWriter out = new PrintWriter(new File("output.out"));

		int T = scan.nextInt();
		int turnAroundTime, na, nb;
		int scheduleA[][], scheduleB[][];

		for (int cT = 1; cT <= T; cT++) {

			turnAroundTime = scan.nextInt();
			na = scan.nextInt();
			nb = scan.nextInt();

			scheduleA = new int[na][2];
			scheduleB = new int[nb][2];

			for (int i = 0; i < na; i++) {
				scheduleA[i][0] = clockToMinutes(scan.next());
				scheduleA[i][1] = clockToMinutes(scan.next());
			}
			for (int i = 0; i < nb; i++) {
				scheduleB[i][0] = clockToMinutes(scan.next());
				scheduleB[i][1] = clockToMinutes(scan.next());
			}

		}

		scan.close();
		out.close();

	}

}
