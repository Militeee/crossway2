package dssc.crossway.console;

import dssc.crossway.Coordinates;
import dssc.crossway.GoBoard;
import dssc.crossway.exceptions.OutOfBoardException;
import dssc.crossway.StoneColor;

import java.util.stream.IntStream;


public class ConsoleBoardWriter {

    private final static char EMPTY_SIGN = '.';
    private final static char WHITE_SIGN =  '\u25CF';
    private final static char BLACK_SIGN = '\u25CB';


    public void printBoard(GoBoard board) {
        System.out.println(boardToString(board));
    }


    public String boardToString(GoBoard board) {
        char[][] canvas = new char[board.getSide()][board.getSide()];

        Coordinates.getCoordinatesMesh(0,board.getSide(),0,board.getSide())
                .forEach(c -> {
                    try {
                        canvas[c.getY()][c.getX()] = color2consoleRepresentation(board.getStoneColorStatus(c));
                    } catch (OutOfBoardException e) {
                        e.printStackTrace();
                    }
                });

        return matrixToString(canvas,board.getSide(),board.getSide());
    }

    private char color2consoleRepresentation(StoneColor c) {

        char sign;

        switch(c)
        {
            case WHITE: sign = WHITE_SIGN; break;
            case BLACK: sign = BLACK_SIGN; break;
            case EMPTY: sign = EMPTY_SIGN; break;
            default:
                throw new IllegalStateException("Unexpected value: " + c);
        }

        return sign;
    }

    /**
     * Method that returns the String representation of a matrix of characters
     * @param charMatrix a 2D array of chars
     * @param x first dimension of the array
     * @param y second dimension of the array
     * @return String representation of a matrix of characters
     */
    private String matrixToString(char[][] charMatrix, int x, int y) {

        StringBuilder s = new StringBuilder();

        s.append ("  ");

        IntStream.range(0, y)
                .forEachOrdered(n -> s.append(n % 10).append(" "));

        s.append("\n");

        IntStream.range(0, x)
            .forEachOrdered(row -> {
                s.append(row % 10).append(" ");
                IntStream.range(0,y)
                         .forEachOrdered(col -> s.append(charMatrix[row][col]).append(" "));
                s.append("\n");
            });
        return s.substring(0,s.length()-1);
    }

}
