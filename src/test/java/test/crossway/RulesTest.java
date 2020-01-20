package test.crossway;

import dssc.crossway.*;
import dssc.crossway.exceptions.IllegalMoveException;
import dssc.crossway.exceptions.OutOfBoardException;
import org.junit.Test;

import static org.junit.Assert.*;

public class RulesTest {

    @Test
    public void validateLegalMoveTest() throws OutOfBoardException {
        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());
        Move m0 = new Move(new Coordinates(1,1), StoneColor.WHITE);
        assertTrue(gc.validateMove(m0));
    }

    @Test
    public void validatePieRuleTest() throws OutOfBoardException, IllegalMoveException {
        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());


        Coordinates m0 = new Coordinates(1, 1);
        Move m1 = new Move(m0, StoneColor.BLACK);

        gc.performGameMove(m0);
        assertTrue(gc.validateMove(m1));
    }

    @Test
    public void validateIllegalMoveTest() throws OutOfBoardException, IllegalMoveException {
        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());

        Move m1 = new Move(new Coordinates(1,1), StoneColor.WHITE);
        Move m2 = new Move(new Coordinates(1,1), StoneColor.WHITE);
        gc.placeStone( m1 );
        assertFalse(gc.validateMove(m2));
    }

    @Test
    public void crosswayIllegalMoveTestSoutheast() throws OutOfBoardException, IllegalMoveException {

        /*
              0 1 2
            0
            1   F E
            2   E i

         */
        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());

        gc.placeStone(new Move(new Coordinates( 1, 1), StoneColor.BLACK));
        gc.placeStone(new Move(new Coordinates( 1, 2), StoneColor.WHITE));
        gc.placeStone(new Move(new Coordinates( 2, 1), StoneColor.WHITE));

        Move m4 = new Move(new Coordinates(2, 2), StoneColor.BLACK);
        assertFalse(gc.validateMove(m4));
    }

    @Test
    public void crosswayIllegalMoveTestSouthwest() throws OutOfBoardException, IllegalMoveException {

        /*
              0 1 2
            0
            1   E F
            2   i E

         */
        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());

        gc.placeStone(new Move(new Coordinates(2, 1), StoneColor.BLACK));
        gc.placeStone(new Move(new Coordinates(2, 2), StoneColor.WHITE));
        gc.placeStone(new Move(new Coordinates(1, 1), StoneColor.WHITE));


        Move m4 = new Move(new Coordinates(1, 2), StoneColor.BLACK);
        assertFalse(gc.validateMove(m4));
    }

    @Test
    public void crosswayIllegalMoveTestNorthwest() throws OutOfBoardException, IllegalMoveException {

        /*
              0 1 2
            0
            1   i E
            2   E F

         */
        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());

        gc.placeStone(new Move(new Coordinates( 2, 2), StoneColor.BLACK));
        gc.placeStone(new Move(new Coordinates( 1, 2), StoneColor.WHITE));
        gc.placeStone(new Move(new Coordinates(2, 1), StoneColor.WHITE));


        Move m4 = new Move(new Coordinates(1, 1), StoneColor.BLACK);
        assertFalse(gc.validateMove(m4));
    }

    @Test
    public void crosswayIllegalMoveTestNorthEast() throws OutOfBoardException, IllegalMoveException {

        /*
              0 1 2
            0
            1   E i
            2   F E

         */
        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());

        gc.placeStone(new Move(new Coordinates( 1, 2), StoneColor.BLACK));
        gc.placeStone(new Move(new Coordinates( 1, 1), StoneColor.WHITE));
        gc.placeStone(new Move(new Coordinates( 2, 2), StoneColor.WHITE));


        Move m4 = new Move(new Coordinates( 2, 1), StoneColor.BLACK);
        assertFalse(gc.validateMove(m4));
    }

    @Test
    public void hasWhiteWon() throws IllegalMoveException, OutOfBoardException {


        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());

            for(int i = 0; i < gc.getSide() ; i++){
                gc.placeStone(new Move(new Coordinates( i, 0), StoneColor.WHITE));
            }

        assertEquals(gc.winner(), StoneColor.WHITE);
    }


    @Test
    public void hasBlackWon() throws IllegalMoveException, OutOfBoardException {


        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());

        for(int i = 0; i < gc.getSide() ; i++){
            gc.placeStone(new Move(new Coordinates( 0, i), StoneColor.BLACK));
        }

        assertEquals(gc.winner(), StoneColor.BLACK);
    }
    
}