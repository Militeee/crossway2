package dssc.crossway;

import dssc.crossway.console.ConsoleBoardWriter;
import dssc.crossway.console.ConsoleMessageWriter;
import dssc.crossway.exceptions.IllegalMoveException;
import dssc.crossway.exceptions.OutOfBoardException;

class CrosswayGame {

    private ConsoleMessageWriter consoleMessageWriter = new ConsoleMessageWriter();
    private ConsoleBoardWriter consoleBoardWriter = new ConsoleBoardWriter();
    private UserInputManager userInputManager = new UserInputManager();

    private static final int CROSSWAY_BOARD_SIDE = 12;

    private GameController controller = new GameController(new GoBoard(CROSSWAY_BOARD_SIDE), new CrosswayRules());

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

    private void applyMove(Coordinates m) throws IllegalMoveException, OutOfBoardException {
        this.controller.performGameMove(m);
    }



}
