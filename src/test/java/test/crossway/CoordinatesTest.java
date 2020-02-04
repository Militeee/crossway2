package test.crossway;

import dssc.crossway.Coordinates;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class CoordinatesTest {

    @Test
    public void equalsTest() {
        assertEquals(new Coordinates(2, 3), new Coordinates(2, 3));
        assertNotEquals(new Coordinates(2, 8), new Coordinates(2, 3));
        assertNotEquals(new Coordinates(2, 8), new Object());
    }

    @Test
    public void coordinatesMeshTest() {
        List<Coordinates> l = Coordinates.getCoordinatesMesh(0,4,2,7);
        assertEquals(20, l.size());
        assertTrue(l.contains(new Coordinates(0,2)));
        assertTrue(l.contains(new Coordinates(3,2)));
        assertFalse(l.contains(new Coordinates(4,2)));
        assertFalse(l.contains(new Coordinates(2,7)));
    }

    @Test
    public void getAdjacentsTest() {
        Coordinates center = new Coordinates(3,4);
        List<Coordinates> l = center.getAdjacents();

        assertEquals(8, l.size());
        assertTrue(l.contains(new Coordinates(3,5)));
        assertTrue(l.contains(new Coordinates(3,3)));

        assertTrue(l.contains(new Coordinates(4,5)));
        assertTrue(l.contains(new Coordinates(4,4)));
        assertTrue(l.contains(new Coordinates(4,3)));

        assertTrue(l.contains(new Coordinates(2,5)));
        assertTrue(l.contains(new Coordinates(2,4)));
        assertTrue(l.contains(new Coordinates(2,3)));

        assertFalse(l.contains(center));
        assertFalse(l.contains(new Coordinates(2,7)));
        assertFalse(l.contains(new Coordinates(4,7)));
    }
}
