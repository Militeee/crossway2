package dssc.crossway;

/**
 * A Cell class.
 *
 * Default status = "null"
 */
public class Cell {

    private Colors status;

    public Cell() {
        this.status = Colors.EMPTY;
    }

    Cell (Colors status) {
        this.status = status;
    }

    public Colors getStatus() {
        return this.status;
    }

    void setStatus(Colors newStatus) {
        this.status = newStatus;
    }
}
