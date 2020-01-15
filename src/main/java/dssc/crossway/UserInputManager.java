package dssc.crossway;

public class UserInputManager {

    private ConsoleMessageWriter consoleMessageWriter  = new ConsoleMessageWriter();
    private ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
    /**
     * Asks the current player to input the next move.
     * @return a Coordinate object with the user input.
     */
     Coordinates askMove(GoBoard crosswayBoard) {

         int x = requestValidInput(crosswayBoard, 'x');
         int y = requestValidInput(crosswayBoard, 'y');
         return new Coordinates(x,y);
    }

    private int requestValidInput(GoBoard crosswayBoard, char coordinate){
        int dimension = -1;
        Boolean dimIsValid = false;
        while(true){
            if (coordinate == 'x') {
                consoleMessageWriter.requestXMoveMessage();
            }
            else if (coordinate == 'y') {
                consoleMessageWriter.requestYMoveMessage();
            }
            dimension = consoleInputHandler.askUserForInput();
            dimIsValid = crosswayBoard.isInside(dimension);
            if (dimIsValid) { break;} // <---------------------this works but can be made much better than this. refactor later.
            else {consoleMessageWriter.writeIllegalValueMessage();}

            }
        return dimension;
    }


}
