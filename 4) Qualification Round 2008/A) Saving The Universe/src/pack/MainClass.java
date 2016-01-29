package pack;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) throws Exception {

		Scanner scan = new Scanner(new File("input.in"));
		PrintWriter out = new PrintWriter(new File("output.out"));

		int T = scan.nextInt(), s, q,c;
		String temp;

		HashSet<String> set = new HashSet<String>();

		scan.nextLine();
		for (int cT = 1; cT <= T; cT++) {
			set.clear();
			c = 0;
			s = scan.nextInt();

			for (int i = 0; i <= s; i++)
				scan.nextLine();

			q = scan.nextInt();
			scan.nextLine();

			for (int i = 0; i < q; i++) {
				set.add(temp = scan.nextLine());
				if (set.size() == s) {
					c++;
					set.clear();
					set.add(temp);
				}
			}
			out.println("Case #"+ cT+": "+c);
		}
		
		scan.close();
		out.close();
	}

}
