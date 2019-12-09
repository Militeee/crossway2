package dssc.crossway2;

 abstract class Validator {

     Validator() {
    }

     public abstract boolean validateMove(GoBoard board, Move m0) throws OutOfBoardException;

     public abstract Colors winner(GoBoard board) throws OutOfBoardException;
 }
