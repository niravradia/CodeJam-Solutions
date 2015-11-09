package pack;

public class Triangle {
	Vertex v1, v2, v3;
	int e1, e2, e3;
	String s;

	public Triangle(Vertex v1, Vertex v2, Vertex v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;

		this.e1 = v1.distanceSquared(v2);
		this.e2 = v2.distanceSquared(v3);
		this.e3 = v3.distanceSquared(v1);

		s = "";
		int p = (v2.x - v1.x) * (v3.y - v2.y) - (v2.y - v1.y) * (v3.x - v2.x);
		if (p == 0)
			s += "not a ";
		else {
			if (e1 == e2 || e2 == e3 || e3 == e1)
				s += "isosceles ";
			else
				s += "scalene ";

			if (e1 < e2)
				e1 = (e1 + e2) - (e2 = e1);
			if (e1 < e3)
				e1 = (e1 + e3) - (e3 = e1);

			if (e1 > e2 + e3)
				s += "obtuse ";
			else if (e1 == e2 + e3)
				s += "right ";
			else
				s += "acute ";
		}
		s += "triangle";
	}

	@Override
	public String toString() {
		return s;
	}
}
