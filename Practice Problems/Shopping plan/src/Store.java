/**
 * Created by Aditya on 7/14/2015.
 */
public class Store {
	int price[];
	int x, y;

	public Store(int x, int y, int numItems, int price[]) {
		this.x = x;
		this.y = y;

		this.price = price;
	}

	@Override
	public String toString() {
		String s = x + "  " + y + "  ";
		for (int i = 0; i < price.length; i++)
			s += price[i] + " ";
		s += '\n';
		return s;
	}
}
