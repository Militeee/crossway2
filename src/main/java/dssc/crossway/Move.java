package dssc.crossway;

/**
 * This class represents a player move in a game.
 * A player move is characterized by the color of the player (and of the piece moved)
 * and by the coordinates of the position where the piece is placed.
 */
public class Move {

    private Coordinates coordinates;
    private Colors color;

    /**
     * Constructor of a Move, characterized by a coordinate c and a the color of the piece
     * @param c
     * @param color
     */
    public Move(Coordinates c, Colors color) {
        coordinates = c;
        this.color = color;
    }

    /**
     * Constructor of a Move, characterized by the x and y coordinates c and a the color of the piece
     * @param x
     * @param y
     * @param color
     */
    public Move(int x, int y, Colors color) {
        coordinates = new Coordinates(x,y);
        this.color = color;
    }

    public int getX() {
        return coordinates.getX();
    }

    public int getY() {
        return coordinates.getY();
    }

    public Colors getColor() {
        return color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
