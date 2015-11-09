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
					new Vertex(scan.nextInt(), scan.nextInt()), new Vertex(
							scan.nextInt(), scan.nextInt()));
			out.print("Case #" + cT + ": ");
			if (t.area == 0)
				out.print("not a ");
			else if (t.e1 == t.e2 && t.e2 == t.e3)
				out.print("equilateral ");
			else {
				if (t.e1 == t.e2 || t.e2 == t.e3 || t.e3 == t.e1)
					out.print("isoscelus ");
				else
					out.print("scelene ");

				if (t.theta1 == Math.PI || t.theta2 == Math.PI
						|| t.theta3 == Math.PI)
					out.print("right ");
				else if (t.theta1 > Math.PI || t.theta2 > Math.PI
						|| t.theta3 > Math.PI)
					out.print("obtuse ");
				else
					out.print("acute ");
			}
			out.println("triangle");
		}
		out.close();
		scan.close();
	}
}
