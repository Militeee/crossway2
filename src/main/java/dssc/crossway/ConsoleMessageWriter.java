package dssc.crossway;

public class ConsoleMessageWriter {


    private static final String STARTING_MESSAGE = "CROSSWAY";
    private static final String WINNER_MESSAGE = "\n%s won the game";



    /**
     * Prints a message when there is a winner.
     * @param winner Winner color.
     */
    void showEndingMessage(StoneColor winner) {
        System.out.println(String.format(WINNER_MESSAGE, winner.toString()));
    }

    /**
     * Prints welcome message and game rules.
     */
    void showStartingMessage() {
        System.out.println(STARTING_MESSAGE);
    }



}
