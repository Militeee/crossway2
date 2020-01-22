package dssc.crossway;

public enum StoneColor {
    EMPTY,
    BLACK,
    WHITE;

    /**
     * Gives the opposite of a Color.
     * @return  white if black, return black if white, otherwise returns empty.
     */
    public StoneColor getOpposite() {
        if (this== StoneColor.BLACK) {return StoneColor.WHITE;}
        else if (this == StoneColor.WHITE) {return StoneColor.BLACK;}
        return StoneColor.EMPTY;
    }
}
