package pack;

import java.util.Arrays;

public class Tile {
	double x, y;
	Tile neighbours[];

	public Tile(double x, double y) {
		this.x = x;
		this.y = y;
		neighbours = new Tile[6];
		Arrays.fill(neighbours, null);
	}

	public void addNeighbour(Tile t) {
		int i = 0;
		while (neighbours[i++] == null) {
		}
		neighbours[i] = t;
	}

	public double cartesianDistanceSquared(Tile t) {
		return (this.x - t.x) * (this.x - t.x) + (this.y - t.y)
				* (this.y - t.y);
	}

	public int steps(Tile t) {
		if (t == this)
			return 0;
		int n = 0;
		double minD = neighbours[0].cartesianDistanceSquared(t);
		for (int i = 1; i < neighbours.length; i++)
			if (neighbours[i] == null)
				break;
			else if (neighbours[i].cartesianDistanceSquared(t) > minD) {
				n = i;
				minD = neighbours[i].cartesianDistanceSquared(t);
			}
		return 1 + neighbours[n].steps(t);
	}
}
