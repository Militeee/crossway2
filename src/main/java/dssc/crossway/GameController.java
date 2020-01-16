package dssc.crossway;

/**
 *  Class that encodes the methods to carry on the game
 *
 *
 */

public class GameController {

    private GoBoard board;
    private CrosswayRules rules;
    private int turn = 0;


    public GameController(GoBoard board, CrosswayRules rules) {
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
            board.setStoneColorStatus(m.getCoordinates(), m.getColor());
        }
        else {
            throw new IllegalMoveException();
        }
    }

    /**
     *  Return the color of a cell
     * @param coor coordinates
     * @return the status of the cell in that position
     * @throws OutOfBoardException if the coordinated are out of the board size
     */
    public StoneColor getStoneColorStatus(Coordinates coor) throws OutOfBoardException {
        return this.board.getStoneColorStatus(coor);
    }


    /**
     * Control if a move respect the set of rules encoded by its validator
     * @param m the Move object to be validated
     * @return boolean that encode the validity
     * @throws OutOfBoardException if the move is out of the board
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
    public StoneColor winner() { return  this.rules.winner(this.board); }

    /**
     * Same as PlaceStone but updates the number of turns
     * @param c Coordinates object
     * @throws IllegalMoveException if the move is forbidden by the rules
     * @throws OutOfBoardException if the coordinates are out of the board
     */
    public void performGameMove(Coordinates c) throws IllegalMoveException, OutOfBoardException {
        placeStone(new Move(c, currentTurnColor()));
        this.turn++;
    }


    /**
     * @return the color that is currently playing
     */
    public StoneColor currentTurnColor() {
        StoneColor starterColor = rules.firstPlayer();
        return (this.turn % 2 == 0) ? starterColor : starterColor.getOpposite();
    }

    public GoBoard getBoard() {
        return this.board;
    }
}