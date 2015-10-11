package pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ShoppingPlan {

	public static void main(String[] args) {
		File fin = new File("input.in");
		File fout = new File("output.out");
		Scanner scan = null;
		Scanner scanLine = null;

		try {
			scan = new Scanner(fin);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int T = scan.nextInt();
		int numStores, numItems;
		double priceGas;
		ArrayList<String> itemList = new ArrayList<String>();
		ArrayList<Store> storeList = new ArrayList<Store>();
		boolean perishable[];

		String s = null;
		double lx, ly;
		double price[];

		for (int cT = 1; cT <= T; cT++) {
			itemList.clear();
			storeList.clear();
			numItems = scan.nextInt();
			numStores = scan.nextInt();
			priceGas = scan.nextDouble();
			perishable = new boolean[numItems];

			System.out.println(numItems + "  " + numStores);
			for (int i = 0; i < numItems; i++)
				if ((s = scan.next()).charAt(s.length() - 1) == '!') {
					perishable[i] = true;
					itemList.add(s.substring(0, s.length() - 1));
				} else
					itemList.add(s);

			scan.nextLine();
			for (int i = 0; i < numStores; i++) {

				price = new double[numItems];
				Arrays.fill(price, -1);
				try {
					scanLine = new Scanner(scan.nextLine());
				} catch (Exception e) {
					e.printStackTrace();
				}
				lx = scanLine.nextDouble();
				ly = scanLine.nextDouble();

				storeList.add(new Store(lx, ly, price));

				while (scanLine.hasNext()) {
					s = scanLine.next();
					price[itemList.indexOf(s.substring(0, s.indexOf(":")))] = Double
							.parseDouble(s.substring(s.indexOf(":") + 1,
									s.length()));
				}
			}

			for (int z = 0; z < numItems; z++)
				System.out.print("  " + itemList.get(z));
			for (int z = 0; z < numStores; z++)
				System.out.println(storeList.get(z).toString());
		}
	}
}
