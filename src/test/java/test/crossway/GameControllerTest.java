package test.crossway;

import dssc.crossway.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GameControllerTest {



   @Test
   public void gameIsStarted(){
       GameController gc = initialize();

   }

   @Test
   public void firstMove() throws OutOfBoardException, IllegalMoveException {
       GameController gc = initialize();
       gc.placeStone(new Move(1,1, StoneColor.WHITE));
       assertEquals(gc.getCellStatus(1,1), StoneColor.WHITE);
   }

   private GameController initialize() {
       return new GameController(new GoBoard(12), new CrosswayRules());
   }


    @Test
    public void firstMove2() throws OutOfBoardException, IllegalMoveException {
        GameController gc = initialize();
        Move m = new Move(1,1, StoneColor.WHITE);
        gc.placeStone(m);
        assertEquals(gc.getCellStatus(1,1), StoneColor.WHITE);
    }

    @Test
    public void illegalMove() throws OutOfBoardException, IllegalMoveException {
        GameController gc = initialize();
        Move m1 = new Move(1,1, StoneColor.WHITE);
        gc.placeStone(m1);
        Move m2 = new Move(1,1, StoneColor.WHITE);

        try {
            gc.placeStone(m2);
        } catch (IllegalMoveException e) {
            assertEquals(e.getMessage(), "Illegal move!");
        }
    }


}
