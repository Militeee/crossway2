package dssc.crossway;

import dssc.crossway.exceptions.OutOfBoardException;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 *  Game rules class. This class is responsible for validation of a move.
 */
public class CrosswayRules {

    private static final int CROSSWAY_BOARD_SIZE = 12;


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
            return (board.getStoneColorStatus(friendPosition) == m.getColor() &&
                    board.getStoneColorStatus(crossingPosition1) == m.getColor().getOpposite() &&
                    board.getStoneColorStatus(crossingPosition2) == m.getColor().getOpposite());

        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     *  Main rule validation class. It checks if all the rules are satisfied.
     */
    public boolean validateMove(GoBoard board, Move m, int turn) throws OutOfBoardException {
        if(turn == 1) return true;
        return noSuperposition(board, m) && noCrossways(board, m);
    }


    /**
     * Given a board status, it checks whether there is a winner, if asked by the GameController.
     * @param board a game board
     * @param currentPlayer color of the current player
     * @return Colors.EMPTY if there is no winner, Colors object of the winner color if there is one.
     */
    public StoneColor winner(GoBoard board, StoneColor currentPlayer){
       return IntStream.range(0, board.getSide())
               .mapToObj(i -> callRightChain(i, currentPlayer.getOpposite(), board))
               .filter(color -> color == currentPlayer.getOpposite())
               .findAny()
               .orElse(StoneColor.EMPTY);
    }

    private StoneColor callRightChain(int movingIndex, StoneColor color, GoBoard board){
        try {

            if(color == StoneColor.WHITE) {
                return winningChain(new Coordinates(0, movingIndex), StoneColor.WHITE, board);
            } else {
                return winningChain(new Coordinates(movingIndex,0), StoneColor.BLACK, board);
            }

        } catch (OutOfBoardException e) {
                e.printStackTrace();
        }
        return StoneColor.EMPTY;
    }


    private StoneColor winningChain(Coordinates node, StoneColor color, GoBoard board) throws OutOfBoardException {


        if (board.getStoneColorStatus(node) != color) return StoneColor.EMPTY;
        int side = board.getSide();
        boolean[][] visited = new boolean[side][side];
        return winningChainAux(node, color, board, visited);

    }


    private StoneColor winningChainAux(Coordinates node, StoneColor color, GoBoard board, boolean[][] visited) {


        visited[node.getX()][node.getY()] = true;

        ArrayList<Coordinates> nodeList =  filterVisited(node, board, visited);

        StoneColor winningColor = colorOfWinningPlayer(node, color, board);

        if(winningColor != StoneColor.EMPTY) return winningColor;

        if(nodeList.isEmpty()) return StoneColor.EMPTY;

        return nodeList.stream().map(n -> winningChainAux(n, color, board,visited))
                .filter(c -> c == color)
                .findAny()
                .orElse(StoneColor.EMPTY);

    }

    private ArrayList<Coordinates> filterVisited(Coordinates node, GoBoard board, boolean[][] visited){

        return board.adjacentFriendsCoordinates(node).stream()
                .filter(n -> !visited[n.getX()][n.getY()])
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private StoneColor colorOfWinningPlayer(Coordinates node, StoneColor color, GoBoard board){

        if ((node.getX() == (board.getSide() - 1)) && (color == StoneColor.WHITE)) {
            return StoneColor.WHITE;
        } else if ((node.getY() == (board.getSide() - 1)) && (color == StoneColor.BLACK)) {
            return StoneColor.BLACK;
        } else {
            return StoneColor.EMPTY;
        }

    }

    StoneColor firstPlayer() {
        return StoneColor.BLACK;
    }


    public int boardSize() {
        return CROSSWAY_BOARD_SIZE;
    }
}