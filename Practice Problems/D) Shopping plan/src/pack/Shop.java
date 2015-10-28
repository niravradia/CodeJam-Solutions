package pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Shop {
	double x;
	double y;
	int[] price;
	double[] minAmount;
	double[] minPersAmount;
	double[] route;
	int itemsMask;
	
	static Shop[] shops;
	static int persMask;
	static Queue<Event> events;
	
	static class Event implements Comparable<Event> {
		boolean pers;
		double amount; 
		Shop shop;
		int mask;
		
		public Event(Shop shop, boolean pers, int mask, double amount) {
			this.pers = pers;
			this.shop = shop;
			this.mask = mask;
			this.amount = amount;
		}

		@Override
		public int compareTo(Event that) {
			return Double.compare(this.amount, that.amount);
		}
		
		private void process() {
			shop.process(pers, mask, amount);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(new File("input.in"));
		System.setOut(new PrintStream(new File("outputC.out")));
		
		int tests = scanner.nextInt();
		for (int test = 0; test < tests; test++) {
			int itemCount = scanner.nextInt();
			int shopCount = scanner.nextInt();
			double gasPrice = scanner.nextDouble();
						
			Shop.persMask = 0;
			List<String> items = new ArrayList<String>();
			for (int i = 0; i < itemCount; i++) {
				String item = scanner.next();
				if (item.endsWith("!")) {
					Shop.persMask |= 1 << i;
					item = item.substring(0, item.length() - 1);
				}
				items.add(item);
			}
			
			Shop.shops = new Shop[shopCount + 1];
			for (int i = 0; i < shopCount; i++) {
				double x = scanner.nextDouble();
				double y = scanner.nextDouble(); 
				Shop shop = new Shop(x, y, itemCount);
				StringTokenizer t = new StringTokenizer(scanner.nextLine(), " :");
				while (t.hasMoreTokens()) {
					String item = t.nextToken();
					int itemIndex = items.indexOf(item);
					shop.price[itemIndex] = Integer.parseInt(t.nextToken());
					shop.itemsMask |= 1 << itemIndex;
				}
				Shop.shops[i] = shop;
			}
			Shop shop = new Shop(0, 0, itemCount);
			Shop.shops[shopCount] = shop;
			
			for (int i = 0; i < shopCount + 1; i++) {
				double[] route = new double[shopCount + 1];
				for (int j = 0; j < shopCount + 1; j++) {
					route[j] = gasPrice * Math.hypot(Shop.shops[i].x - Shop.shops[j].x, Shop.shops[i].y - Shop.shops[j].y); 
				}
				Shop.shops[i].route = route;
			}
			
			Shop.events = new PriorityQueue<Shop.Event>();
			shop.addEvent(false, 0, 0);
			while (!Shop.events.isEmpty()) {
				Event event = Shop.events.poll();
				event.process();
			}
			
			int allItems = (1 << itemCount) - 1;
			double result = shop.minAmount[allItems];
			System.out.format("Case #%d: %.7f\n", test + 1, result);
		}
	}
	
	public Shop(double x, double y, int itemCount) {
		this.x = x;
		this.y = y;
		price = new int[itemCount];
		minAmount = new double[1 << itemCount];
		minPersAmount = new double[1 << itemCount];
		Arrays.fill(minAmount, Double.MAX_VALUE);
		Arrays.fill(minPersAmount, Double.MAX_VALUE);
	}

	private void process(boolean pers, int mask, double amount) {
		if (!pers) {
			if (amount != minAmount[mask]) {
				return;
			}
			for (int i = 0; i < price.length; i++) {
				if (price[i] != 0 && !isSet(mask, i)) {
					addEvent(isSet(persMask, i), set(mask, i), amount + price[i]);
				}
			}
			for (int i = 0; i < shops.length; i++) {
				goTo(i, mask, amount);
			}
		} else {
			if (amount != minPersAmount[mask]) {
				return;
			}
			for (int i = 0; i < price.length; i++) {
				if (price[i] != 0 && !isSet(mask, i)) {
					addEvent(true, set(mask, i), amount + price[i]);
				}
			}
			goTo(shops.length - 1, mask, amount);
		}
	}

	private void goTo(int shop, int mask, double amount) {
		shops[shop].addEvent(false, mask, amount + route[shop]);
	}

	private int set(int mask, int i) {
		return mask | (1 << i);
	}

	private boolean isSet(int mask, int i) {
		return ((mask >> i) & 1) != 0;
	}

	private void addEvent(boolean pers, int mask, double amount) {
		if (!pers) {
			if (amount < minAmount[mask] - 1e-10) {
				events.add(new Event(this, pers, mask, amount));
				minAmount[mask] = amount;
			}
		} else {
			if (amount < minPersAmount[mask] - 1e-10) {
				events.add(new Event(this, pers, mask, amount));
				minPersAmount[mask] = amount;
			}
		}
	}
}
