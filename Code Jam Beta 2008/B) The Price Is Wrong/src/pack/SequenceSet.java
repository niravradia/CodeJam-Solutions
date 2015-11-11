package pack;

import java.util.Iterator;
import java.util.TreeSet;

public class SequenceSet {

	public TreeSet<Integer> tree = new TreeSet<Integer>();

	public int compareTo(SequenceSet s) {
		if (tree.size() != s.tree.size())
			throw new IllegalArgumentException();
		Iterator<Integer> i1 = tree.iterator(), i2 = s.tree.iterator();
		int a1, a2;
		while (i1.hasNext()) {
			a1 = i1.next().intValue();
			a2 = i2.next().intValue();
			if (a1 > a2)
				return 1;
			else if (a1 < a2)
				return -1;
		}
		return 0;
	}
}
