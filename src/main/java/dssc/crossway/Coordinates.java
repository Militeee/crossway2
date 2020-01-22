package dssc.crossway;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Coordinates {


    private int x_cord;
    private int y_cord;

    int getX() {
        return x_cord;
    }

    int getY() {
        return y_cord;
    }

    public Coordinates(int x_cord, int y_cord) {
        this.x_cord = x_cord;
        this.y_cord = y_cord;
    }

    /**
     * Returns an ArrayList with the coordinates of the discrete space [x1, x2)x[y1,y2)
     * @param x1 included
     * @param x2 excluded
     * @param y1 included
     * @param y2 excluded
     * @return  ArrayList of coordinates
     */
    public static ArrayList<Coordinates> getCoordinatesMesh(int x1, int x2, int y1, int y2) {
        return IntStream.range(x1,x2).boxed()
                .flatMap(x -> IntStream.range(y1,y2).mapToObj(y -> new Coordinates(x,y)))
                .collect( Collectors.toCollection(ArrayList::new));
    }


    /**
     * Returns an ArrayList with the coordinates of the adjacent coordinates
     * with diagonals, center excluded
     *
     * @return  ArrayList of coordinates
     */
    public ArrayList<Coordinates> getAdjacents() {

        return getCoordinatesMesh(getX()-1, getX()+2, getY()-1, getY()+2).stream()
                .filter(c -> !this.equals(c))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
