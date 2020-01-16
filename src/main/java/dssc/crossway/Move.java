package dssc.crossway;

/**
 * This class represents a player move in a game.
 * A player move is characterized by the color of the player (and of the piece moved)
 * and by the coordinates of the position where the piece is placed.
 */
public class Move {

    private Coordinates coordinates;
    private StoneColor color;

    /**
     * Constructor of a Move, characterized by a coordinate c and a the color of the piece
     * @param c Coordinates of the move
     * @param color Colors object of the move
     */
    public Move(Coordinates c, StoneColor color) {
        coordinates = c;
        this.color = color;
    }


    public StoneColor getColor() {
        return color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
