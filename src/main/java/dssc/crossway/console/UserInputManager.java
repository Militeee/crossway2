package dssc.crossway.console;

import dssc.crossway.Coordinates;
import dssc.crossway.GoBoard;

public class UserInputManager {

    private ConsoleMessageWriter consoleMessageWriter  = new ConsoleMessageWriter();
    private ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();

    public Coordinates askMove(GoBoard crosswayBoard) {

         int x = requestValidInput(crosswayBoard, 'x');
         int y = requestValidInput(crosswayBoard, 'y');
         return new Coordinates(x,y);
    }

    private int requestValidInput(GoBoard crosswayBoard, char coordinate){

        int dimension = -1;

        boolean dimIsValid = false;

        while(!dimIsValid){

            if (coordinate == 'x') {
                consoleMessageWriter.requestXMoveMessage();
            }
            else if (coordinate == 'y') {
                consoleMessageWriter.requestYMoveMessage();
            }

            dimension = consoleInputHandler.askUserForInteger();
            dimIsValid = crosswayBoard.isInside(dimension);

            if(!dimIsValid)
                consoleMessageWriter.writeIllegalValueMessage();
            }

        return dimension;
    }


}
