package test.crossway;

import dssc.crossway.*;
import dssc.crossway.exceptions.IllegalMoveException;
import dssc.crossway.exceptions.OutOfBoardException;
import org.junit.Test;

import static org.junit.Assert.*;

public class RulesTest {

    @Test
    public void legalMoveTest() throws OutOfBoardException {
        GoBoard b = new GoBoard(12);
        CrosswayRules r = new CrosswayRules();
        Move m0 = new Move(new Coordinates(1,1), StoneColor.WHITE);

        assertTrue(r.validateMove(b, m0, 20));
    }

    @Test
    public void pieRuleTest() throws OutOfBoardException {
        GoBoard b = new GoBoard(12);
        CrosswayRules r = new CrosswayRules();
        Coordinates c = new Coordinates(1, 1);
        b.setStoneColorStatus(c, StoneColor.BLACK);

        Move m = new Move(c, StoneColor.WHITE);

        assertTrue(r.validateMove(b, m, 1));
    }

    @Test
    public void noSuperpositionTest() throws OutOfBoardException {
        GoBoard b = new GoBoard(12);
        CrosswayRules r = new CrosswayRules();
        Coordinates c = new Coordinates(7, 1);
        b.setStoneColorStatus(c, StoneColor.BLACK);

        Move m = new Move(c, StoneColor.WHITE);

        assertFalse(r.validateMove(b, m, 2));
    }

    @Test
    public void crosswayIllegalMoveTestSoutheast() throws OutOfBoardException {

        /*
              0 1 2
            0
            1   F E
            2   E i

         */

        GoBoard b = new GoBoard(12);
        CrosswayRules r = new CrosswayRules();
        b.setStoneColorStatus(new Coordinates( 1, 1), StoneColor.BLACK);
        b.setStoneColorStatus(new Coordinates( 1, 2), StoneColor.WHITE);
        b.setStoneColorStatus(new Coordinates( 2, 1), StoneColor.WHITE);

        Move m = new Move(new Coordinates(2, 2), StoneColor.BLACK);
        assertFalse(r.validateMove(b, m, 7));
    }

    @Test
    public void crosswayIllegalMoveTestSouthwest() throws OutOfBoardException {

        /*
              0 1 2
            0
            1   E F
            2   i E

         */
        GoBoard b = new GoBoard(12);
        CrosswayRules r = new CrosswayRules();

        b.setStoneColorStatus(new Coordinates(2, 1), StoneColor.BLACK);
        b.setStoneColorStatus(new Coordinates(2, 2), StoneColor.WHITE);
        b.setStoneColorStatus(new Coordinates(1, 1), StoneColor.WHITE);


        Move m = new Move(new Coordinates(1, 2), StoneColor.BLACK);
        assertFalse(r.validateMove(b, m, 5));
    }

    @Test
    public void crosswayIllegalMoveTestNorthwest() throws OutOfBoardException {

        /*
              0 1 2
            0
            1   i E
            2   E F

         */
        GoBoard b = new GoBoard(12);
        CrosswayRules r = new CrosswayRules();

        b.setStoneColorStatus(new Coordinates( 2, 2), StoneColor.BLACK);
        b.setStoneColorStatus(new Coordinates( 1, 2), StoneColor.WHITE);
        b.setStoneColorStatus(new Coordinates(2, 1), StoneColor.WHITE);


        Move m = new Move(new Coordinates(1, 1), StoneColor.BLACK);
        assertFalse(r.validateMove(b, m, 25));
    }

    @Test
    public void crosswayIllegalMoveTestNorthEast() throws OutOfBoardException {

        /*
              0 1 2
            0
            1   E i
            2   F E

         */
        GoBoard b = new GoBoard(12);
        CrosswayRules r = new CrosswayRules();

        b.setStoneColorStatus(new Coordinates( 1, 2), StoneColor.BLACK);
        b.setStoneColorStatus(new Coordinates( 1, 1), StoneColor.WHITE);
        b.setStoneColorStatus(new Coordinates( 2, 2), StoneColor.WHITE);

        Move m = new Move(new Coordinates( 2, 1), StoneColor.BLACK);
        assertFalse(r.validateMove(b, m, 25));
    }

    @Test
    public void hasWhiteWon() throws  OutOfBoardException {

        GoBoard b = new GoBoard(12);
        CrosswayRules r = new CrosswayRules();

            for(int i = 0; i < b.getSide() ; i++){
                b.setStoneColorStatus(new Coordinates( i, 0), StoneColor.WHITE);
            }

        assertEquals(r.winner(b), StoneColor.WHITE);
    }


    @Test
    public void hasBlackWon() throws OutOfBoardException {

        GoBoard b = new GoBoard(12);
        CrosswayRules r = new CrosswayRules();

        for(int i = 0; i < b.getSide() ; i++){
            b.setStoneColorStatus(new Coordinates( 0, i), StoneColor.BLACK);
        }

        assertEquals(r.winner(b), StoneColor.BLACK);
    }
    
}