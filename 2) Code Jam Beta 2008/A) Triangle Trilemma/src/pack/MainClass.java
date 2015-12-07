package pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) throws FileNotFoundException {

		Scanner scan = new Scanner(new File("input.in"));
		PrintWriter out = new PrintWriter(new File("output.out"));

		int T = scan.nextInt();
		Triangle t;

		for (int cT = 1; cT <= T; cT++) {
			t = new Triangle(new Vertex(scan.nextInt(), scan.nextInt()),
					new Vertex(scan.nextInt(), scan.nextInt()),
					new Vertex(scan.nextInt(), scan.nextInt()));
			out.println("Case #" + cT + ": " + t.toString());
		}
		out.close();
		scan.close();
	}
}
