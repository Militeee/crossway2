package dssc.crossway;

/**
 *  Main game management class.
 *  <p>
 *      Class responsibility: Manage game turns logic.
 */
public class CrosswayGame {

    private ConsoleMessageWriter consoleMessageWriter = new ConsoleMessageWriter();
    private ConsoleBoardWriter consoleBoardWriter = new ConsoleBoardWriter();
    private UserInputManager userInputManager = new UserInputManager();

    public static final int CROSSWAY_BOARD_SIDE = 12;


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
        consoleMessageWriter.showCurrentPlayer(controller.currentTurnColor());
        boolean validMove = false;

        while(!validMove) {

            try {
                Coordinates m = userInputManager.askMove(this.controller.getBoard());
                applyMove(m);
                validMove = true;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
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




}
