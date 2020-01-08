package dssc.crossway;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *  Data structure used to store information about the position of an entity
 *  in the board
 *
 */

public class Coordinates {


    int x_cord;
    int y_cord;

    public int getX() {
        return x_cord;
    }

    public int getY() {
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

        ArrayList<Coordinates> mesh = new ArrayList<>();

        for(int i = x1; i<x2; i++)
            for(int j = y1; j<y2; j++)
                mesh.add(new Coordinates(i,j));

        return mesh;
    }

    /**
     * Returns an ArrayList with the coordinates of the Adjacents coordinates
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
