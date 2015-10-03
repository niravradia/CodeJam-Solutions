/**
 * Created by Aditya on 7/9/2015.
 */
public class Maze {
    int h, w;
    boolean wall[][][];

    public Maze(int h, int w) {
        this.h = h;
        this.w = w;
        wall = new boolean[w][h][4];
        for (int i = 0; i < w; i++)
            for (int j = 0; j < h; j++)
                for (int k = 0; k < 4; k++)
                    wall[i][j][k] = true;
    }

    public void erase(int x, int y, Direction d) {
        try {
            if (d == Direction.N)
                wall[x][y][0] = false;
            if (d == Direction.E)
                wall[x][y][1] = false;
            if (d == Direction.S)
                wall[x][y][2] = false;
            if (d == Direction.W)
                wall[x][y][3] = false;
        } catch (Exception e) {
            System.out.println(x+"  "+y+"  "+d);
        }
    }

    public String rep(int x, int y) {
        int num = 0;
        if (!wall[x][y][0])
            num += 1;
        if (!wall[x][y][2])
            num += 2;
        if (!wall[x][y][3])
            num += 4;
        if (!wall[x][y][1])
            num += 8;
        if (num < 10)
            return num + "";
        else
            return (char) (num - 10 + (int) 'a') + "";
    }

    @Override
    public String toString() {
        String r = "";
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                r += rep(i, j);
            }
            r += '\n';
        }
        return r;
    }
}
