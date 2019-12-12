package test.crossway;

import dssc.crossway.*;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {
    //board initializer
    private GameController initialize() {
        return new GameController(new GoBoard(12), new CrosswayRules());
    }

    @Test
    public void boardIsValid(){
        GameController gc = initialize();
        assertTrue(gc.exists());
    }

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
