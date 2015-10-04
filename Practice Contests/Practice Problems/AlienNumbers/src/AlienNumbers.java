import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class AlienNumbers {

	public static void main(String s[]) {
		File fin = new File("input.in");
		File fout = new File("output.out");
		Scanner scan = null;
		try {
			scan = new Scanner(fin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter write = null;
		try {
			write = new PrintWriter(fout);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int T = scan.nextInt();
		String n, sl, tl, sol;
		int sb, tb;
		long num;
		for (int cT = 1; cT <= T; cT++) {
			n = scan.next();
			sl = scan.next();
			tl = scan.next();

			sb = sl.length();
			tb = tl.length();

			num = 0;
			sol = new String();
			for (int i = 0; i < n.length(); i++) {
				char cd = n.charAt(i);
				double pv = Math.pow(sb, n.length() - i - 1);
				num += pv * sl.indexOf(cd);
			}
			int cr;
			while (num > 0) {
				cr = (int) num % tb;
				num /= tb;
				sol += tl.charAt(cr);
			}
			String rsol = "";
			for (int i = 0; i < sol.length(); i++)
				rsol += sol.charAt(sol.length() - i - 1);

			System.out.println(rsol);
			write.write("Case #" + cT + ": " + rsol + '\n');
		}
		write.close();
	}
}
