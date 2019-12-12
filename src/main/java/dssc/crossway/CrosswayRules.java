package dssc.crossway;

import java.util.LinkedList;

public class CrosswayRules extends Validator {

    public CrosswayRules() {

    }

    private boolean noSuperposition(GoBoard board, Move m) throws OutOfBoardException {
        return board.getCellStatus(m.getX(), m.getY()) == Colors.EMPTY;
    }

    private boolean noCrossways(GoBoard board, Move m) throws OutOfBoardException {

        Colors enemy = m.getColor().getOpposite();
        Colors friendly = m.getColor();
        boolean legality = true;
        GoBoard window = new GoBoard(2);

        //case 1: the move cell is SouthEast
        try {
            window.setCellStatus(0,0, board.getCellStatus(m.getX()-1, m.getY()-1));
            window.setCellStatus(1,0, board.getCellStatus(m.getX(),      m.getY()-1));
            window.setCellStatus(0,1, board.getCellStatus(m.getX()-1,    m.getY())  );
        } catch (OutOfBoardException ignored) {}

        if ( window.getCellStatus(0,0) == friendly &&
            window.getCellStatus(1,0) == enemy &&
            window.getCellStatus(0,1) == enemy)  {legality = false;}

        //case 2: the move cell is SouthWest
        try {
            window.setCellStatus(0,0, board.getCellStatus(m.getX(), m.getY()-1));
            window.setCellStatus(1,0, board.getCellStatus(m.getX()+1,      m.getY()-1));
            window.setCellStatus(1,1, board.getCellStatus(m.getX()+1,    m.getY())  );
        } catch (OutOfBoardException ignored) {}

        if ( window.getCellStatus(1,0) == friendly &&
                window.getCellStatus(0,0) == enemy &&
                window.getCellStatus(1,1) == enemy)  {legality = false;}

        //case 3: the move cell is NorthWest
        try {
            window.setCellStatus(0,1, board.getCellStatus(m.getX(), m.getY()+1));
            window.setCellStatus(1,1, board.getCellStatus(m.getX()+1,      m.getY()+1));
            window.setCellStatus(1,0, board.getCellStatus(m.getX()+1,    m.getY()));
        } catch (OutOfBoardException ignored) {}

        if ( window.getCellStatus(1,1) == friendly &&
                window.getCellStatus(1,0) == enemy &&
                window.getCellStatus(0,1) == enemy)  {legality = false;}

        //case 4: the move cell is NorthEast
        try {
            window.setCellStatus(0,0, board.getCellStatus(m.getX()-1,         m.getY()));
            window.setCellStatus(0,1, board.getCellStatus(m.getX()-1,      m.getY()+1));
            window.setCellStatus(1,1, board.getCellStatus(m.getX(),           m.getY()+1));
        } catch (OutOfBoardException ignored) {}

        if ( window.getCellStatus(0,1) == friendly &&
                window.getCellStatus(0,0) == enemy &&
                window.getCellStatus(1,1) == enemy)  {legality = false;}



        return legality;
    }

    @Override
    public boolean validateMove(GoBoard board, Move m) throws OutOfBoardException {
        //check rule 1
        return noSuperposition(board, m) &&
                noCrossways(board,m);

    }

    @Override

    public Colors winner(GoBoard board){

        try {
            for (int i = 0; i < board.getSide(); i++) {
                // Check white
                if (board.getCellStatus(i, 0) == Colors.WHITE) {
                    if (winningChain(i, 0, Colors.WHITE, board))
                        return Colors.WHITE;
                }
                //Check black
                if (board.getCellStatus(0, i) == Colors.BLACK) {
                    if (winningChain(0, i, Colors.BLACK, board))
                        return Colors.BLACK;
                }
            }
        } catch (Exception e) {e.printStackTrace();}

        return Colors.EMPTY;

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
     * @throws OutOfBoardException
     */

    private boolean winningChain(int x, int y, Colors c, GoBoard board)  {



        int side = board.getSide();

        boolean visited[][] = new boolean[side][side];
        LinkedList<Coordinates> Q = new LinkedList<Coordinates>();

        visited[x][y] = true;
        Coordinates node = new Coordinates(x,y);
        Q.add(node);

        while(Q.size() != 0){

            node = Q.poll();
            for (Coordinates n : adjacentNodes( node, board, c)) {

                if ((n.getX_cord() == (side - 1)) && (c == Colors.WHITE)) {
                    return true;
                } else if ((n.getY_cord() == (side - 1)) && (c == Colors.BLACK)) {
                    return true;
                }
                if (!visited[n.getX_cord()][n.getY_cord()]) {
                    visited[n.getX_cord()][n.getY_cord()] = true;
                    Q.add(n);
                }

            }

        }

        return false;
    }

    private LinkedList<Coordinates> adjacentNodes(Coordinates node, GoBoard board, Colors c) {

        int x = node.getX_cord() - 1;
        int y = node.getY_cord() - 1;

        LinkedList<Coordinates> ret = new LinkedList<Coordinates>();


        for(int i = x; i < x + 3; i++){
            for(int j = y; j < y + 3; j++){
                try {
                    if ((i != node.getX_cord() || j != node.getY_cord()) && (board.getCellStatus(i, j) == c)) {
                        ret.add(new Coordinates(i, j));
                    }
                } catch (OutOfBoardException ignored) {}
            }
        }

        return ret;
    }

}
