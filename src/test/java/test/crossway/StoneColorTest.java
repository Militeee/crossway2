package test.crossway;

import dssc.crossway.StoneColor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StoneColorTest {

    @Test
    public void oppositeTest() {
        assertEquals(StoneColor.EMPTY, StoneColor.EMPTY.getOpposite());
        assertEquals(StoneColor.BLACK, StoneColor.WHITE.getOpposite());
        assertEquals(StoneColor.WHITE, StoneColor.BLACK.getOpposite());
    }
}
