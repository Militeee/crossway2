package dssc.crossway;

/**
 * This class represents a cell characterized by a color.
 */
public class Cell {

    private StoneColor status;

    public Cell() {
        this.status = StoneColor.EMPTY;
    }

    Cell (StoneColor status) {
        this.status = status;
    }

    public StoneColor getStatus() {
        return this.status;
    }

    void setStatus(StoneColor newStatus) {
        this.status = newStatus;
    }
}
