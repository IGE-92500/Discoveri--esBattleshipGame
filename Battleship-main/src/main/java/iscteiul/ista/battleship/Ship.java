package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Ship implements IShip {
    private static final String GALEAO = "galeao";
    private static final String FRAGATA = "fragata";
    private static final String NAU = "nau";
    private static final String CARAVELA = "caravela";
    private static final String BARCA = "barca";

    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        if (shipKind == null) return null;
        Ship s;
        switch (shipKind.toLowerCase()) {
            case BARCA: s = new Barge(bearing, pos); break;
            case CARAVELA: s = new Caravel(bearing, pos); break;
            case NAU: s = new Carrack(bearing, pos); break;
            case FRAGATA: s = new Frigate(bearing, pos); break;
            case GALEAO: s = new Galleon(bearing, pos); break;
            default: s = null;
        }
        return s;
    }

    private String category;
    private Compass bearing;
    private IPosition pos;
    protected List<IPosition> positions;

    public Ship(String category, Compass bearing, IPosition pos) {
        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        this.positions = new ArrayList<>();
    }

    @Override public String getCategory() { return category; }
    @Override public List<IPosition> getPositions() { return positions; }
    @Override public IPosition getPosition() { return pos; }
    @Override public Compass getBearing() { return bearing; }

    @Override
    public boolean stillFloating() {
        for (IPosition p : positions) if (!p.isHit()) return true;
        return false;
    }

    @Override
    public int getTopMostPos() {
        if (positions.isEmpty()) return 0;
        int top = positions.get(0).getRow();
        for (IPosition p : positions) if (p.getRow() < top) top = p.getRow();
        return top;
    }

    @Override
    public int getBottomMostPos() {
        if (positions.isEmpty()) return 0;
        int bottom = positions.get(0).getRow();
        for (IPosition p : positions) if (p.getRow() > bottom) bottom = p.getRow();
        return bottom;
    }

    @Override
    public int getLeftMostPos() {
        if (positions.isEmpty()) return 0;
        int left = positions.get(0).getColumn();
        for (IPosition p : positions) if (p.getColumn() < left) left = p.getColumn();
        return left;
    }

    @Override
    public int getRightMostPos() {
        if (positions.isEmpty()) return 0;
        int right = positions.get(0).getColumn();
        for (IPosition p : positions) if (p.getColumn() > right) right = p.getColumn();
        return right;
    }

    @Override
    public boolean occupies(IPosition pos) {
        for (IPosition p : positions) if (p.equals(pos)) return true;
        return false;
    }

    @Override
    public boolean tooCloseTo(IShip other) {
        for (IPosition p : other.getPositions()) if (tooCloseTo(p)) return true;
        return false;
    }

    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (IPosition p : positions) if (p.isAdjacentTo(pos)) return true;
        return false;
    }

    @Override
    public void shoot(IPosition pos) {
        for (IPosition p : positions) if (p.equals(pos)) p.shoot();
    }

    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }
}
