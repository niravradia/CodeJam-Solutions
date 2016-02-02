package pack;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) throws Exception {

		Scanner scan = new Scanner(new File("input.in"));
		PrintWriter out = new PrintWriter(new File("output.out"));

		int T = scan.nextInt(), n;
		BigInteger solution;

		ArrayList<Long> v1 = new ArrayList<Long>(), v2 = new ArrayList<Long>();

		for (int cT = 1; cT <= T; cT++) {

			v1.clear();
			v2.clear();

			n = scan.nextInt();

			for (int i = 0; i < n; i++)
				v1.add(scan.nextLong());
			for (int i = 0; i < n; i++)
				v2.add(scan.nextLong());

			Collections.sort(v1);
			Collections.sort(v2);

			solution = BigInteger.valueOf(0);

			for (int i = 0; i < n; i++)
				solution = solution.add(BigInteger.valueOf(v1.get(i)
						* v2.get((n - i) - 1)));

			out.println("Case #" + cT + ": " + solution);
		}

		scan.close();
		out.close();

	}
}
