package dssc.crossway2;

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

    //ctor
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
