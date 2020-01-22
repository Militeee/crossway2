package dssc.crossway;

import dssc.crossway.exceptions.IllegalMoveException;
import dssc.crossway.exceptions.OutOfBoardException;

public class GameController {

    private GoBoard board;
    private CrosswayRules rules;
    private int turn = 0;


    public GameController(GoBoard board, CrosswayRules rules) {
        this.board = board;
        this.rules = rules;
    }

    private void placeStone(Move m) throws OutOfBoardException, IllegalMoveException {
        if(this.validateMove(m)) {
            board.setStoneColorStatus(m.getCoordinates(), m.getColor());
        }
        else {
            throw new IllegalMoveException();
        }
    }

    private boolean validateMove(Move m) throws OutOfBoardException {
        return rules.validateMove(this.board, m, this.turn);
    }

    public StoneColor winner() { return  this.rules.winner(this.board, currentTurnColor()); }

    public void performGameMove(Coordinates c) throws IllegalMoveException, OutOfBoardException {
        placeStone(new Move(c, currentTurnColor()));
        this.turn++;
    }

    public StoneColor currentTurnColor() {
        StoneColor starterColor = rules.firstPlayer();
        return (this.turn % 2 == 0) ? starterColor : starterColor.getOpposite();
    }

    public GoBoard getBoard() {
        return this.board;
    }
}