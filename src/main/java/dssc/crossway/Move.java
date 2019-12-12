package dssc.crossway;

public class Move {

    private int x;
    private int y;
    private Colors color;

    public Move(int x, int y, Colors color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Colors getColor() {
        return color;
    }
}
