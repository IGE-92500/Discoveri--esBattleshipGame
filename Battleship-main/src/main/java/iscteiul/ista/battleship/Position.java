package iscteiul.ista.battleship;

import java.util.Objects;

public class Position implements IPosition {
    private int row;
    private int column;
    private boolean isOccupied;
    private boolean isHit;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    @Override public int getRow() { return row; }
    @Override public int getColumn() { return column; }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IPosition)) return false;
        IPosition other = (IPosition) obj;
        return row == other.getRow() && column == other.getColumn();
    }

    @Override
    public boolean isAdjacentTo(IPosition other) {
        if (other == null) return false;
        return Math.abs(row - other.getRow()) <= 1 && Math.abs(column - other.getColumn()) <= 1;
    }

    @Override public void occupy() { isOccupied = true; }
    @Override public void shoot() { isHit = true; }
    @Override public boolean isOccupied() { return isOccupied; }
    @Override public boolean isHit() { return isHit; }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
