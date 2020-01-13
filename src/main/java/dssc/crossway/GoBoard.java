package dssc.crossway;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Go board class. A class to manage a Go Board, that is a square check board.
 *
 */
public class GoBoard {

    private final static char EMPTY_SIGN = '.';
    private final static char WHITE_SIGN =  '\u25CF';//'W';
    private final static char BLACK_SIGN = '\u25CB';

    int side;
    StoneColor[][] board;

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

    /**
     * pretty prints a Board.
     * @return a String representation of the board status.
     */
    public String toString() {
        char[][] canvas = new char[side][side];
        for(int i=0;i<side;i++) {
            for(int j=0;j<side;j++) {
                StoneColor StoneColorColor = StoneColor.EMPTY;
                try {
                    StoneColorColor = getStoneColorStatus(new Coordinates(j,i));
                } catch (OutOfBoardException e) {
                    e.printStackTrace();
                }

                canvas[i][j] = color2consoleRepresentation(StoneColorColor);
            }
        }

        return matrixToString(canvas,side,side);

    }

    /**
     * Method that maps a Stone color to its representing char on the console
     * @param c the color of the stone
     * @return char representing the color
     */
    private char color2consoleRepresentation(StoneColor c) {
        
        char sign;
        
        switch(c) 
        {
            case WHITE: sign = WHITE_SIGN; break;
            case BLACK: sign = BLACK_SIGN; break;
            case EMPTY: sign = EMPTY_SIGN; break;
            default:
                throw new IllegalStateException("Unexpected value: " + c);
        }
        
        return sign;
    }

    /**
     * Method that returns the String representation of a matrix of characters
     * @param charMatrix a 2D array of chars
     * @param x first dimension of the array
     * @param y second dimension of the array
     * @return String representation of a matrix of characters
     */
    private String matrixToString(char[][] charMatrix, int x, int y) {

        StringBuilder s = new StringBuilder();

        s.append ("  ");
        for (int i=0; i<y; i++) {
            s.append(i%10);
            s.append( " ");

        }
        s.append("\n");

        for(int i=0;i<x;i++) {
            s.append(i%10);
            s.append(" ");
            for (int j = 0; j < y; j++) {

                s.append(charMatrix[i][j]);
                s.append(" ");
            }
            s.append("\n");
        }

        return s.substring(0,s.length()-1);
    }

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
     * Side getter
     * @return the side of the board in StoneColors
     */
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
     * @param board a game board
     * @param color the provided color
     * @return a LinkedList
     */
    LinkedList<Coordinates> adjacentFriendsCoordinates(Coordinates node, GoBoard board, StoneColor color) {

        return node.getAdjacents().stream()
                .filter(board::isInside)
                .filter(p -> {
                    try {
                        return board.getStoneColorStatus(p) == color;
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
