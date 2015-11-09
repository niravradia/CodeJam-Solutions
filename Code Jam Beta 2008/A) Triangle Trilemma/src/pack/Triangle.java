package pack;

public class Triangle {
	Vertex v1, v2, v3;
	Double e1, e2, e3, s, area, theta1, theta2, theta3;

	public Triangle(Vertex v1, Vertex v2, Vertex v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;

		this.e1 = v1.distance(v2);
		this.e2 = v2.distance(v3);
		this.e3 = v3.distance(v1);

		this.s = (e1 + e2 + e3) / 2;

		this.area = Math.sqrt(s * (s - e1) * (s - e2) * (s - e3));

		this.theta1 = Math.acos((e2 * e2 + e3 * e3 - e1 * e1) / 2 * e2 * e3);
		this.theta2 = Math.acos((e3 * e3 + e1 * e1 - e2 * e2) / 2 * e3 * e1);
		this.theta3 = Math.acos((e1 * e1 + e2 * e2 - e3 * e3) / 2 * e1 * e2);
	}
}
