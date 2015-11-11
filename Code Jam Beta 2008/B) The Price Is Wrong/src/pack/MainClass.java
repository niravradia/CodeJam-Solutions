package pack;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) throws Exception {

		Scanner scanLine = new Scanner(new File("input.in"));
		PrintWriter out = new PrintWriter(new File("output.out"));

		ArrayList<String> itemList = new ArrayList<String>();
		ArrayList<Integer> priceList = new ArrayList<Integer>();
		ArrayList<String> solutionSet = new ArrayList<String>();

		Scanner scan = new Scanner(scanLine.nextLine());
		int T = scan.nextInt(), itemCount;
		scan.close();

		for (int cT = 1; cT <= T; cT++) {
			itemList.clear();
			priceList.clear();
			solutionSet.clear();

			scan = new Scanner(scanLine.nextLine());
			while (scan.hasNext())
				itemList.add(scan.next());
			scan.close();
			scan = new Scanner(scanLine.nextLine());
			while (scan.hasNext())
				priceList.add(scan.nextInt());
			scan.close();

			itemCount = itemList.size();

			int sortedIndex[] = new int[itemCount];
			SequenceSet subsequenceSet[] = new SequenceSet[itemCount];

			Arrays.fill(sortedIndex, 0);

			for (int i = 0; i < itemCount; i++)
				for (int j = 0; j < itemCount; j++)
					if (itemList.get(i).compareTo(itemList.get(j)) < 0)
						sortedIndex[j]++;

			for (int i = 0; i < itemCount; i++) {
				subsequenceSet[i] = new SequenceSet();
				subsequenceSet[i].tree.add(sortedIndex[i]);
			}

			int table[] = new int[itemCount];
			int previousIndex[] = new int[itemCount];

			Arrays.fill(table, 1);
			Arrays.fill(previousIndex, -1);
			for (int i = 0; i < itemCount; i++)
				for (int j = 0; j < i; j++)
					if (priceList.get(j) < priceList.get(i)) {
						if (table[i] <= table[j]) {
							table[i] = 1 + table[j];
							previousIndex[i] = j;
							subsequenceSet[i].tree.clear();
							subsequenceSet[i].tree
									.addAll(subsequenceSet[j].tree);
							subsequenceSet[i].tree.add(sortedIndex[i]);
						} else if (table[i] == 1 + table[j]
								&& subsequenceSet[previousIndex[i]]
										.compareTo(subsequenceSet[j]) < 0) {
							previousIndex[i] = j;
							subsequenceSet[i].tree.clear();
							subsequenceSet[i].tree
									.addAll(subsequenceSet[j].tree);
							subsequenceSet[i].tree.add(sortedIndex[i]);
						}
					}

			boolean stable[] = new boolean[itemCount];

			int highestEnd = 0;
			for (int i = 0; i < itemCount; i++)
				if (table[i] > table[highestEnd])
					highestEnd = i;
				else if (table[i] == table[highestEnd]
						&& subsequenceSet[highestEnd]
								.compareTo(subsequenceSet[i]) < 0)
					highestEnd = i;
			while (highestEnd != -1) {
				stable[highestEnd] = true;
				highestEnd = previousIndex[highestEnd];
			}

			for (int i = 0; i < itemCount; i++)
				if (!stable[i])
					solutionSet.add(itemList.get(i));

			Collections.sort(solutionSet);
			out.print("Case #" + cT + ":");
			for (int i = 0; i < solutionSet.size(); i++)
				out.print(" " + solutionSet.get(i));
			out.println();
		}

		out.close();
		scanLine.close();
	}
}
