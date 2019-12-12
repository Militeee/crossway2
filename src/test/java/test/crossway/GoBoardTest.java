package test.crossway;

import dssc.crossway.Colors;
import dssc.crossway.GoBoard;
import dssc.crossway.OutOfBoardException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


class GoBoardTest {


   @Test
   public void getSideTest() {
       GoBoard board = new GoBoard (8);
       assertEquals(board.getSide(),8);
   }

   @Test
   public void setCellStatusTest() throws OutOfBoardException {
       GoBoard board = new GoBoard(8);
       board.setCellStatus(1,4, Colors.WHITE);
       assertEquals(board.getCellStatus(1,4), Colors.WHITE);
   }

    @Test
    public void getCellStatusTest() throws OutOfBoardException {
        GoBoard board = new GoBoard(8);
        assertEquals(board.getCellStatus(1,4), Colors.EMPTY);
    }

   @Test
   public void testExceptionMessage() {

       GoBoard board = new GoBoard (8);
       try {
           board.setCellStatus(1,9, Colors.WHITE);
       } catch (OutOfBoardException e) {
           assertEquals(e.getMessage(),"Out of board!");
       }
   }

    @Test
    public void testToString() {

        GoBoard board = new GoBoard (12);
        try {
            board.setCellStatus(1,9, Colors.WHITE);
            board.setCellStatus(0,2, Colors.WHITE);
            board.setCellStatus(7,4, Colors.BLACK);
            board.setCellStatus(5,11, Colors.BLACK);
            board.setCellStatus(5,7, Colors.BLACK);
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
