package pack;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) throws Exception {

		Scanner scanLine = new Scanner(new File("input.in"));
		PrintWriter out = new PrintWriter(new File("output.out"));

		ArrayList<String> itemList = new ArrayList<String>();
		ArrayList<Integer> priceList = new ArrayList<Integer>();

		Scanner scan = new Scanner(scanLine.nextLine());
		int T = scan.nextInt(), itemCount;

		for (int cT = 1; cT <= T; cT++) {
			scan.close();
			itemList.clear();
			priceList.clear();

			scan = new Scanner(scanLine.nextLine());
			while (scan.hasNext())
				itemList.add(scan.next());
			scan = new Scanner(scanLine.nextLine());
			while (scan.hasNext())
				priceList.add(scan.nextInt());

			itemCount = itemList.size();
			int table[] = new int[itemCount];
			Arrays.fill(table, 1);
			for (int i = 0; i < itemCount; i++)
				for (int j = 0; j < i; j++)
					if (priceList.get(j) < priceList.get(i)) {
						if (table[i] <= table[j]) {
							table[i] = 1 + table[j];
						} else if (table[i] == table[j] + 1) {
						}
					}

		}

		out.close();
		scanLine.close();
	}
}
