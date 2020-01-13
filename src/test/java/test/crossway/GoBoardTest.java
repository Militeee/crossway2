package test.crossway;

import dssc.crossway.StoneColor;
import dssc.crossway.Coordinates;
import dssc.crossway.GoBoard;
import dssc.crossway.OutOfBoardException;
import org.junit.Test;

import static org.junit.Assert.*;


public class GoBoardTest {


   @Test
   public void getSideTest() {
       GoBoard board = new GoBoard (8);
       assertEquals(board.getSide(),8);
   }

    @Test
    public void isInsideTest() {
        GoBoard board = new GoBoard (8);
        assertTrue(board.isInside(new Coordinates(0,0)));
        assertTrue(board.isInside(new Coordinates(4,6)));
        assertFalse(board.isInside(new Coordinates(0,8)));
        assertFalse(board.isInside(new Coordinates(8,0)));
        assertFalse(board.isInside(new Coordinates(-1,5)));
        assertTrue(board.isInside(new Coordinates(7,7)));
        assertTrue(board.isInside(new Coordinates(7,0)));
    }

   @Test
   public void setStoneColorStatusTest() throws OutOfBoardException {
       GoBoard board = new GoBoard(8);
       board.setStoneColorStatus(new Coordinates( 1,4), StoneColor.WHITE);
       assertEquals(board.getStoneColorStatus(new Coordinates( 1,4)), StoneColor.WHITE);
   }

    @Test
    public void getStoneColorStatusTest() throws OutOfBoardException {
        GoBoard board = new GoBoard(8);
        assertEquals(board.getStoneColorStatus(new Coordinates( 1,4)), StoneColor.EMPTY);
    }

   @Test
   public void testExceptionMessage() {

       GoBoard board = new GoBoard (8);
       try {
           board.setStoneColorStatus(new Coordinates( 1,9), StoneColor.WHITE);
       } catch (OutOfBoardException e) {
           assertEquals(e.getMessage(),"Out of board!");
       }
   }

    @Test
    public void testToString() throws OutOfBoardException {

        GoBoard board = new GoBoard (12);

        board.setStoneColorStatus(new Coordinates( 1,9), StoneColor.WHITE);
        board.setStoneColorStatus(new Coordinates( 0,2), StoneColor.WHITE);
        board.setStoneColorStatus(new Coordinates( 7,4), StoneColor.BLACK);
        board.setStoneColorStatus(new Coordinates( 5,11), StoneColor.BLACK);
        board.setStoneColorStatus(new Coordinates(5,7), StoneColor.BLACK);



    String expected ="  0 1 2 3 4 5 6 7 8 9 0 1 \n"+
        "0 . . . . . . . . . . . . \n"+
        "1 . . . . . . . . . . . . \n"+
        "2 ● . . . . . . . . . . . \n"+
        "3 . . . . . . . . . . . . \n"+
        "4 . . . . . . . ○ . . . . \n"+
        "5 . . . . . . . . . . . . \n"+
        "6 . . . . . . . . . . . . \n"+
        "7 . . . . . ○ . . . . . . \n"+
        "8 . . . . . . . . . . . . \n"+
        "9 . ● . . . . . . . . . . \n"+
        "0 . . . . . . . . . . . . \n"+
        "1 . . . . . ○ . . . . . . ";

        assertEquals(expected,board.toString());
    }

}
