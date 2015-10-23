package pack;

public class Store {
	double x, y;
	double price[];

	Store(double x, double y, double[] price) {
		this.x = x;
		this.y = y;
		this.price = price;
	}

	public double distance(Store s) {
		return Math.sqrt((this.x - s.x) * (this.x - s.x) + (this.y - s.y)
				* (this.y - s.y));
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
		for (int i = 0; i < price.length; i++)
			s += "  " + price[i];
		return x + " " + y + "->" + s;
	}

	@Override
	public boolean equals(Object s) {
		if (s instanceof Store)
			return this.x == ((Store) s).x && this.y == ((Store) s).y;
		else
			return false;
	}
}
