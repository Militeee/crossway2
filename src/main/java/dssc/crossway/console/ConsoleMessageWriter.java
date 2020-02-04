package dssc.crossway.console;

import dssc.crossway.StoneColor;

public class ConsoleMessageWriter {

    private static final String STARTING_MESSAGE = "CROSSWAY";
    private static final String WINNER_MESSAGE = "\n%s won the game";
    private static final String CURRENT_PLAYER_MESSAGE = "\n%s moves now";
    private static final String X_REQUEST_MESSAGE = "Enter X:";
    private static final String Y_REQUEST_MESSAGE = "Enter Y:";
    private static final String WRONG_FORMAT_MESSAGE = "The input format is not correct, please retry";
    private static final String ILLEGAL_VALUE_MESSAGE = "The inserted value is illegal";
    private static final String ILLEGAL_MOVE_MESSAGE = "Illegal Move";
    private static final String OUT_OF_BOARD_MESSAGE = "Out of board coordinates";

    public void showEndingMessage(StoneColor winner) {
        System.out.println(String.format(WINNER_MESSAGE, winner.toString()));
    }

    public void showStartingMessage() {
        System.out.println(STARTING_MESSAGE);
    }

    public void showCurrentPlayer(StoneColor player) {
        System.out.println(String.format(CURRENT_PLAYER_MESSAGE, player));
    }

    public void requestYMoveMessage() {
        System.out.println(Y_REQUEST_MESSAGE);
    }

    public void requestXMoveMessage() {
        System.out.println(X_REQUEST_MESSAGE);
    }

    public void writeWrongFormatMessage( ) {
        System.out.println(WRONG_FORMAT_MESSAGE);
    }

    public void writeIllegalValueMessage() {
        System.out.println(ILLEGAL_VALUE_MESSAGE);
    }

    public void writeIllegalMoveMessage() { System.out.println(ILLEGAL_MOVE_MESSAGE); }

    public void writeOutOfBoardMessage() { System.out.println(OUT_OF_BOARD_MESSAGE); }
}
