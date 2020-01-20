package dssc.crossway;

import dssc.crossway.exceptions.OutOfBoardException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Go board class. A class to manage a Go Board, that is a square check board.
 *
 */
public class GoBoard {

    private int side;
    private StoneColor[][] board;

    /**
     * GoBoard Constructor. Initializes a matrix of StoneColor items
     * @param side the side of the board in units
     */
    public GoBoard(int side) {

        this.side = side;
        this.board = new StoneColor[side][side];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i=0; i<this.side; i++) {
            for (int j=0; j<this.side; j++) {
                this.board[i][j] = StoneColor.EMPTY;
            }
        }
    }

    public Boolean isInside(int position) {
        return (position >= 0 && position < this.getSide());
    }

    /**
     * Checks if given Coordinates are inside the board,
     * i.e if they are between 0 included and side excluded.
     * @param coord the given Coordinates
     * @return true if they are, false otherwise.
     */
    public boolean isInside(Coordinates coord)
    {
        return isInside(coord.getX()) && isInside(coord.getY());
    }


    public int getSide() {
        return side;
    }


    public StoneColor getStoneColorStatus(Coordinates c) throws OutOfBoardException {
        if(!isInside(c))
            throw new OutOfBoardException();

        return this.board[c.getX()][c.getY()];
    }

    /**
     * Changes the color status of a StoneColor
     * @param coordinates StoneColor Coordinates
     * @param newStatus new Colors object
     * @throws OutOfBoardException if coordinates are out of the board
     */
    public void setStoneColorStatus(Coordinates coordinates, StoneColor newStatus) throws OutOfBoardException {
        if (!isInside(coordinates)) {
            throw new OutOfBoardException();
        }
        this.board[coordinates.getX()][coordinates.getY()] = newStatus;
    }



    /**
     * Helper function to retrieve StoneColor coordinates of nearby StoneColors where there are stones of the provided color.
     * @param node Coordinate objects of a StoneColor.
     * @param color the provided color
     * @return a LinkedList
     */
    LinkedList<Coordinates> adjacentFriendsCoordinates(Coordinates node, StoneColor color) {

        return node.getAdjacents().stream()
                .filter(this::isInside)
                .filter(p -> {
                    try {
                        return getStoneColorStatus(p) == color;
                    } catch (OutOfBoardException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Returns an ArrayList filled with all the legal diagonal-adjacent positions
     * relatively to a given coordinate.
     *
     * @param c the coordinates of a StoneColor whose diagonals are queried.
     * @return an ArrayList of Coordinates object, with the 4 diagonally adiacents StoneColors, if they exists.
     */
    public ArrayList<Coordinates> getDiagonalPositions(Coordinates c) {

        return c.getAdjacents().stream()
                .filter(this::isInside)
                .filter(p -> p.getY()!=c.getY() && p.getX()!=c.getX())
                .collect(Collectors.toCollection(ArrayList::new));
    }

}