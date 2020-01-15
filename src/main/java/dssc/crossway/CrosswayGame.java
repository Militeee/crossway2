package dssc.crossway;

/**
 *  Main game management class.
 *  <p>
 *      Class responsibility: Manage game turns logic.
 */
public class CrosswayGame {

    private ConsoleMessageWriter consoleMessageWriter = new ConsoleMessageWriter();
    private ConsoleBoardWriter consoleBoardWriter = new ConsoleBoardWriter();

    public static final int CROSSWAY_BOARD_SIDE = 12;
    private static final String CURRENT_PLAYER_MESSAGE = "\n%s moves now";
    private static final String X_REQUEST_MESSAGE = "Enter X:";
    private static final String Y_REQUEST_MESSAGE = "Enter Y:";

    private GameController controller = new GameController(new GoBoard(CROSSWAY_BOARD_SIDE), new CrosswayRules());


    /**
     *  Instantiate a new game and manage turns.
     */
    public void start() {

        consoleMessageWriter.showStartingMessage();

        boolean gameOver = false;
        StoneColor winner = StoneColor.EMPTY;

        while(!gameOver) {
            turn();
            winner = controller.winner();
            gameOver = (winner != StoneColor.EMPTY);
        }

        consoleBoardWriter.printBoard(this.controller.getBoard());
        consoleMessageWriter.showEndingMessage(winner);
    }

    /**
     * Method for a single turn management.
     */
    private void turn()  {

        consoleBoardWriter.printBoard(this.controller.getBoard());
        showCurrentPlayer();
        boolean validMove = false;

        while(!validMove) {

            try {
                Coordinates m = askMove();
                applyMove(m);
                validMove = true;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     *  Prints the current player color.
     */
    private void showCurrentPlayer() {
        System.out.println(String.format(CURRENT_PLAYER_MESSAGE, controller.currentTurnColor()));
    }



    /**
     * Tries to perform a move m.
     * @param m a Coordinate object
     * @throws IllegalMoveException whenever the move is illegal
     * @throws OutOfBoardException whenever the coordinates falls outside of the board
     */
    private void applyMove(Coordinates m) throws IllegalMoveException, OutOfBoardException {
        this.controller.performGameMove(m);
    }

    /**
     * Asks the current player to input the next move.
     * @return a Coordinate object with the user input.
     */
    private Coordinates askMove() {
        int x = ConsoleInputHandler.askUserForInput(0, CROSSWAY_BOARD_SIDE, X_REQUEST_MESSAGE);
        int y = ConsoleInputHandler.askUserForInput(0, CROSSWAY_BOARD_SIDE, Y_REQUEST_MESSAGE);
        return new Coordinates(x,y);
    }


}
