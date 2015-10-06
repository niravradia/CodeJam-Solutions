package pack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class RandomRoute implements Runnable {
	public static void main(String[] args) {
		new Thread(new RandomRoute()).start();
	}

	@Override
	public void run() {
		Locale.setDefault(Locale.US);
		try {
			br = new BufferedReader(new FileReader("input.in"));
			out = new PrintWriter("output.out");
			solve();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.close();
	}

	BufferedReader br;
	StringTokenizer st;
	PrintWriter out;
	boolean eof;

	String nextToken() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				eof = true;
			}
		}
		return st.nextToken();
	}

	int nextInt() {
		return Integer.parseInt(nextToken());
	}

	long nextLong() {
		return Long.parseLong(nextToken());
	}

	double nextDouble() {
		return Double.parseDouble(nextToken());
	}

	BigInteger nextBigInteger() {
		return new BigInteger(nextToken());
	}

	String FILENAME = "C-large";

	ArrayList<Integer>[] tab;
	int tt;
	int[] t, t2;
	boolean[] qq;

	void dfs(int i) {
		qq[i] = true;
		for (int j : tab[i]) {
			if (!qq[j]) {
				dfs(j);
			}
		}
		t2[i] = tt;
		t[tt++] = i;
	}

	private void solve() throws IOException {
		int ttn = nextInt();
		for (int tn = 1; tn <= ttn; tn++) {
			out.print("Case #" + tn + ": ");
			int m = nextInt();
			HashMap<String, Integer> hm = new HashMap<String, Integer>();
			int[] a = new int[m];
			int[] b = new int[m];
			int[] c = new int[m];
			int n = 0;
			String sstart = nextToken();
			hm.put(sstart, n++);
			for (int i = 0; i < m; i++) {
				String sa = nextToken();
				if (!hm.containsKey(sa)) {
					hm.put(sa, n++);
				}
				String sb = nextToken();
				if (!hm.containsKey(sb)) {
					hm.put(sb, n++);
				}
				a[i] = hm.get(sa);
				b[i] = hm.get(sb);
				c[i] = nextInt();
			}
			int[] d = new int[n];
			boolean[] q = new boolean[n];
			Arrays.fill(d, Integer.MAX_VALUE / 2);
			d[0] = 0;
			for (int i = 0; i < n; i++) {
				int imin = 0;
				while (q[imin]) {
					imin++;
				}
				for (int j = imin; j < n; j++) {
					if (!q[j] && d[j] < d[imin]) {
						imin = j;
					}
				}
				q[imin] = true;
				for (int j = 0; j < m; j++) {
					if (a[j] == imin && d[b[j]] > d[imin] + c[j]) {
						d[b[j]] = d[imin] + c[j];
					}
				}
			}
			tab = new ArrayList[n];
			ArrayList<Integer>[] tab2 = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				tab[i] = new ArrayList<Integer>();
				tab2[i] = new ArrayList<Integer>();
				for (int j = 0; j < m; j++) {
					if (a[j] == i && d[b[j]] - d[a[j]] == c[j]) {
						tab[i].add(b[j]);
						tab2[i].add(j);
					}
				}
			}
			t = new int[n];
			t2 = new int[n];
			Arrays.fill(t2, -1);
			qq = new boolean[n];
			tt = 0;
			dfs(0);
			int ff = tt;
			for (int i = 0; i < n; i++) {
				if (t2[i] != -1) {
					t2[i] = ff - 1 - t2[i];
					t[t2[i]] = i;
				}
			}
			double[] ans = new double[m];
			int[] c1 = new int[n];
			c1[0] = 1;
			for (int i = 0; i < ff; i++) {
				for (int j : tab[t[i]]) {
					c1[j] += c1[t[i]];
				}
			}
			// out.println(ff);
			for (int i = 1; i < ff; i++) {
				int[] c2 = new int[n];
				c2[t[i]] = 1;
				for (int k = i; k >= 0; k--) {
					for (int j = 0; j < m; j++) {
						if (b[j] == t[k] && t2[a[j]] < k
								&& d[b[j]] - d[a[j]] == c[j]) {
							c2[a[j]] += c2[t[k]];
						}
					}
				}
				for (int j = 0; j < m; j++) {
					if (t2[a[j]] < i && d[b[j]] - d[a[j]] == c[j]) {

						// if (c1[t[i]] != 0) {
						ans[j] += 1.0 * c1[a[j]] * c2[b[j]]
								/ (c1[t[i]] * (ff - 1));
						// }
					}
				}
			}
			for (int j = 0; j < m; j++) {
				if (ff == 1) {
					out.print("0 ");
				} else {
					out.print(ans[j] + " ");
				}
			}
			out.println();
		}
	}
}
