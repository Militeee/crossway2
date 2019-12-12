package test.crossway;

import dssc.crossway.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class RulesTest {

    @Test
    public void validateLegalMoveTest() throws OutOfBoardException {
        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());
        gc.startGame();
        Move m0 = new Move(1, 1, Colors.WHITE);
        assertTrue(gc.validateMove(m0));
    }

    @Test
    public void validatePieRuleTest() throws OutOfBoardException, IllegalMoveException {
        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());
        gc.startGame();


        Coordinates m0 = new Coordinates(1, 1);
        Move m1 = new Move(1, 1, Colors.BLACK);

        gc.performGameMove(m0);
        assertTrue(gc.validateMove(m1));
    }

    @Test
    public void validateIllegalMoveTest() throws OutOfBoardException, IllegalMoveException {
        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());
        gc.startGame();

        Move m1 = new Move(1, 1, Colors.WHITE);
        Move m2 = new Move(1, 1, Colors.WHITE);
        gc.placeStone( m1 );
        assertFalse(gc.validateMove(m2));
    }

    @Test
    public void crosswayIllegalMoveTestSoutheast() throws OutOfBoardException {

        /**
         *     0 1 2
         *   0
         *   1   F E
         *   2   E i
         *
         */
        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());
        gc.startGame();
        try {
            gc.placeStone(new Move(1, 1, Colors.BLACK));
            gc.placeStone(new Move(1, 2, Colors.WHITE));
            gc.placeStone(new Move(2, 1, Colors.WHITE));

        } catch (Exception e) {
            System.out.println("Something wrong with the theoretically legal moves.");
        }

        Move m4 = new Move(2, 2, Colors.BLACK);
        assertFalse(gc.validateMove(m4));
    }

    @Test
    public void crosswayIllegalMoveTestSouthwest() throws OutOfBoardException {

        /**
         *     0 1 2
         *   0
         *   1   E F
         *   2   i E
         *
         */
        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());
        gc.startGame();
        try {
            gc.placeStone(new Move(2, 1, Colors.BLACK));
            gc.placeStone(new Move(2, 2, Colors.WHITE));
            gc.placeStone(new Move(1, 1, Colors.WHITE));

        } catch (Exception e) {
            System.out.println("Something wrong with the theoretically legal moves.");
        }

        Move m4 = new Move(1, 2, Colors.BLACK);
        assertFalse(gc.validateMove(m4));
    }

    @Test
    public void crosswayIllegalMoveTestNorthwest() throws OutOfBoardException {

        /**
         *     0 1 2
         *   0
         *   1   i E
         *   2   E F
         *
         */
        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());
        gc.startGame();
        try {
            gc.placeStone(new Move(2, 2, Colors.BLACK));
            gc.placeStone(new Move(1, 2, Colors.WHITE));
            gc.placeStone(new Move(2, 1, Colors.WHITE));

        } catch (Exception e) {
            System.out.println("Something wrong with the theoretically legal moves.");
        }

        Move m4 = new Move(1, 1, Colors.BLACK);
        assertFalse(gc.validateMove(m4));
    }

    @Test
    public void crosswayIllegalMoveTestNorthEast() throws OutOfBoardException {

        /**
         *     0 1 2
         *   0
         *   1   E i
         *   2   F E
         *
         */
        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());
        gc.startGame();
        try {
            gc.placeStone(new Move(1, 2, Colors.BLACK));
            gc.placeStone(new Move(1, 1, Colors.WHITE));
            gc.placeStone(new Move(2, 2, Colors.WHITE));

        } catch (Exception e) {
            System.out.println("Something wrong with the theoretically legal moves.");
        }

        Move m4 = new Move(2, 1, Colors.BLACK);
        assertFalse(gc.validateMove(m4));
    }

    @Test
    public void hasWhiteWon() throws OutOfBoardException, IllegalMoveException {


        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());
        gc.startGame();
        try {
            for(int i = 0; i < gc.getSide() ; i++){
                gc.placeStone(new Move(i, 0, Colors.WHITE));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(gc.winner(), Colors.WHITE);
    }


    @Test
    public void hasBlackWon() throws OutOfBoardException, IllegalMoveException {


        GameController gc = new GameController(new GoBoard(12), new CrosswayRules());
        gc.startGame();
        try {
            for(int i = 0; i < gc.getSide() ; i++){
                gc.placeStone(new Move(0, i, Colors.BLACK));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(gc.winner(), Colors.BLACK);
    }
    
}