package dssc.crossway;

import java.util.Scanner;

/**
 *  Main game managemen class.
 *  <p>
 *      Class responsability: Manage game turns.
 */
public class CrosswayGame {

    public static final int CROSSWAY_BOARD_SIDE = 12;

    private GameController controller = new GameController(new GoBoard(CROSSWAY_BOARD_SIDE), new CrosswayRules());

    /**
     *  Instantiate a new game and manage turns.
     */
    public void start() {

        showStartingMessage();

        boolean gameOver = false;
        Colors winner = Colors.EMPTY;

        while(!gameOver) {
            winner = turn();
            gameOver = winner != Colors.EMPTY;
        }

        showEndingMessage(winner);

    }

    /**
     * helper class for a single turn management.
     * @return Color of winner. Returns Colors.EMPTY if no one is winning the game.
     */
    private Colors turn()  {
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
        return controller.winner();
    }

    /**
     *  Helper class. Prints the current player color.
     */
    private void showCurrentPlayer() {
        System.out.println(controller.currentTurnColor() + " moves now:");
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

        int x = ConsoleInputHandler.askInt(0, CROSSWAY_BOARD_SIDE, "Enter X:");
        int y = ConsoleInputHandler.askInt(0, CROSSWAY_BOARD_SIDE, "Enter Y:");

        return new Coordinates(x,y);
    }

    /**
     * Helper class: prints a message when there is a winner.
     * @param winner
     */
    private void showEndingMessage(Colors winner) {
        System.out.println("Winner:" + winner.toString());
    }

    /**
     * Helper class: prints welcome message and game rules.
     */
    private void showStartingMessage() {
        System.out.println("Rules placeholder");
    }
}
