package dssc.crossway;

import dssc.crossway.exceptions.OutOfBoardException;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 *  Game rules class. This class is responsible for validation of a move.
 */
public class CrosswayRules {


    /**
     * No superposition rule: a player cannot place a piece in an already occupied cell.
     * @param board the game board
     * @param m the Move object to be validated
     * @return true if the move is legal, false otherwise.
     * @throws OutOfBoardException if the Move falls outside of the board.
     */
    private boolean noSuperposition(GoBoard board, Move m) throws OutOfBoardException {

        return board.getStoneColorStatus(m.getCoordinates()) == StoneColor.EMPTY;
    }

    /**
     * No crossways rule: forbids the placement of a piece if nearby pieces are in a certain configuration.
     * See website rules for details
     * @param board the game board
     * @param m the Move object to be validated
     * @return true if the move is legal, false otherwise.
     */
    private boolean noCrossways(GoBoard board, Move m) {
        ArrayList<Coordinates> diagonalPositions = board.getDiagonalPositions(m.getCoordinates());

        return diagonalPositions.stream()
                .map(x -> !areCrossed(board, m, x))
                .reduce(true, Boolean::logicalAnd);
    }


    /**
     * Checks for a given move and a given relative adjacent-diagonal position on the board
     * if the move is considered illegal, since in these two position there are pieces of the same color,
     * and this is opposite to the color of the pieces that "cross" these two.
     *
     * @param board the game board
     * @param m the Move object to be validated
     * @param friendPosition the coordinates of the cells which legality should be verified.
     * @return true if the move is legal, false otherwise.
     */
    private boolean areCrossed(GoBoard board, Move m, Coordinates friendPosition) {

        Coordinates crossingPosition1 = new Coordinates(m.getCoordinates().getX(), friendPosition.getY());
        Coordinates crossingPosition2 = new Coordinates(friendPosition.getX(), m.getCoordinates().getY());

        try {
            if(board.getStoneColorStatus(crossingPosition1) == m.getColor().getOpposite() &&
                    board.getStoneColorStatus(crossingPosition2) == m.getColor().getOpposite())
                return true;

        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     *  Main rule validation class. It checks if all the rules are satisfied.
     * @param board the game board.
     * @param m the Move object to be validated
     * @param turn the game turn, passed by GameController. Needed to implement the pie rule.
     * @return true if the move is valid, false otherwise.
     * @throws OutOfBoardException if the Move points out of the board.
     */

    public boolean validateMove(GoBoard board, Move m, int turn) throws OutOfBoardException {
        if(turn == 1) return true;
        return noSuperposition(board, m) && noCrossways(board,m);
    }

    /**
     * Given a board status, it checks whether there is a winner, if asked by the GameController.
     * @param board a game board
     * @return Colors.EMPTY if there is no winner, Colors object of the winner color if there is one.
     */

    public StoneColor winner(GoBoard board){
        try {
            for (int i = 0; i < board.getSide(); i++) {
                // Check white
                if(hasPlayerWon(new Coordinates(0,i),StoneColor.WHITE, board))
                    return StoneColor.WHITE;

                //Check black
                if(hasPlayerWon(new Coordinates(i,0),StoneColor.BLACK, board))
                    return StoneColor.BLACK;
            }
        } catch (OutOfBoardException e) {e.printStackTrace();}

        return StoneColor.EMPTY;

    }

    /**
     * Performs a winningChain check if there is a stone of a given color in a given coordinate.
     * @param coor coordinates
     * @param col color that is being considered
     * @param board the game board.
     * @return true if the color compatible with the coordinate has won the game, false otherwise
     * @throws OutOfBoardException if the Move points out of the board.
     */
    private boolean hasPlayerWon(Coordinates coor, StoneColor col, GoBoard board) throws OutOfBoardException {
        if (board.getStoneColorStatus(coor) == col) {
            return winningChain(coor, col, board);
        }
        return false;
    }



    /**
     *
     * Implements breadth first search for finding the winner
     *
     * @param node coordinates of the node
     * @param c color of the current player
     * @param board 2D board
     * @return if there is a winner
     */
    private boolean winningChain(Coordinates node, StoneColor c, GoBoard board)  {

        int side = board.getSide();

        boolean[][] visited = new boolean[side][side];
        LinkedList<Coordinates> queue = new LinkedList<>();

        visited[node.getX()][node.getY()] = true;
        queue.add(node);

        while(queue.size() != 0){

            node = queue.poll();
            for (Coordinates coordinates : board.adjacentFriendsCoordinates(node, c)) {

                if ((coordinates.getX() == (side - 1)) && (c == StoneColor.WHITE)) {
                    return true;
                } else if ((coordinates.getY() == (side - 1)) && (c == StoneColor.BLACK)) {
                    return true;
                }

                if (!visited[coordinates.getX()][coordinates.getY()]) {
                    visited[coordinates.getX()][coordinates.getY()] = true;
                    queue.add(coordinates);
                }

            }

        }

        return false;
    }

    public StoneColor firstPlayer() {
        return StoneColor.BLACK;
    }

}