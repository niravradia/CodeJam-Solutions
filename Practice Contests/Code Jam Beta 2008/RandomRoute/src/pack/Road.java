package pack;

import java.math.BigDecimal;

public class Road implements Comparable<Road> {
	City src, dest;
	int cost;
	BigDecimal probability;

	public Road(City src, City dest, int cost) {
		this.src = src;
		this.dest = dest;
		this.cost = cost;
		this.probability = new BigDecimal(0);
	}

	@Override
	public int hashCode() {
		return cost + src.hashCode() + dest.hashCode();
	}

	@Override
	public int compareTo(Road r) {
		return (src.name + dest.name + cost).compareTo(r.src.name + r.dest.name
				+ cost);
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Road && compareTo((Road) o) == 0;
	}
}
