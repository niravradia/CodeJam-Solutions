package pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ShoppingPlan {

	static double optimalCost[][];
	public static Store start = new Store(0, 0, null);

	public static int atoi(int[] a) {
		int i = 0;
		for (int x = 0; x < a.length; x++)
			i += exp(x) * a[x];
		return i;
	}

	public static int[] itoa(int i, int l) {
		int a[] = new int[l];
		for (int x = 0; x < l; x++) {
			a[x] = i & 1;
			i = i >>> 1;
		}
		return a;
	}

	public static int exp(int a) {
		return (int) Math.pow(2, a);
	}

	public static double findMinCost(int itemsLeft, Store currentStore,
			ArrayList<Store> storeList, boolean[] perishable, int depth) {
		double result = 0, optimalResult = -1;
		boolean perished = false;

		if (!currentStore.equals(start))
			if (optimalCost[itemsLeft][storeList.indexOf(currentStore)] != -1) {
				return optimalCost[itemsLeft][storeList.indexOf(currentStore)];
			}
		int totalOptions = 0;
		for (int i = 0; i < currentStore.price.length; i++)
			if ((((itemsLeft >>> i) & 1) == 1) && currentStore.price[i] != -1)
				totalOptions++;
		int optionAt[] = new int[totalOptions];
		int counter = 0;

		for (int i = 0; i < currentStore.price.length; i++)
			if ((((itemsLeft >>> i) & 1) == 1) && currentStore.price[i] != -1)
				optionAt[counter++] = i;

		int choice;
		int init = 1;
		if (currentStore.equals(start))
			init = 0;
		int lim = exp(totalOptions);
		for (int c = init; c < lim; c++) {
			choice = c;

			result = 0;
			perished = false;
			for (int i = 0; i < totalOptions; i++)
				if ((((choice >>> i) & 1) == 1)) {
					result += currentStore.price[optionAt[i]];
					itemsLeft &= 0xFFFF ^ (1 << optionAt[i]);
					if (perishable[optionAt[i]])
						perished = true;
				}

			boolean finished = true;
			int til = itemsLeft;
			while (til > 0) {
				if ((til & 1) == 1)
					finished = false;
				til >>>= 1;
			}
			if (finished) {
				result += currentStore.distance(storeList.size() - 1);
				if (optimalResult == -1 || optimalResult > result)
					optimalResult = result;
			} else {
				double tempResult = result;
				for (int i = 0; i < storeList.size(); i++)
					if (storeList.get(i).hasItems(itemsLeft)) {

						if (!perished)
							result += currentStore.distance((i))
									+ findMinCost(itemsLeft, storeList.get(i),
											storeList, perishable, depth + 1);
						else
							result += currentStore
									.distance(storeList.size() - 1)
									+ start.distance((i))
									+ findMinCost(itemsLeft, storeList.get(i),
											storeList, perishable, depth + 1);

						if (optimalResult == -1 || optimalResult > result)
							optimalResult = result;

						result = tempResult;

					}
			}
			for (int i = 0; i < totalOptions; i++)
				if ((((choice >>> i) & 1) == 1)) {
					itemsLeft |= 1 << optionAt[i];
				}

		}

		if (!currentStore.equals(start))
			optimalCost[itemsLeft][storeList.indexOf(currentStore)] = optimalResult;

		if (!currentStore.equals(start))
			return (optimalResult);
		else
			return optimalResult;
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public static void main(String[] args) {
		File fin = new File("input.in");
		File fout = new File("output.out");
		PrintWriter out = null;
		Scanner scan = null;
		Scanner scanLine = null;

		try {
			out = new PrintWriter(fout);
			scan = new Scanner(fin);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int T = scan.nextInt();
		int numStores, numItems;
		double priceGas;
		ArrayList<String> itemList = new ArrayList<String>();
		ArrayList<Store> storeList = new ArrayList<Store>();
		boolean perishable[] = null;

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

			int expItems = exp(numItems);
			optimalCost = new double[expItems][numStores];

			for (int x = 0; x < expItems; x++)
				for (int y = 0; y < numStores; y++)
					optimalCost[x][y] = -1;

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

			storeList.add(start);

			for (int i = 0; i < storeList.size(); i++)
				storeList.get(i).initDist(storeList, priceGas);

			for (int i = 0; i < numStores; i++)
				optimalCost[0][i] = start.distance((i));

			int[] itemsLeft = new int[itemList.size()];
			Arrays.fill(itemsLeft, 1);
			double[] noItems = new double[itemList.size()];
			Arrays.fill(noItems, -1);
			start.price = noItems;

			out.format(
					"case #%d: %.7f\n",
					cT,
					+round(findMinCost(exp(itemList.size()) - 1, start,
							storeList, perishable, 0), 7));
			System.out.format(
					"case #%d: %.7f\n",
					cT,
					+round(findMinCost(exp(itemList.size()) - 1, start,
							storeList, perishable, 0), 7));
		}
		out.close();
		scan.close();
		scanLine.close();
	}
}
