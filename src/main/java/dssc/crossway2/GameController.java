package dssc.crossway2;

public class GameController {
    private final CrosswayRules rules;
    private GoBoard board;

    /**
     * GameController
     * @param goBoard a valid GoBoard
     * @param crosswayRules a valid rule set
     */
    public GameController(GoBoard goBoard, CrosswayRules crosswayRules) {
        this.board = goBoard;
        this.rules = crosswayRules;
    }

    /**
     * Helper method, remove in future
     * @return true if the board exists
     */
    public boolean exists() {
        return true;
    }
}
