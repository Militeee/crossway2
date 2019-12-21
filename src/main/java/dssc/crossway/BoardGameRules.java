package dssc.crossway;

 abstract class BoardGameRules {

     /**
      *  Abstract class for rule validation.
      */
     BoardGameRules() {
    }

     /**
      * Given the board of the game, the actual turn number, and a move, returns true if the move is legal,
      * false otherwise
      * @param board of the game
      * @param m move which validity will be checked
      * @param turn actual turn
      * @return true if the move is legal, false otherwise
      * @throws OutOfBoardException
      */
     public abstract boolean validateMove(GoBoard board, Move m, int turn) throws OutOfBoardException;

     /**
      * Returns the color of the winner, if no one won it returns the EMPTY color
      * @param board the board of the game used for checking the winner
      * @return the color of the winner
      */
     public abstract StoneColor winner(GoBoard board) ;

     /**
      * This method returns the color of the player that does the first move.
      * @return the color of the player that does the first move
      */
     public abstract StoneColor firstPlayer();
 }
