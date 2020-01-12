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
   public void setCellStatusTest() throws OutOfBoardException {
       GoBoard board = new GoBoard(8);
       board.setCellStatus(new Coordinates( 1,4), StoneColor.WHITE);
       assertEquals(board.getCellStatus(new Coordinates( 1,4)), StoneColor.WHITE);
   }

    @Test
    public void getCellStatusTest() throws OutOfBoardException {
        GoBoard board = new GoBoard(8);
        assertEquals(board.getCellStatus(new Coordinates( 1,4)), StoneColor.EMPTY);
    }

   @Test
   public void testExceptionMessage() {

       GoBoard board = new GoBoard (8);
       try {
           board.setCellStatus(new Coordinates( 1,9), StoneColor.WHITE);
       } catch (OutOfBoardException e) {
           assertEquals(e.getMessage(),"Out of board!");
       }
   }

    @Test
    public void testToString() {

        GoBoard board = new GoBoard (12);
        try {
            board.setCellStatus(new Coordinates( 1,9), StoneColor.WHITE);
            board.setCellStatus(new Coordinates( 0,2), StoneColor.WHITE);
            board.setCellStatus(new Coordinates( 7,4), StoneColor.BLACK);
            board.setCellStatus(new Coordinates( 5,11), StoneColor.BLACK);
            board.setCellStatus(new Coordinates(5,7), StoneColor.BLACK);
        } catch (OutOfBoardException e) {
            assertEquals(e.getMessage(),"Out of board!");
        }

       // String expected = "............accapo............accapoW...........accapo............accapo.......B....accapo............accapo............accapo.....B......accapo............accapo.W..........accapo............accapo.....B......accapo";

        String expected =                  //y
                        "............\n" + //0
                        "............\n" + //1
                        "W...........\n" + //2
                        "............\n" + //3
                        ".......B....\n" + //4
                        "............\n" + //5
                        "............\n" + //6
                        ".....B......\n" + //7
                        "............\n" + //8
                        ".W..........\n" + //9
                        "............\n" + //10
                        ".....B......";    //11
                    //x: 0123456789te

        assertEquals(expected,board.toString());
    }

 




}
