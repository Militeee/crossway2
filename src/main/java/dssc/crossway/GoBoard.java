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
     * board initialization
     */
    @Override
    public void initializeBoard() {
        for (int i=0; i<this.side; i++) {
            for (int j=0; j<this.side; j++) {
                this.board[i][j] = new Cell(Colors.EMPTY);
            }
        }
    }

    public String toString() {
        char[][] canvas = new char[side][side];
        for(int i=0;i<side;i++) {
            for(int j=0;j<side;j++) {
                Colors c = null;
                try {
                    c = getCellStatus(j,i);
                } catch (OutOfBoardException e) {
                    e.printStackTrace();
                }

                if(c== Colors.WHITE)
                    canvas[i][j]=WHITE_SIGN;

                if(c== Colors.BLACK)
                    canvas[i][j]=BLACK_SIGN;

                if(c== Colors.EMPTY)
                    canvas[i][j]=EMPTY_SIGN;
            }
        }

        return matrixToString(canvas,side,side);

    }

    private String matrixToString(char[][] canvas, int a, int b) {

        StringBuilder s = new StringBuilder();

        for(int i=0;i<a;i++) {
            for (int j = 0; j < b; j++) {
                s.append(canvas[i][j]);
            }
            s.append("\n");
        }

        return s.substring(0,s.length()-1); // I don't want last \n;
    }


    /**
     * Side getter
     * @return the side of the board in cells
     */
    public int getSide() {

        return side;
    }

    /** Cell getter
     * @return Gets the String status of a cell
     */
    public Colors getCellStatus(int x, int y) throws OutOfBoardException {

        if ((x>=this.side)||(y>=this.side)|| (x < 0) || (y < 0)) {
            throw new OutOfBoardException();
        }

        return this.board[x][y].getStatus();
    }

    /**
     * Cell setter
     * @param x x board coordinates
     * @param y y board coordinates
     * @param newStatus String status to update
     */
    public void setCellStatus(int x, int y, Colors newStatus) throws OutOfBoardException {

        if ((x>=this.side)||(y>=this.side) || (x < 0) || (y < 0) ) {
            throw new OutOfBoardException();
        }

        this.board[x][y].setStatus(newStatus);


    }
}
