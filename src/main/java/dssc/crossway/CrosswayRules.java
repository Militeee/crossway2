package dssc.crossway;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *  Game rules class. This class is responsible for validation of a move.
 */
public class CrosswayRules extends BoardGameRules {


    /**
     * No superposition rule: a player cannot place a piece in an already occupied cell.
     * @param board the game board
     * @param m the Move object to be validated
     * @return true if the move is legal, false otherwise.
     * @throws OutOfBoardException if the Move falls outside of the board.
     */
    private boolean noSuperposition(GoBoard board, Move m) throws OutOfBoardException {
        return board.getCellStatus(m.getCoordinates()) == StoneColor.EMPTY;
    }

    /**
     * No crossways rule: forbids the placement of a piece if nearby pieces are in a certain configuration.
     * See website rules for details
     * @param board the game board
     * @param m the Move object to be validated
     * @return true if the move is legal, false otherwise.
     * @throws OutOfBoardException if the Move falls outside of the board.
     */
    private boolean noCrossways(GoBoard board, Move m) throws OutOfBoardException {
        ArrayList<Coordinates> diagonalPositions = board.getDiagonalPositions(m.getCoordinates());

        return diagonalPositions.stream()
                .map(x -> !areCrossed(board, m, x))
                .reduce(true, Boolean::logicalAnd);
    }

    /**
     * Helper function for noCrossway method.
     *
     * @param board the game board
     * @param m the Move object to be validated
     * @param coord2 the coordinates of the cells which legality should be verified.
     * @return true if the move is legal, false otherwise.
     */
    private boolean areCrossed(GoBoard board, Move m, Coordinates coord2) {
        Coordinates coord1 = m.getCoordinates();
        Coordinates coord3 = new Coordinates(coord1.getX(), coord2.getY());
        Coordinates coord4 = new Coordinates(coord2.getX(), coord1.getY());

        StoneColor c[] = new StoneColor[0];
        try {
            c = new StoneColor[]{m.getColor(), board.getCellStatus(coord2), board.getCellStatus(coord3), board.getCellStatus(coord4)};
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }

        boolean full = c[0]!= StoneColor.EMPTY && c[1]!= StoneColor.EMPTY && c[2]!= StoneColor.EMPTY && c[3]!= StoneColor.EMPTY;
        boolean cross = c[0] == c[1] && c[2] == c[3] && c[0] != c[2];
        return full && cross;

    }


    /**
     *  Main rule validation class. It checks if all the rules are satisfied.
     * @param board the game board.
     * @param m the Move object to be validated
     * @param turn the game turn, passed by GameController. Needed to implement the pie rule.
     * @return true if the move is valid, false otherwise.
     * @throws OutOfBoardException if the Move points out of the board.
     */
    @Override
    public boolean validateMove(GoBoard board, Move m, int turn) throws OutOfBoardException {
        //check rule 1
        if(turn == 1) return true;
        return noSuperposition(board, m) &&
                noCrossways(board,m);

    }

    /**
     * Given a board status, it checks whether there is a winner, if asked by the GameController.
     * @param board a game board
     * @return Colors.EMPTY if there is no winner, Colors object of the winner color if there is one.
     */
    @Override
    public StoneColor winner(GoBoard board){

        try {
            for (int i = 0; i < board.getSide(); i++) {
                // Check white
                if (board.getCellStatus(i, 0) == StoneColor.WHITE) {
                    if (winningChain(i, 0, StoneColor.WHITE, board))
                        return StoneColor.WHITE;
                }
                //Check black
                if (board.getCellStatus(0, i) == StoneColor.BLACK) {
                    if (winningChain(0, i, StoneColor.BLACK, board))
                        return StoneColor.BLACK;
                }
            }
        } catch (Exception e) {e.printStackTrace();}

        return StoneColor.EMPTY;

    }




    /**
     *
     * Implements breadth first search for finding the winner
     *
     * @param x vertical axes
     * @param y horizontal axes
     * @param c color of the current player
     * @param board 2D board
     * @return if there is a winner
     */
    private boolean winningChain(int x, int y, StoneColor c, GoBoard board)  {



        int side = board.getSide();

        boolean[][] visited = new boolean[side][side];
        LinkedList<Coordinates> Q;
        Q = new LinkedList<Coordinates>();

        visited[x][y] = true;
        Coordinates node = new Coordinates(x,y);
        Q.add(node);

        while(Q.size() != 0){

            node = Q.poll();
            for (Coordinates n : adjacentNodes( node, board, c)) {

                if ((n.getX() == (side - 1)) && (c == StoneColor.WHITE)) {
                    return true;
                } else if ((n.getY() == (side - 1)) && (c == StoneColor.BLACK)) {
                    return true;
                }
                if (!visited[n.getX()][n.getY()]) {
                    visited[n.getX()][n.getY()] = true;
                    Q.add(n);
                }

            }

        }

        return false;
    }

    /**
     * Helper function to retrieve cell coordinates of nearby cells where there are stones of the provided color.
     * @param node Coordinate objects of a cell.
     * @param board a game board
     * @param c the provided color
     * @return a LinkedList<Coordinates> </Coordinates>
     */
    private LinkedList<Coordinates> adjacentNodes(Coordinates node, GoBoard board, StoneColor c) {

        int x = node.getX() - 1;
        int y = node.getY() - 1;

        LinkedList<Coordinates> ret = new LinkedList<Coordinates>();


        for(int i = x; i < x + 3; i++){
            for(int j = y; j < y + 3; j++){
                try {
                    if (!(i == node.getX() && j == node.getY()) && (board.getCellStatus(i, j) == c)) {
                        ret.add(new Coordinates(i, j));
                    }
                } catch (Exception ignored) {}
            }
        }

        return ret;
    }

    /**
     * This method returns BLACK: the color of the player that does the first move.
     * @return BLACK
     */
    public StoneColor firstPlayer() {
        return StoneColor.BLACK;
    }

}
