package dssc.crossway;

/**
 *  Main game management class.
 *  <p>
 *      Class responsibility: Manage game turns.
 */
public class CrosswayGame {

    public static final int CROSSWAY_BOARD_SIDE = 12;
    private static final String STARTING_MESSAGE = "CROSSWAY";
    private static final String WINNER_MESSAGE = "%s won the game";
    private static final String CURRENT_PLAYER_MESSAGE = "%s moves now";
    private static final String X_REQUEST_MESSAGE = "Enter X:";
    private static final String Y_REQUEST_MESSAGE = "Enter Y:";

    private GameController controller = new GameController(new GoBoard(CROSSWAY_BOARD_SIDE), new CrosswayRules());


    /**
     *  Instantiate a new game and manage turns.
     */
    public void start() {

        showStartingMessage();

        boolean gameOver = false;
        StoneColor winner = StoneColor.EMPTY;

        while(!gameOver) {
            turn();
            winner = controller.winner();
            gameOver = winner != StoneColor.EMPTY;
        }

        showEndingMessage(winner);
    }

    /**
     * helper class for a single turn management.
     * @return Color of winner. Returns Colors.EMPTY if no one is winning the game.
     */
    private void turn()  {
        boolean validMove = false;
        printBoard();
        showCurrentPlayer();

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
     *  Helper class. Prints the current player color.
     */
    private void showCurrentPlayer() {
        System.out.println(String.format(CURRENT_PLAYER_MESSAGE, controller.currentTurnColor()));
    }

    /**
     *  Helper class. Pretty prints a board.
     */
    private void printBoard() {
        System.out.println(controller.stringBoard());
    }

    /**
     * Helper class. It tries to perform a move m.
     * @param m a Coordinate object
     * @throws IllegalMoveException whenever the move is illegal
     * @throws OutOfBoardException whenever the coordinates falls outside of the board
     */
    private void applyMove(Coordinates m) throws IllegalMoveException, OutOfBoardException {
        this.controller.performGameMove(m);
    }

    /**
     * Helper class. Asks the current player to input the next move.
     * @return a Coordinate object with the user input.
     */
    private Coordinates askMove() {
        int x = ConsoleInputHandler.askInt(0, CROSSWAY_BOARD_SIDE, X_REQUEST_MESSAGE);
        int y = ConsoleInputHandler.askInt(0, CROSSWAY_BOARD_SIDE, Y_REQUEST_MESSAGE);
        return new Coordinates(x,y);
    }

    /**
     * Helper class: prints a message when there is a winner.
     * @param winner Winner color.
     */
    private void showEndingMessage(StoneColor winner) {
        System.out.println(String.format(WINNER_MESSAGE, winner.toString()));
    }

    /**
     * Helper class: prints welcome message and game rules.
     */
    private void showStartingMessage() {
        System.out.println(STARTING_MESSAGE);
    }
}
