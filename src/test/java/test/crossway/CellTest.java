package test.crossway;


import dssc.crossway.Cell;
import dssc.crossway.Colors;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

class CellTest {

   @Test
   public void initializeCell () {
       Cell c = new Cell();
       assertEquals(c.getStatus(), Colors.EMPTY);
   }

   @Test
   public void initializeWithEnumClass() {
       Cell c = new Cell();
       assertEquals(c.getStatus(), Colors.EMPTY);
   }

   @Test
   public void oppositeColorTest() {
       assertEquals(Colors.BLACK.getOpposite(),Colors.WHITE);
       assertEquals(Colors.WHITE.getOpposite(),Colors.BLACK);
       assertEquals(Colors.EMPTY.getOpposite(),Colors.EMPTY);

   }
}
