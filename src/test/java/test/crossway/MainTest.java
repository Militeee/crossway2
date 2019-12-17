package test.crossway;

import dssc.crossway.*;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {


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
}
