package dssc.crossway;

public class ConsoleMessageWriter {


    private static final String STARTING_MESSAGE = "CROSSWAY";
    private static final String WINNER_MESSAGE = "\n%s won the game";
    private static final String CURRENT_PLAYER_MESSAGE = "\n%s moves now";
    private static final String X_REQUEST_MESSAGE = "Enter X:";
    private static final String Y_REQUEST_MESSAGE = "Enter Y:";
    private static final String WRONG_FORMAT_MESSAGE = "The input format is not correct, please retry";
    private static final String ILLEGAL_VALUE_MESSAGE = "The inserted value is illegal";



    /**
     * Prints a message when there is a winner.
     * @param winner Winner color.
     */
    void showEndingMessage(StoneColor winner) {
        System.out.println(String.format(WINNER_MESSAGE, winner.toString()));
    }


    void showStartingMessage() {
        System.out.println(STARTING_MESSAGE);
    }


    void showCurrentPlayer(StoneColor player) {
        System.out.println(String.format(CURRENT_PLAYER_MESSAGE, player));
    }

    void requestYMoveMessage( ) {
        System.out.println(Y_REQUEST_MESSAGE);
    }


    void requestXMoveMessage( ) {
        System.out.println(X_REQUEST_MESSAGE);
    }

    void writeWrongFormatMessage( ) {
        System.out.println(WRONG_FORMAT_MESSAGE);
    }

    void writeIllegalValueMessage( ) {
        System.out.println(ILLEGAL_VALUE_MESSAGE);
    }

}
