package dssc.crossway;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *  Data structure used to store information about the position of an entity
 *  in the board
 *
 */

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
     * Returns an ArrayList with the coordinates of the adjacent coordinates
     * with diagonals, center excluded
     *
     * @return  ArrayList of coordinates
     */
    ArrayList<Coordinates> getAdjacents() {

        return CoordinateMeshTools.getCoordinatesMesh(getX()-1, getX()+2, getY()-1, getY()+2).stream()
                .filter(c -> !this.equals(c))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
