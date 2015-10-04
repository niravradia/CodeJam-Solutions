import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Aditya on 7/9/2015.
 */
public class AlwaysTurnLeft {
    public static void main(String s[]) {
        File fin = new File("input.in");
        File fout = new File("output.out");
        Scanner sc = null;
        PrintWriter printer = null;
        try {
            sc = new Scanner(fin);
            printer = new PrintWriter(fout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int T;
        T = sc.nextInt();
        String fw, bw;
        for (int cT = 1; cT <= T; cT++) {
            fw = sc.next();
            bw = sc.next();

            int cx = 0, cy = 0, min_x, max_x, min_y, max_y;
            min_x = min_y = max_x = max_y = 0;
            Direction d = Direction.S;
            for (int i = 1; i < fw.length() - 1; i++) {
                System.out.print(fw.charAt(i) + "  " + d + "  ");
                if (fw.charAt(i) == 'W') {
                    cy = d.stepY(cy);
                    cx = d.stepX(cx);
                    System.out.println(cx + "  " + cy);
                    if (cx > max_x)
                        max_x = cx;
                    if (cx < min_x)
                        min_x = cx;
                    if (cy > max_y)
                        max_y = cy;
                    if (cy < min_y)
                        min_y = cy;
                } else if (fw.charAt(i) == 'L') {
                    d = d.left();
                } else {
                    d = d.left();
                    d = d.left();
                    d = d.left();
                }
            }

            d=d.left().left();
            for (int i = 1; i < bw.length() - 1; i++) {
                System.out.print(bw.charAt(i) + "  " + d + "  ");
                if (bw.charAt(i) == 'W') {
                    cy = d.stepY(cy);
                    cx = d.stepX(cx);
                    System.out.println(cx + "  " + cy);
                    if (cx > max_x)
                        max_x = cx;
                    if (cx < min_x)
                        min_x = cx;
                    if (cy > max_y)
                        max_y = cy;
                    if (cy < min_y)
                        min_y = cy;
                } else if (bw.charAt(i) == 'L') {
                    d = d.left();
                } else {
                    d = d.left();
                    d = d.left();
                    d = d.left();
                }
            }

            int sizeX, sizeY;
            sizeX = max_x - min_x + 1;
            sizeY = max_y - min_y + 1;

            Maze m = new Maze(sizeY, sizeX);

            cy = 0;
            cx = -min_x;
            d = Direction.S;

            for (int i = 1; i < fw.length() - 1; i++) {
                if (fw.charAt(i) == 'W') {

                    m.erase(cx, cy, d);
                    cy = d.stepY(cy);
                    cx = d.stepX(cx);
                    m.erase(cx, cy, (d.left()).left());
                } else if (fw.charAt(i) == 'L') {
                    d = d.left();
                } else {
                    d = d.left();
                    d = d.left();
                    d = d.left();
                }
                System.out.println(cx + "  " + cy + "  " + d + "  " + fw.charAt(i));
            }
            m.erase(cx, cy, d);


            System.out.println("back now");

            d = d.left();
            d = d.left();

            for (int i = 1; i < bw.length() - 1; i++) {
                if (bw.charAt(i) == 'W') {
                    m.erase(cx, cy, d);
                    cy = d.stepY(cy);
                    cx = d.stepX(cx);
                    m.erase(cx, cy, d.left().left());
                } else if (bw.charAt(i) == 'L') {
                    d = d.left();
                } else {
                    d = d.left();
                    d = d.left();
                    d = d.left();
                }
//                System.out.println(cx + "  " + cy + "  " + d + "  " + fw.charAt(i));

            }
            m.erase(cx, cy, d);


            for (int j = 0; j < m.h; j++) {
                for (int i = 0; i < m.w; i++) {
                    for (int x = 0; x < 4; x++) {
                        System.out.print(m.wall[i][j][x]);
                    }
                    System.out.print("   ");
                }
                System.out.println();
            }
            System.out.println(sizeX + "  " + sizeY);

            System.out.println(m.toString());

            printer.println("Case #" + cT + ":");
            printer.print(m.toString());
        }
        printer.close();

    }
}
