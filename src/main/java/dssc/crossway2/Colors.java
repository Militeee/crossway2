package dssc.crossway2;


/**
 * enum of possible color status of a cell.
 */
public enum Colors {
    EMPTY,
    BLACK,
    WHITE;

    public Colors getOpposite() {
        if (this==Colors.BLACK) {return Colors.WHITE;}
        else if (this ==Colors.WHITE) {return Colors.BLACK;}
        return Colors.EMPTY;
    }
}
