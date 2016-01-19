package pack;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) throws Exception {

		Scanner scan = new Scanner(new File("input.in"));
		PrintWriter out = new PrintWriter(new File("output.out"));

		int T = scan.nextInt();
		int n,k,edges[][];
		for (int cT = 1; cT <= T; cT++) {
			n = scan.nextInt();
			k = scan.nextInt();
			
			edges = new int[k][2];
			
			for(int i=0;i<k;i++){
				edges[i][0] = scan.nextInt();
				edges[i][1] = scan.nextInt();
			}
		}
	}

}
