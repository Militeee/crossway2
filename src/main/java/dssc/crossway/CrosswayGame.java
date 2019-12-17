package dssc.crossway;

import java.util.Scanner;

public class CrosswayGame {

    public static final int CROSSWAY_BOARD_SIDE = 12;

    private GameController controller = new GameController(new GoBoard(CROSSWAY_BOARD_SIDE), new CrosswayRules());

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

    private void showCurrentPlayer() {
        System.out.println(controller.currentTurnColor() + " moves now:");
    }

    private void printBoard() {
        System.out.println(controller.stringBoard());
    }


    private void applyMove(Coordinates m) throws IllegalMoveException, OutOfBoardException {
        this.controller.performGameMove(m);
    }

    private Coordinates askMove() {

        int x = ConsoleInputHandler.askInt(0, CROSSWAY_BOARD_SIDE, "Enter X:");
        int y = ConsoleInputHandler.askInt(0, CROSSWAY_BOARD_SIDE, "Enter Y:");

        return new Coordinates(x,y);
    }


    private void showEndingMessage(Colors winner) {
        System.out.println("Winner:" + winner.toString());
    }

    private void showStartingMessage() {
        System.out.println("Rules placeholder");
    }
}
