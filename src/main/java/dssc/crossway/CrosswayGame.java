package dssc.crossway;

import java.util.Scanner;

public class CrosswayGame {

    private GameController controller = new GameController(new GoBoard(12), new CrosswayRules());

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

        while(!validMove) {
            printBoard();
            showCurrentPlayer();
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

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter X:");
        int x = scanner.nextInt();
        System.out.print("Enter Y:");
        int y = scanner.nextInt();



        return new Coordinates(x,y);
    }


    private void showEndingMessage(Colors winner) {
        System.out.println("Winner:" + winner.toString());
    }

    private void showStartingMessage() {
        System.out.println("Rules placeholder");
    }
}
