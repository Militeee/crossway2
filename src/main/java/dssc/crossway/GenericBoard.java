package dssc.crossway;

abstract class GenericBoard {

    int side;
     Cell[][] board;

    /**
     * GoBoard Constructor. Initializes a matrix of Cell items
     * @param side the side of the board in units
     */
      GenericBoard (int side) {

        this.side = side;
        this.board = new Cell[side][side];

    }

    abstract void initializeBoard();
}
