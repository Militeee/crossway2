package dssc.crossway;


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
                StoneColor c = null;
                try {
                    c = getCellStatus(j,i);
                } catch (OutOfBoardException e) {
                    e.printStackTrace();
                }

                if(c== StoneColor.WHITE)
                    canvas[i][j]=WHITE_SIGN;

                if(c== StoneColor.BLACK)
                    canvas[i][j]=BLACK_SIGN;

                if(c== StoneColor.EMPTY)
                    canvas[i][j]=EMPTY_SIGN;
            }
        }

        return matrixToString(canvas,side,side);

    }

    /**
     * Helper function for printing the board.
     * @param canvas a 2D array of chars
     * @param x first dimension of the array
     * @param y second dimension of the array
     * @return
     */
    private String matrixToString(char[][] canvas, int x, int y) {

        StringBuilder s = new StringBuilder();

        for(int i=0;i<x;i++) {
            for (int j = 0; j < y; j++) {
                s.append(canvas[i][j]);
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

    public StoneColor getCellStatus(int x, int y) throws OutOfBoardException {
        return getCellStatus(new Coordinates(x,y));
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
     * Changes the color status of a Cell.
     * @param x integer x value of the coordinates
     * @param y integer y value of the coordinates
     * @param newStatus new Colors object
     * @throws OutOfBoardException if coordinates are out of the board
     */
    public void setCellStatus(int x, int y, StoneColor newStatus) throws OutOfBoardException {
        setCellStatus(new Coordinates(x,y), newStatus);
    }
}
