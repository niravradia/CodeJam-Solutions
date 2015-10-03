import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Aditya on 7/10/2015.
 */
public class EggDrop {
    public static void main(String s[]) {
        File fin = new File("input.in");
        File fout = new File("output.out");

        Scanner scan = null;
        PrintWriter writer = null;
        try {
            scan = new Scanner(fin);
            writer = new PrintWriter(fout);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int T;
        T = scan.nextInt();

        int f, d, b, ds, bs;
        long fs;
        long fmax[][] = new long[100001][33];

        for (int x = 1; x < 33; x++)
            fmax[1][x] = 1;
        for (int x = 1; x < 100001; x++)
            fmax[x][1] = x;

        int ix, iy;
        boolean set = false;

        for (int id = 2; id < 100001; id++) {
            for (int ib = 2; ib < 33; ib++) {
                if (ib <= id) {
                    fmax[id][ib] = 1 + fmax[id - 1][ib] + fmax[id - 1][ib - 1];
                    if (fmax[id - 1][ib] == -1 || fmax[id - 1][ib - 1] == -1)
                        fmax[id][ib] = -1;
                    if (fmax[id][ib] < 0 || fmax[id][ib] >= Math.pow(2, 32))
                        fmax[id][ib] = -1;
                } else
                    fmax[id][ib] = fmax[id][id];
                //System.out.print(fmax[id][ib] + " ");
            }
            //System.out.println();
        }
        for (int cT = 1; cT <= T; cT++) {
            f = scan.nextInt();
            d = scan.nextInt();
            b = scan.nextInt();

            if (d > 32 && b > 32)
                fs = -1;
            else if (b > d)
                fs = fmax[d][d];
            else if (b == 1)
                fs = d;
            else
                fs = fmax[d][b];

            if (b > 32)
                b = 32;
            for (ds = 1; fmax[ds][b] < f; ds++) {
            }

            if (d >= f)
                bs = 1;
            else {
                if (d > 100000)
                    d = 100000;

                for (bs = 1; fmax[d][bs] < f && fmax[d][bs] != -1; bs++) {
                }
            }

            //    System.out.println(fs + "  " + ds + "  " + bs);
            writer.println("Case #" + cT + ": " + fs + " " + ds + " " + bs);
        }
        writer.close();
    }
}
