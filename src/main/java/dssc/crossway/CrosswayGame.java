package dssc.crossway;

import dssc.crossway.console.ConsoleBoardWriter;
import dssc.crossway.console.ConsoleMessageWriter;
import dssc.crossway.console.ConsoleInputManager;
import dssc.crossway.exceptions.IllegalMoveException;
import dssc.crossway.exceptions.OutOfBoardException;

class CrosswayGame {

    private ConsoleMessageWriter consoleMessageWriter = new ConsoleMessageWriter();
    private ConsoleBoardWriter consoleBoardWriter = new ConsoleBoardWriter();
    private ConsoleInputManager consoleInputManager = new ConsoleInputManager();

    private GameController controller = new GameController();

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
                Coordinates m = consoleInputManager.askMove(this.controller.getBoard());
                applyMove(m);
                validMove = true;

            } catch (IllegalMoveException|OutOfBoardException e) {
                consoleMessageWriter.writeIllegalMoveMessage();
            }

        }
    }

    private void applyMove(Coordinates m) throws IllegalMoveException, OutOfBoardException {
        this.controller.performGameMove(m);
    }

}