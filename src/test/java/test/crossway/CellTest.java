package test.crossway;


import dssc.crossway.Cell;
import dssc.crossway.StoneColor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CellTest {

   @Test
   public void initializeCell () {
       Cell c = new Cell();
       assertEquals(c.getStatus(), StoneColor.EMPTY);
   }

   @Test
   public void initializeWithEnumClass() {
       Cell c = new Cell();
       assertEquals(c.getStatus(), StoneColor.EMPTY);
   }

   @Test
   public void oppositeColorTest() {
       assertEquals(StoneColor.BLACK.getOpposite(), StoneColor.WHITE);
       assertEquals(StoneColor.WHITE.getOpposite(), StoneColor.BLACK);
       assertEquals(StoneColor.EMPTY.getOpposite(), StoneColor.EMPTY);

   }
}
