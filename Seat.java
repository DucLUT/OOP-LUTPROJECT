package pkg;

public class Seat {
    protected int row;
    protected int column;
    protected boolean available;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.available = true; // By default, a new seat is available
    }

    // Getter for the row number
    public int getRow() {
        return row;
    }

    // Setter for the row number
    public void setRow(int row) {
        this.row = row;
    }

    // Getter for the column number
    public int getColumn() {
        return column;
    }

    // Setter for the column number
    public void setColumn(int column) {
        this.column = column;
    }

    // Getter for the seat availability
    public boolean isAvailable() {
        return available;
    }

    // Setter for the seat availability
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
