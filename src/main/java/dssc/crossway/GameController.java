package dssc.crossway;

/**
 *  Class that encodes the methods to carry on the game
 *
 *
 */

public class GameController {


    private GoBoard board;
    private  Validator rules;
    private int turn = 0;


    public GameController(GoBoard board, Validator rules) {
        this.board = board;
        this.rules = rules;
    }


    /**
     * Validate a move and then perform a it
     *
     * @param m a Move object
     * @throws OutOfBoardException if the move is out of board
     * @throws IllegalMoveException if the move is not allowed by the rules
     */

    public void placeStone(Move m) throws OutOfBoardException, IllegalMoveException {
        if(this.validateMove(m)) {
            board.setCellStatus(m.getX(), m.getY(), m.getColor());

        }
        else {
            throw new IllegalMoveException();
        }
    }

    /**
     *  Return the color of a cell
     * @param x coordinate
     * @param y coordinate
     * @return the status of the cell in that position
     * @throws OutOfBoardException if the coordinated are out of the board size
     */
    public Colors getCellStatus(int x, int y) throws OutOfBoardException {
        return this.board.getCellStatus(x,y);
    }


    /**
     * Control if a move respect the set of rules encoded by its validator
     * @param m the Move object to be validated
     * @return boolean that encode the validity
     * @throws OutOfBoardException if the coordinated are out of the board size
     */
    public boolean validateMove(Move m) throws OutOfBoardException {
        return rules.validateMove(this.board, m, this.turn);
    }

    public int getSide()  {
        return this.board.getSide();
    }

    /**
     *
     * @return the Color of the winner in that turn, if none returns COLORS.EMPTY
     */
    public Colors winner() {

        return  this.rules.winner(this.board);

    }

    /**
     * Same as PlaceStone but updates the number of turns
     * @param m
     * @throws IllegalMoveException if the move is not allowed by the rules
     * @throws OutOfBoardException if the coordinated are out of the board size
     */
    public void performGameMove(Coordinates m) throws IllegalMoveException, OutOfBoardException {
        placeStone(new Move(m.getX(), m.getY(), currentTurnColor()));
        this.turn++;
    }

    /**
     *
     * @return the color that is currently playing
     */
    public Colors currentTurnColor() {
        return (this.turn % 2 == 0) ? Colors.WHITE : Colors.BLACK;
    }

    /**
     *
     * @return the board in a String printable format
     */
    public String stringBoard() {
        return this.board.toString();
    }

}

