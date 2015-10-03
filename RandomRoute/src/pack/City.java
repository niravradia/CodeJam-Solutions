package pack;

public class City implements Comparable {
	String name;

	public City(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Object o) {
		return this.name.compareTo(((City) o).name);
	}

	@Override
	public boolean equals(Object o) {
		return this.name.equals(((City) o).name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return name;
	}
}
