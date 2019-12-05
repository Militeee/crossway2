package dssc.crossway2;


public class GoBoard {

    private int side;
    private Cell [][] board;

    /**
     * GoBoard class
     * @param size the size of the side of the board, in cells.
     */
    public GoBoard(int size) {
        this.side = size;
    }

    /**
     * Side getter
     * @return the side of the board in cells
     */
    public int getSide() {

        return this.side;
    }

    /** Cell getter
     * @param x x coord
     * @param y coord
     * @return Gets the String status of a cell
     * @throws OutOfBoardException when the x or y are out of board boundaries
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
     * @throws OutOfBoardException when x or y out of board boundaries
     */
    public void setCellStatus(int x, int y, Colors newStatus) throws OutOfBoardException {


        if ((x>=this.side)||(y>=this.side) || (x < 0) || (y < 0) ) {

            throw new OutOfBoardException();
        }

        this.board[x][y].setStatus(newStatus);


    }
}
