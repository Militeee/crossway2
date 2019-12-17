package dssc.crossway;

/**
 *  Data structure used to store information about the position of an entity
 *  in the board
 *
 */

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

    /**
     * Considering a 3x3 square centered into the current coordinate
     * @return the coordinates of the upper-left corner
     */
    public Coordinates getNorthWest()
    {
        return new Coordinates(x_cord-1, y_cord-1);
    }

    /**
     * Considering a 3x3 square centered into the current coordinate
     * @return the coordinates of the upper-right corner
     */
    public Coordinates getNorthEast()
    {
        return new Coordinates(x_cord+1, y_cord-1);
    }
    /**
     * Considering a 3x3 square centered into the current coordinate
     * @return the coordinates of the lower-right corner
     */
    public Coordinates getSouthEast()
    {
        return new Coordinates(x_cord+1, y_cord+1);
    }
    /**
     * Considering a 3x3 square centered into the current coordinate
     * @return the coordinates of the lower-left corner
     */
    public Coordinates getSouthWest()
    {
        return new Coordinates(x_cord-1, y_cord+1);
    }
}
