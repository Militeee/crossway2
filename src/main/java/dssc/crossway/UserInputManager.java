package dssc.crossway;

import dssc.crossway.console.ConsoleInputHandler;
import dssc.crossway.console.ConsoleMessageWriter;

public class UserInputManager {

    private ConsoleMessageWriter consoleMessageWriter  = new ConsoleMessageWriter();
    private ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();

    Coordinates askMove(GoBoard crosswayBoard) {

         int x = requestValidInput(crosswayBoard, 'x');
         int y = requestValidInput(crosswayBoard, 'y');
         return new Coordinates(x,y);
    }

    private int requestValidInput(GoBoard crosswayBoard, char coordinate){
         /*
         int dimension;
         do {
             if (coordinate == 'x') {
                 consoleMessageWriter.requestXMoveMessage();
             }
             else if (coordinate == 'y') {
                 consoleMessageWriter.requestYMoveMessage();
             }
             dimension = consoleInputHandler.askUserForInteger();
         } while (!(crosswayBoard.isInside( dimension )));
        */

        int dimension = -1;

        Boolean dimIsValid = true;
        while(dimIsValid){

            if (coordinate == 'x') {
                consoleMessageWriter.requestXMoveMessage();
            }
            else if (coordinate == 'y') {
                consoleMessageWriter.requestYMoveMessage();
            }

            dimension = consoleInputHandler.askUserForInteger();
            dimIsValid = crosswayBoard.isInside(dimension);
            if (dimIsValid) { break;} // <---------------------this works but can be made much better than this. refactor later.
            else {consoleMessageWriter.writeIllegalValueMessage();}

            }


        return dimension;
    }


}
