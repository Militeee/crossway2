package dssc.crossway;

public class Move {

    private Coordinates coordinates;
    private StoneColor color;

    public Move(Coordinates c, StoneColor color) {
        coordinates = c;
        this.color = color;
    }

    public StoneColor getColor() {
        return color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
