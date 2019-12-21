package dssc.crossway;

import java.util.ArrayList;

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
     * Helper function to retrieve diagonal position whose legality is to be verified.
     * @param c the coordinates of a cell whose diagonals are queried.
     * @return an ArrayList of Coordinates object, with the 4 diagonally adiacents cells, if they exists.
     */
    public ArrayList<Coordinates> getDiagonalPositions(Coordinates c) {
        ArrayList<Coordinates> diagonalPositions = new ArrayList<>();
        Coordinates[] diagonals = {
                c.getNorthEast(),
                c.getNorthWest(),
                c.getSouthWest(),
                c.getSouthEast()
        };

        for(Coordinates coord: diagonals)
        {
            if(isInside(coord))
                diagonalPositions.add(coord);
        }

        return diagonalPositions;
    }
}
