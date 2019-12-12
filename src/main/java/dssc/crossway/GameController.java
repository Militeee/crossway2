package dssc.crossway;

public class GameController {

    /**
     *  Class that encodes the methods to carry on the game
     *
     *
     */

    private GoBoard board;
    private  Validator rules;
    private int turn = 0;


    public GameController(GoBoard board, Validator rules) {
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


  //  public void placeStone(int x, int y, Colors color) throws OutOfBoardException {
  //      board.setCellStatus(x,y,color);
  //
  //  }
    public void placeStone(Move m) throws OutOfBoardException, IllegalMoveException {
        if(this.validateMove(m)) {
            board.setCellStatus(m.getX(), m.getY(), m.getColor());

        }
        else {
            throw new IllegalMoveException();
        }
    }


    public Colors getCellStatus(int x, int y) throws OutOfBoardException {
        return this.board.getCellStatus(x,y);
    }




    public boolean validateMove(Move m) throws OutOfBoardException {
        return rules.validateMove(this.board, m, this.turn);
    }

    public int getSide()  {
        return this.board.getSide();
    }

    public Colors winner() {

        return  this.rules.winner(this.board);

    }

    public void performGameMove(Coordinates m) throws IllegalMoveException, OutOfBoardException {
        placeStone(new Move(m.getX_cord(), m.getY_cord(), currentTurnColor()));
        this.turn++;
    }

    public Colors currentTurnColor() {
        return (this.turn % 2 == 0) ? Colors.WHITE : Colors.BLACK;
    }

    public String stringBoard() {
        return this.board.toString();
    }

}

