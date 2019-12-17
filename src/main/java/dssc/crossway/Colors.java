package dssc.crossway;


/**
 * enum of possible color status of a cell.
 */
public enum Colors {
    EMPTY,
    BLACK,
    WHITE;

    /**
     * Gives the opposite of a Color.
     * @return  white if black, return black if white, otherwise returns empty.
     */
    public Colors getOpposite() {
        if (this== Colors.BLACK) {return Colors.WHITE;}
        else if (this == Colors.WHITE) {return Colors.BLACK;}
        return Colors.EMPTY;
    }
}
