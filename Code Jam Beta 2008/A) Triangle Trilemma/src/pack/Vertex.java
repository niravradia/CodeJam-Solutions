package pack;

public class Vertex {
	public int x, y;

	public Vertex(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public double distance(Vertex v) {
		return (this.x - v.x) * (this.x - v.x) + (this.y - v.y)
				* (this.y - v.y);
	}
}
