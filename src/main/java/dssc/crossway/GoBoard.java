package dssc.crossway;


import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Go board class. A class to manage a Go Board, that is a square check board.
 *
 */
public class GoBoard extends GenericBoard {

    private final static char EMPTY_SIGN = '.';
    private final static char WHITE_SIGN = 'W';
    private final static char BLACK_SIGN = 'B';

    /**
     * GoBoard constructor
     * @param side the side of the board, in cells
     */
    public GoBoard(int side) {
        super(side);
        initializeBoard();
    }

    /**
     * board initialization: fills the board with empty Cells objects.
     */
    @Override
    public void initializeBoard() {
        for (int i=0; i<this.side; i++) {
            for (int j=0; j<this.side; j++) {
                this.board[i][j] = new Cell(StoneColor.EMPTY);
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
                StoneColor c = StoneColor.EMPTY;
                try {
                    c = getCellStatus(new Coordinates(j,i));
                } catch (OutOfBoardException e) {
                    e.printStackTrace();
                }

                canvas[i][j] = color2consoleRepresentation(c);
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

        for(int i=0;i<x;i++) {
            for (int j = 0; j < y; j++) {
                s.append(charMatrix[i][j]);
            }
            s.append("\n");
        }

        return s.substring(0,s.length()-1);
    }


    /**
     * Side getter
     * @return the side of the board in cells
     */
    public int getSide() {
        return side;
    }



    public StoneColor getCellStatus(Coordinates c) throws OutOfBoardException {
        if(!isInside(c))
            throw new OutOfBoardException();

        return this.board[c.getX()][c.getY()].getStatus();
    }

    /**
     * Changes the color status of a Cell
     * @param coordinates cell Coordinates
     * @param newStatus new Colors object
     * @throws OutOfBoardException if coordinates are out of the board
     */
    public void setCellStatus(Coordinates coordinates, StoneColor newStatus) throws OutOfBoardException {
        if (!isInside(coordinates)) {
            throw new OutOfBoardException();
        }
        this.board[coordinates.getX()][coordinates.getY()].setStatus(newStatus);
    }



    /**
     * Helper function to retrieve cell coordinates of nearby cells where there are stones of the provided color.
     * @param node Coordinate objects of a cell.
     * @param board a game board
     * @param c the provided color
     * @return a LinkedList<Coordinates> </Coordinates>
     */
    public LinkedList<Coordinates> adjacentFriendsCoordinates(Coordinates node, GoBoard board, StoneColor c) {

        return node.getAdjacents().stream()
                .filter(board::isInside)
                .filter(p -> {
                    try {
                        return board.getCellStatus(p) == c;
                    } catch (OutOfBoardException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
