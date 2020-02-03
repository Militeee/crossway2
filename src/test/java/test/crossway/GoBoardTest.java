package test.crossway;

import dssc.crossway.StoneColor;
import dssc.crossway.Coordinates;
import dssc.crossway.GoBoard;
import dssc.crossway.exceptions.OutOfBoardException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

       String exceptionMessage = "There were no exception";

       try {
           board.setStoneColorStatus(new Coordinates( 1,9), StoneColor.WHITE);
       } catch (OutOfBoardException e) {
           exceptionMessage = e.getMessage();
       }

       assertEquals("Out of board!", exceptionMessage);
   }

    @Test
    public void adjacentFriendsCoordinatesTest() throws OutOfBoardException {

        GoBoard board = new GoBoard (8);
        Coordinates c = new Coordinates(1,1);
        Coordinates f1 = new Coordinates(1,0);
        Coordinates f2 = new Coordinates(2,2);
        Coordinates f3 = new Coordinates(3,2);

        board.setStoneColorStatus(c,StoneColor.BLACK);
        board.setStoneColorStatus(f1,StoneColor.BLACK);
        board.setStoneColorStatus(f2,StoneColor.BLACK);
        board.setStoneColorStatus(f3,StoneColor.BLACK);

        board.setStoneColorStatus(new Coordinates(2,1),StoneColor.WHITE);
        board.setStoneColorStatus(new Coordinates(0,0),StoneColor.WHITE);
        board.setStoneColorStatus(new Coordinates(2,3),StoneColor.WHITE);

        List<Coordinates> friends = board.adjacentFriendsCoordinates(c);
        assertTrue(friends.contains(f1));
        assertTrue(friends.contains(f2));
        assertEquals(2, friends.size());

    }


    @Test
    public void getDiagonalPositionsTest() {

        GoBoard board = new GoBoard (8);
        Coordinates c1 = new Coordinates(0,0);
        Coordinates c2 = new Coordinates(0,5);
        Coordinates c3 = new Coordinates(3,5);

        assertEquals(1, board.getDiagonalPositions(c1).size());
        assertEquals(2, board.getDiagonalPositions(c2).size());

        ArrayList<Coordinates> c3diags = board.getDiagonalPositions(c3);

        assertTrue(c3diags.contains(new Coordinates(4,6)));
        assertTrue(c3diags.contains(new Coordinates(4,4)));
        assertTrue(c3diags.contains(new Coordinates(2,6)));
        assertTrue(c3diags.contains(new Coordinates(2,4)));
        assertFalse(c3diags.contains(new Coordinates(3,4)));
        assertFalse(c3diags.contains(new Coordinates(3,5)));

    }

}
