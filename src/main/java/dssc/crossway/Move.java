package dssc.crossway;

public class Move {

    private Coordinates coordinates;
    private Colors color;

    public Move(Coordinates c, Colors color) {
        coordinates = c;
        this.color = color;
    }

    public Move(int x, int y, Colors color) {
        coordinates = new Coordinates(x,y);
        this.color = color;
    }

    public int getX() {
        return coordinates.getX();
    }

    public int getY() {
        return coordinates.getY();
    }

    public Colors getColor() {
        return color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
