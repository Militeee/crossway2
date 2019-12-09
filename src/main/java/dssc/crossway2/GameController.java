package dssc.crossway2;

public class GameController {

    /**
     *  Class that encodes the methods to carry on the game
     *
     *
     */

    private GoBoard board;
    private  Validator rules;



    public GameController(GoBoard board,Validator rules) {
        this.board = board;
        this.rules = rules;
    }

    // test method, remove
    public boolean exists() {
        return true;
    }

    /**
     * starts a new game, instantiating a board for two players.
     */
    public void startGame() {

    }

    /**
     * Places a stone.
     * @param x  x coordinate [0,size-1]
     * @param y  y coordinate [0,size-1]
     * @param color {EMPTY, BLACK, WHITE}.
     * @see Colors
     *
     * @throws OutOfBoardException
     */
    public void placeStone(int x, int y, Colors color) throws OutOfBoardException {
        board.setCellStatus(x,y,color);
    }

    public Colors getCellStatus(int x, int y) throws OutOfBoardException {
        return this.board.getCellStatus(x,y);
    }

    public void placeStone(Move m) throws OutOfBoardException, IllegalMoveException {
        if(this.validateMove(m))
            board.setCellStatus(m.getX(),m.getY(),m.getColor());
        else {
            throw new IllegalMoveException();
        }
    }


    public boolean validateMove(Move m) throws OutOfBoardException {
        return rules.validateMove(this.board, m);
    }

    public int getSide()  {
        return this.board.getSide();
    }

    public Colors winner() throws OutOfBoardException {
        return this.rules.winner(this.board);
    }
}
