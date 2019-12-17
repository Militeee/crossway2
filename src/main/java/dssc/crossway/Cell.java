package dssc.crossway;

/**
 * A class that stores a cell with a defined status coded as a Colors enumerator
 *
 * Default status = "Colors.EMPTY"
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
