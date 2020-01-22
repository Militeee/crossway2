package dssc.crossway;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoordinateMeshTools {

    /**
     * Returns an ArrayList with the coordinates of the discrete space [x1, x2)x[y1,y2)
     * @param x1 included
     * @param x2 excluded
     * @param y1 included
     * @param y2 excluded
     * @return  ArrayList of coordinates
     */
    static ArrayList<Coordinates> getCoordinatesMesh(int x1, int x2, int y1, int y2) {
        return IntStream.range(x1,x2).boxed()
                .flatMap(x -> IntStream.range(y1,y2).mapToObj(y -> new Coordinates(x,y)))
                .collect( Collectors.toCollection(ArrayList::new));
    }
}
