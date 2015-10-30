package pack;

import java.util.ArrayList;
import java.util.Arrays;

public class Store {
	double x, y;
	double price[];
	double distance[];

	Store(double x, double y, double[] price) {
		this.x = x;
		this.y = y;
		this.price = price;
	}

	public double distance(int j) {
		return distance[j];
	}

	public void initDist(ArrayList<Store> storeList, double price) {
		distance = new double[storeList.size()];
		for (int i = 0; i < storeList.size(); i++)
			distance[i] = price
					* Math.sqrt((this.x - storeList.get(i).x)
							* (this.x - storeList.get(i).x)
							+ (this.y - storeList.get(i).y)
							* (this.y - storeList.get(i).y));
	}

	public boolean hasItems(int[] itemsLeft) {
		for (int i = 0; i < price.length; i++)
			if (price[i] != -1 && itemsLeft[i] == 1)
				return true;
		return false;
	}

	@Override
	public String toString() {
		String s = "";
		s += Arrays.toString(price);
		return "(" + x + "," + y + ")->[" + s + "]\n";
	}

	@Override
	public boolean equals(Object s) {
		if (s instanceof Store)
			return this.x == ((Store) s).x && this.y == ((Store) s).y;
		else
			return false;
	}
}
