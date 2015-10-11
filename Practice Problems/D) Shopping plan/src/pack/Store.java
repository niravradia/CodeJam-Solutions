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

	@Override
	public String toString() {
		String s="";
		for (int i = 0; i < price.length; i++)
			s += "  " + price[i];
		return x + " " + y + "->" + s;
	}
}
