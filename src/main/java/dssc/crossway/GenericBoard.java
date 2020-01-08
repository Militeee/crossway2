package dssc.crossway;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
     * Checks if given Coordinates are inside the board,
     * i.e if they are between 0 included and side excluded.
     * @param coord the given Coordinates
     * @return true if they are, false otherwise.
     */
    public boolean isInside(Coordinates coord)
    {
        return coord.getX()<side && coord.getY()<side && coord.getY()>=0 && coord.getX()>=0;
    }


    /**
     * Returns an ArrayList filled with all the legal diagonal-adjacent positions
     * relatively to a given coordinate.
     *
     * @param c the coordinates of a cell whose diagonals are queried.
     * @return an ArrayList of Coordinates object, with the 4 diagonally adiacents cells, if they exists.
     */
    public ArrayList<Coordinates> getDiagonalPositions(Coordinates c) {

        return c.getAdjacents().stream()
                .filter(this::isInside)
                .filter(p -> p.getY()!=c.getY() && p.getX()!=c.getX())
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
