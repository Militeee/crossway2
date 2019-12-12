package dssc.crossway;

public class Coordinates {

    int x_cord;
    int y_cord;

    public int getX() {
        return x_cord;
    }

    public void setX(int x_cord) {
        this.x_cord = x_cord;
    }

    public int getY() {
        return y_cord;
    }

    public void setY(int y_cord) {
        this.y_cord = y_cord;
    }

    public Coordinates(int x_cord, int y_cord) {
        this.x_cord = x_cord;
        this.y_cord = y_cord;
    }

    public Coordinates getNorthWest()
    {
        return new Coordinates(x_cord-1, y_cord-1);
    }

    public Coordinates getNorthEast()
    {
        return new Coordinates(x_cord+1, y_cord-1);
    }

    public Coordinates getSouthEast()
    {
        return new Coordinates(x_cord+1, y_cord+1);
    }

    public Coordinates getSouthWest()
    {
        return new Coordinates(x_cord-1, y_cord+1);
    }
}
