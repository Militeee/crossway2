package test.crossway;

import dssc.crossway.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


class GameControllerTest {


   @Test
   public void boardIsValid(){
       GameController gc = initialize();
       assertTrue(gc.exists());
   }
   @Test
   public void gameIsStarted(){
       GameController gc = initialize();
       gc.startGame();

   }

   @Test
   public void firstMove() throws OutOfBoardException, IllegalMoveException {
       GameController gc = initialize();
       gc.startGame();
       gc.placeStone(new Move(1,1, Colors.WHITE));
       assertEquals(gc.getCellStatus(1,1), Colors.WHITE);
   }

   private GameController initialize() {
       return new GameController(new GoBoard(12), new CrosswayRules());
   }


    @Test
    public void firstMove2() throws OutOfBoardException, IllegalMoveException {
        GameController gc = initialize();
        gc.startGame();
        Move m = new Move(1,1, Colors.WHITE);
        gc.placeStone(m);
        assertEquals(gc.getCellStatus(1,1), Colors.WHITE);
    }

    @Test
    public void illegalMove() throws OutOfBoardException, IllegalMoveException {
        GameController gc = initialize();
        gc.startGame();
        Move m1 = new Move(1,1, Colors.WHITE);
        gc.placeStone(m1);
        Move m2 = new Move(1,1, Colors.WHITE);

        try {
            gc.placeStone(m2);
        } catch (IllegalMoveException e) {
            assertEquals(e.getMessage(), "Illegal move!");
        }
    }


}
