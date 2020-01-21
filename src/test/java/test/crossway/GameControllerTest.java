package test.crossway;

import dssc.crossway.*;
import dssc.crossway.exceptions.IllegalMoveException;
import dssc.crossway.exceptions.OutOfBoardException;
import org.junit.Test;

import static org.junit.Assert.*;


public class GameControllerTest {

    private GameController initialize() {
        return new GameController(new GoBoard(12), new CrosswayRules());
    }

   @Test
   public void firstMove() throws OutOfBoardException, IllegalMoveException {
       GameController gc = initialize();
       gc.performGameMove((new Coordinates(1,1)));
       assertEquals(gc.getBoard().getStoneColorStatus(new Coordinates(1,1)), StoneColor.BLACK);
   }

    @Test
    public void illegalMove() throws OutOfBoardException, IllegalMoveException {
        GameController gc = initialize();
        gc.performGameMove(new Coordinates(1,1));
        gc.performGameMove(new Coordinates(1,2));

        String exceptionMessage = "";

        try {
            gc.performGameMove(new Coordinates(1,1));
        } catch (IllegalMoveException e) {
            exceptionMessage = e.getMessage();
        }

        assertEquals("Illegal move!", exceptionMessage);

    }


    @Test
    public void fullMatch() throws OutOfBoardException, IllegalMoveException {

        GameController gc = initialize();
        assertEquals(StoneColor.EMPTY, gc.winner());

        assertEquals(gc.currentTurnColor(), StoneColor.BLACK);
        gc.performGameMove(new Coordinates(0,0));
        assertEquals(gc.currentTurnColor(), StoneColor.WHITE);
        gc.performGameMove(new Coordinates(0,0));

        assertEquals(gc.currentTurnColor(), StoneColor.BLACK);
        assertEquals(StoneColor.EMPTY, gc.winner());

        for(int i=0; i<10; i++)
        {
            gc.performGameMove(new Coordinates(i,i+1)); //BLACK
            gc.performGameMove(new Coordinates(i+1,i+1)); //WHITE
            assertEquals(StoneColor.EMPTY, gc.winner());
        }

        //assertFalse(gc.validateMove(new Move(new Coordinates(2,1), StoneColor.BLACK)));
        //assertFalse(gc.validateMove(new Move(new Coordinates(5,7), StoneColor.WHITE)));
        //assertTrue(gc.validateMove(new Move(new Coordinates(2,1), StoneColor.WHITE)));

        gc.performGameMove(new Coordinates(9,11)); //BLACK
        assertEquals(StoneColor.EMPTY, gc.winner());

        gc.performGameMove(new Coordinates(10,11)); //WHITE
        assertEquals(StoneColor.EMPTY, gc.winner());

        gc.performGameMove(new Coordinates(11,11)); //BLACK
        assertEquals(StoneColor.EMPTY, gc.winner());

        gc.performGameMove(new Coordinates(11,10)); //WHITE
        assertEquals(StoneColor.WHITE, gc.winner());

    }


}