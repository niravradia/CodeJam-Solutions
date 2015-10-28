package pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Verifier {

	public static void main(String s[]) throws Exception {
		Scanner s1 = new Scanner(new File("output.out"));
		Scanner s2 = new Scanner(new File("outputC.out"));

		String a, b;
		while (s1.hasNext()) {
			a = s1.next();
			b = s2.next();
			try {
				if (Double.parseDouble(a) != Double.parseDouble(b))
					System.out.println(a + "  " + b);
			} catch (Exception e) {

			}
		}
	}

}
