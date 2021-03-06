package dssc.crossway;

import dssc.crossway.exceptions.OutOfBoardException;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GoBoard {

    private int side;
    private StoneColor[][] board;

    public GoBoard(int side) {
        this.side = side;
        this.board = new StoneColor[side][side];
        initializeBoard();
    }

    private void initializeBoard() {
        Coordinates.getCoordinatesMesh(0, side, 0, side)
                .forEach(c -> this.board[c.getX()][c.getY()] = StoneColor.EMPTY);
    }

    public boolean isInside(int position) {
        return (position >= 0 && position < this.getSide());
    }

    public boolean isInside(Coordinates coord) {
        return isInside(coord.getX()) && isInside(coord.getY());
    }

    public int getSide() {
        return side;
    }


    public StoneColor getStoneColorStatus(Coordinates c) throws OutOfBoardException {
        if (!isInside(c))
            throw new OutOfBoardException();

        return this.board[c.getX()][c.getY()];
    }

    public void setStoneColorStatus(Coordinates coordinates, StoneColor newStatus) throws OutOfBoardException {
        if (!isInside(coordinates)) {
            throw new OutOfBoardException();
        }
        this.board[coordinates.getX()][coordinates.getY()] = newStatus;
    }


    /**
     * given the coordinates of a stone in the board, returns coordinates of nearby Stones of the same color .
     *
     * @param node selected coordinates.
     * @return a LinkedList
     */
    public ArrayList<Coordinates> adjacentFriendsCoordinates(Coordinates node) {

        return node.getAdjacents().stream()
                .filter(this::isInside)
                .filter(p -> {
                    try {
                        return getStoneColorStatus(p) == getStoneColorStatus(node);
                    } catch (OutOfBoardException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .collect(Collectors.toCollection(ArrayList::new));
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
                .filter(p -> p.getY() != c.getY() && p.getX() != c.getX())
                .collect(Collectors.toCollection(ArrayList::new));
    }

}