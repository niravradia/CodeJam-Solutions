/**
 * Created by Aditya on 7/9/2015.
 */
public enum Direction {
    N, W, S, E;

    public Direction left() {
        if (this == Direction.N)
            return W;
        else if (this == Direction.W)
            return S;
        else if (this == Direction.S)
            return E;
        else
            return N;
    }

    public int stepX(int x){
        if (this == Direction.W)
            return x-1;
        else if(this == Direction.E)
            return x+1;
        else
            return x;
    }
    public int stepY(int y){
        if (this == Direction.N)
            return y-1;
        else if(this == Direction.S)
            return y+1;
        else
            return y;
    }
}
