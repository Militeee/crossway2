package dssc.crossway;

 abstract class Validator {

     /**
      *  Abstract class for rule validation.
      */
     Validator() {
    }

     public abstract boolean validateMove(GoBoard board, Move m0, int turn) throws OutOfBoardException;

     public abstract Colors winner(GoBoard board) ;
 }
