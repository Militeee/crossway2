package test.crossway;

import dssc.crossway.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsoleGraphicsTest {


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
