package dssc.crossway;

/**
 * Abstract class for a game board made of Cells.
 */
public abstract class GenericBoard {

    int side;
     Cell[][] board;

    /**
     * GoBoard Constructor. Initializes a matrix of Cell items
     * @param side the side of the board in units
     */
      GenericBoard (int side) {

        this.side = side;
        this.board = new Cell[side][side];

    }

    /**
     * Abstract method, needed to initialize the board.
     */
    abstract void initializeBoard();

    /**
     * Checks if given Coordinates are inside the board.
     * @param coord the given Coordinates
     * @return true if they are, false otherwise.
     */
    public boolean isInside(Coordinates coord)
    {
        return coord.getX()<side && coord.getY()<side && coord.getY()>=0 && coord.getX()>=0;
    }
}
