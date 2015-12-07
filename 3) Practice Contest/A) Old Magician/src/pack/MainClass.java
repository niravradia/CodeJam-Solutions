package pack;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(new File("input.in"));
		PrintWriter out = new PrintWriter(new File("output.out"));

		int T = scan.nextInt();
		for (int cT = 1; cT <= T; cT++) {
			scan.nextLong();
			out.print("Case #" + cT + ": ");
			if (scan.nextLong() % 2 == 0)
				out.println("WHITE");
			else
				out.println("BLACK");
		}
		scan.close();
		out.close();
	}

}
