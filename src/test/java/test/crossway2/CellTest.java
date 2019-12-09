package test.crossway2;


import dssc.crossway2.Cell;
import dssc.crossway2.Colors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CellTest {

   @Test
   void initializeCell () {
       Cell c = new Cell();
       assertEquals(c.getStatus(), Colors.EMPTY);
   }

   @Test
   void initializeWithEnumClass() {
       Cell c = new Cell();
       assertEquals(c.getStatus(), Colors.EMPTY);
   }

   @Test
   void oppositeColorTest() {
       assertEquals(Colors.BLACK.getOpposite(),Colors.WHITE);
       assertEquals(Colors.WHITE.getOpposite(),Colors.BLACK);
       assertEquals(Colors.EMPTY.getOpposite(),Colors.EMPTY);

   }
}
