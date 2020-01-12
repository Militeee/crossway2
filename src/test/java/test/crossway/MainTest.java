package test.crossway;

import dssc.crossway.*;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MainTest {


    @Test
    public void getSideTest() {
        GoBoard board = new GoBoard (8);
        assertEquals(board.getSide(),8);
    }

    @Test
    public void setCellStatusTest() throws OutOfBoardException {
        GoBoard board = new GoBoard(8);
        board.setCellStatus(new Coordinates(1,4), StoneColor.WHITE);
        assertEquals(board.getCellStatus(new Coordinates(1,4)), StoneColor.WHITE);
    }
}
